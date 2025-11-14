package com.example.main.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic menuTopic() {

        return TopicBuilder.name("kfk-menu-tpc")
                .partitions(2)
                .replicas(1)
                .build();
    }
}
