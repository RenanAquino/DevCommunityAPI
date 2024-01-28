package com.devcommunity.community.dto;

import jakarta.validation.constraints.NotNull;

public record CommentDTO(@NotNull String content) {
    
}
