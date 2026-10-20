package com.socialfeed.feed_engine.repository;

import com.socialfeed.feed_engine.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}