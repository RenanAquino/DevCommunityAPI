package com.devcommunity.community.services.func;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.devcommunity.community.entities.User;

public class FunctionsAux {
    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        try {
            return ((User) principal).getId();
        } catch (RuntimeException e) {
            throw new IllegalStateException("Falha ao pegar o id do usúario");
        }
    }

    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        try {
            return ((User) principal).getUsername();
        } catch (RuntimeException e) {
            throw new IllegalStateException("Falha ao pegar o id do usúario");
        }
    }

    public static boolean isAdmin() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getAuthorities().stream()
           .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
}
}
