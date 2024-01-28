package com.devcommunity.community.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.devcommunity.community.entities.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByIdPost(String idPost);
}
