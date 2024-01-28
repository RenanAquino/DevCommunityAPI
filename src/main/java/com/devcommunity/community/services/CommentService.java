package com.devcommunity.community.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devcommunity.community.dto.CommentDTO;
import com.devcommunity.community.entities.Comment;
import com.devcommunity.community.entities.Post;
import com.devcommunity.community.exceptions.comment.CommentNotFoundException;
import com.devcommunity.community.repository.CommentRepository;
import com.devcommunity.community.repository.PostRepository;
import com.devcommunity.community.services.func.FunctionsAux;

@Service
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Comment findById(String id) {
        Optional<Comment> obj =  commentRepository.findById(id);
        return obj.orElseThrow(() -> new CommentNotFoundException("Comment not found"));
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsByPostId(String postId) {
        return commentRepository.findByIdPost(postId);
    }

    @Transactional
    public Comment create(String postId, @Validated CommentDTO data) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new CommentNotFoundException("Post not found"));
        Comment comment = new Comment(data.content(), post.getId());
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment update(String id, CommentDTO data) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new CommentNotFoundException("Comment not found"));
        
        String currentUsername = FunctionsAux.getCurrentUsername();
        if (!comment.getAuthor().equals(currentUsername)) {
            throw new AccessDeniedException("Access denied");
        }

        comment.setContent(data.content());
        return commentRepository.save(comment);
    }

    @Transactional
    public void delete(String id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new CommentNotFoundException("User not found"));

        String currentUsername = FunctionsAux.getCurrentUsername();
        if (!comment.getAuthor().equals(currentUsername) || FunctionsAux.isAdmin()) {
            throw new AccessDeniedException("Access denied");
        }

        commentRepository.deleteById(id);
    }
}
