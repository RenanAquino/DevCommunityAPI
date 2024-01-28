package com.devcommunity.community.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.devcommunity.community.dto.PostDTO;
import com.devcommunity.community.entities.Post;
import com.devcommunity.community.entities.User;
import com.devcommunity.community.repository.PostRepository;
import com.devcommunity.community.role.UserRole;

@SpringBootTest
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    public void setUp() {
        // Simula um usuário autenticado
        User user = new User("renan", "123456", UserRole.USER);
         Authentication auth = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testCreate() {
        PostDTO postDTO = new PostDTO("Test title", "Test content");
        Post post = new Post(postDTO.title(), postDTO.content());

        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post createdPost = postService.create(postDTO);

        assertEquals(postDTO.title(), createdPost.getTitle());
        assertEquals(postDTO.content(), createdPost.getContent());

        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    public void testCreateThrowsException() {
        PostDTO postDTO = new PostDTO("Test title", "Test content");

        when(postRepository.save(any(Post.class))).thenThrow(new RuntimeException("Error"));

        assertThrows(RuntimeException.class, () -> {
            postService.create(postDTO);
        });
    }

    @AfterEach
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }
}

