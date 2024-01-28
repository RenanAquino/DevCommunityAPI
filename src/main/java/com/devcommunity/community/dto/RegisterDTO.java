package com.devcommunity.community.dto;

import com.devcommunity.community.role.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
    
}
