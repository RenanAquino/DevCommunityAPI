package com.devcommunity.community.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcommunity.community.dto.CommentDTO;
import com.devcommunity.community.entities.Comment;
import com.devcommunity.community.services.CommentService;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> findAll() {
        List<Comment> list = commentService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> findById(@PathVariable String id) {
        Comment obj = commentService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable String postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok().body(comments);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Comment> update(@PathVariable String id, @RequestBody CommentDTO data) {
        Comment updatedComment = commentService.update(id, data);
        return ResponseEntity.ok().body(updatedComment);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        commentService.delete(id);
        return ResponseEntity.ok("Delete successful");
    }
}
