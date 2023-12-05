package com.example.locationMasterDemo.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class KafkaConsumerConfig {

        @Bean
        public ReceiverOptions<String, String> kafkaReceiverOptions(@Value(value = "${spring.kafka.topic}") String topic, KafkaProperties kafkaProperties) {
            ReceiverOptions<String, String> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
            return basicReceiverOptions.subscription(Collections.singletonList(topic));
        }

        @Bean
        public ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate(ReceiverOptions<String, String> kafkaReceiverOptions) {
            return new ReactiveKafkaConsumerTemplate<String, String>(kafkaReceiverOptions);
        }

}
