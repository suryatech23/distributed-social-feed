package com.socialfeed.feed_engine.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.socialfeed.feed_engine.model.Post;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostProducer {
    
    private static final String TOPIC = "social-feed";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public PostProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    public void sendPostEvent(Post post) {
        try {
            String payload = objectMapper.writeValueAsString(post);
            kafkaTemplate.send(TOPIC, payload);
            System.out.println("🚀 [PRODUCER] Event published to Kafka: " + payload);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}