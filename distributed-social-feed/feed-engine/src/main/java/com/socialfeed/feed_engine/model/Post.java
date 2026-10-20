package com.socialfeed.feed_engine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "posts")
public class Post {
    
    @Id
    private String id;
    private String userId;
    private String content;
    private Instant timestamp;

    public Post() {
        // Default constructor required by Spring Data and Jackson JSON parsing
    }

    public Post(String userId, String content) {
        this.userId = userId;
        this.content = content;
        this.timestamp = Instant.now();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}