package com.devcommunity.community.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devcommunity.community.entities.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    
}
