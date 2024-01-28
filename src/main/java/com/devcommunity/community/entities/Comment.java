package com.devcommunity.community.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devcommunity.community.services.func.FunctionsAux;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String content;
    private String author;
    private LocalDateTime date = LocalDateTime.now();

    @JsonIgnore
    private String idPost;

    public Comment(String content, String idPost) {
        this.content = content;
        this.author = FunctionsAux.getCurrentUsername();
        this.date = LocalDateTime.now();
        this.idPost = idPost;
    }
}
