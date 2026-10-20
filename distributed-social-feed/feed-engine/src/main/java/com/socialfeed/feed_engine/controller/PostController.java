package com.socialfeed.feed_engine.controller;

import com.socialfeed.feed_engine.kafka.PostProducer;
import com.socialfeed.feed_engine.model.Post;
import com.socialfeed.feed_engine.repository.PostRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostProducer postProducer;
    private final PostRepository postRepository;

    public PostController(PostProducer postProducer, PostRepository postRepository) {
        this.postProducer = postProducer;
        this.postRepository = postRepository;
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        // Generate metadata before sending to the queue
        post.setId(UUID.randomUUID().toString());
        post.setTimestamp(Instant.now());
        
        postProducer.sendPostEvent(post);
        return ResponseEntity.accepted().body("Post accepted for processing. ID: " + post.getId() + "\n");
    }

    @GetMapping
    @Cacheable(value = "feedCache")
    public List<Post> getFeed() {
        System.out.println("📦 [CACHE MISS] Fetching recent posts directly from MongoDB...");
        return postRepository.findAll();
    }
}