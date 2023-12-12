package com.example.locationMasterDemo.service;

import com.example.locationMasterDemo.model.LocationEntity;
import com.example.locationMasterDemo.repository.LocationRepository;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@EnableKafka
@Component
@Slf4j
public class KafkaLocationConsumerService {

    Logger log = LoggerFactory.getLogger(KafkaLocationConsumerService.class);

    @Value(value = "${kafka.topic}")
    private String topic;

    @Autowired
    private final LocationRepository locationRepository;

    @Autowired
    private final ReactiveKafkaConsumerTemplate <String, String> reactiveKafkaConsumerTemplate;

    public KafkaLocationConsumerService(LocationRepository locationRepository, ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate) {
      //  this.locationService = locationService;
        this.locationRepository = locationRepository;
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }

    @EventListener(ApplicationStartedEvent.class)
    public Flux<LocationEntity> consumerMsg() {
        log.info("Inside consumer");
        return reactiveKafkaConsumerTemplate.receiveAutoAck()
                .doOnNext(consumerRecord -> log.info("received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .flatMap(consumerRecord -> locationRepository.save(new LocationEntity(UUID.randomUUID(),Json.of(consumerRecord.value())))
                .doOnNext(location -> log.info("successfully consumed {}={}", location, location))
                .doOnError(throwable -> log.error("something bad happened while consuming : {}", throwable.getMessage())));
    }


}
