package com.socialfeed.feed_engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FeedEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedEngineApplication.class, args);
    }

}