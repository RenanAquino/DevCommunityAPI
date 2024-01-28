package com.devcommunity.community.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devcommunity.community.dto.PostDTO;
import com.devcommunity.community.entities.Post;
import com.devcommunity.community.exceptions.post.PostNotFoundException;
import com.devcommunity.community.repository.PostRepository;
import com.devcommunity.community.services.func.FunctionsAux;


@Service
public class PostService {
    
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findById(String id) {
        return postRepository.findById(id)
            .orElseThrow(() -> new PostNotFoundException("Post not found"));
    }

    @Transactional
    public Post create(@Validated PostDTO data) {
        Post post = new Post(data.title(), data.content());
        return postRepository.save(post);
    }

    @Transactional
    public Post update(String id, PostDTO data) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new PostNotFoundException("User not found"));
        
        String currentUsername = FunctionsAux.getCurrentUsername();
        if (!post.getAuthor().equals(currentUsername)) {
            throw new AccessDeniedException("Access denied");
        }

        post.setTitle(data.title());
        post.setContent(data.content());
        return postRepository.save(post);
    }


    @Transactional
    public void delete(String id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new PostNotFoundException("User not found"));

        String currentUsername = FunctionsAux.getCurrentUsername();
        if (!post.getAuthor().equals(currentUsername) || FunctionsAux.isAdmin()) {
            throw new AccessDeniedException("Access denied");
        }

        postRepository.deleteById(id);
    }
}
