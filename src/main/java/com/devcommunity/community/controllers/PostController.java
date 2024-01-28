package com.devcommunity.community.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcommunity.community.dto.CommentDTO;
import com.devcommunity.community.dto.PostDTO;
import com.devcommunity.community.entities.Comment;
import com.devcommunity.community.entities.Post;
import com.devcommunity.community.services.CommentService;
import com.devcommunity.community.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post obj = postService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Post> create(@Valid @RequestBody PostDTO data) {
        Post post = postService.create(data);
        return ResponseEntity.ok().body(post);
    }

    @PostMapping(value = "/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable String postId, @RequestBody CommentDTO data) {
        Comment comment = commentService.create(postId, data);
        return ResponseEntity.ok().body(comment);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody PostDTO data) {
        Post updatedpost = postService.update(id, data);
        return ResponseEntity.ok().body(updatedpost);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.ok("Delete successful");
    }
}
