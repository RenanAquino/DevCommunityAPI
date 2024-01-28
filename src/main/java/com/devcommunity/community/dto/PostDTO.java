package com.devcommunity.community.dto;

import jakarta.validation.constraints.NotNull;

public record PostDTO(@NotNull String title, @NotNull String content) {
    
}
