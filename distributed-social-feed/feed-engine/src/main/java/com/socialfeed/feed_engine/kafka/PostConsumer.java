package com.socialfeed.feed_engine.kafka;

import com.socialfeed.feed_engine.model.Post;
import com.socialfeed.feed_engine.repository.PostRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PostConsumer {

    private final PostRepository postRepository;
    private final ObjectMapper objectMapper;

    public PostConsumer(PostRepository postRepository) {
        this.postRepository = postRepository;
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @KafkaListener(topics = "social-feed", groupId = "social-feed-group")
    public void consumePostEvent(String message) {
        try {
            Post post = objectMapper.readValue(message, Post.class);
            postRepository.save(post);
            System.out.println("💾 [CONSUMER] Event processed and saved to MongoDB: " + post.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}