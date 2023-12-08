package com.example.locationMasterDemo.service;

import com.example.locationMasterDemo.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.core.publisher.Flux;
@EnableKafka
@Slf4j
public class KafkaLocationConsumerService {

    Logger log = LoggerFactory.getLogger(KafkaLocationConsumerService.class);

    @Value(value = "${spring.kafka.topic}")
    private String topic;

    @Autowired
    private final LocationService locationService;

    @Autowired
    private final ReactiveKafkaConsumerTemplate <String, String> reactiveKafkaConsumerTemplate;

    public KafkaLocationConsumerService(LocationService locationService, ReactiveKafkaConsumerTemplate <String, String>  reactiveKafkaConsumerTemplate) {
        this.locationService = locationService;
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }

  // @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.groupId}")
  @KafkaListener(topics = "locationtopic_0", groupId = "ConsumerGroup1")
  public Flux<Void> consumerMsg() {
        log.info("Inside consumer");
        return reactiveKafkaConsumerTemplate.receiveAutoAck()
                .doOnNext(consumerRecord -> log.info("received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .flatMap(consumerRecord -> locationService.saveLocation(consumerRecord.value()))
                .doOnNext(employee -> log.info("successfully consumed {}={}", employee, employee))
                .doOnError(throwable -> log.error("something bad happened while consuming : {}", throwable.getMessage()));
    }

}
