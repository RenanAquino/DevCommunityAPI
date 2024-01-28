package com.devcommunity.community.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.devcommunity.community.services.func.FunctionsAux;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "posts")
public class Post {
    @Id
    private String id;

    @NotNull
    @Size(min = 1, max = 255)
    private String title;

    @NotNull
    @Size(min = 1, max = 2048)
    private String content;

    private String author;
    private LocalDateTime date;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.author = FunctionsAux.getCurrentUsername();
        this.date = LocalDateTime.now();
    }
}
