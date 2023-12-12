package com.example.locationMasterDemo.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${kafka.security.protocol}")
    private String securityProtocol;
    @Value(value = "${kafka.sasl.jaas.config}")
    private String saslJassConfig;
    @Value(value = "${kafka.sasl.mechanism}")
    private String saslMechanism;
    @Value(value = "${kafka.consumer.group.id}")
    private String groupId;

        @Bean
        public ReceiverOptions<String, String> kafkaReceiverOptions(@Value(value = "${kafka.topic}") String topic, KafkaProperties kafkaProperties) {
            Map<String, Object> props = kafkaProperties.buildConsumerProperties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-6ojv2.us-west4.gcp.confluent.cloud:9092");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put("security.protocol", securityProtocol);
            props.put("sasl.mechanism", saslMechanism);
            props.put("sasl.jaas.config", saslJassConfig);
            ReceiverOptions<String, String> basicReceiverOptions = ReceiverOptions.create(props);
            return basicReceiverOptions.subscription(Collections.singletonList(topic));
        }

        @Bean
        public ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate(ReceiverOptions<String, String> kafkaReceiverOptions) {
            return new ReactiveKafkaConsumerTemplate<String, String>(kafkaReceiverOptions);
        }

}
