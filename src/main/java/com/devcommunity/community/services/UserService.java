package com.devcommunity.community.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcommunity.community.dto.UpdateUserDTO;
import com.devcommunity.community.entities.User;
import com.devcommunity.community.exceptions.user.UserNotFoundException;
import com.devcommunity.community.repository.UserRepository;
import com.devcommunity.community.services.func.FunctionsAux;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public User update(String id, UpdateUserDTO data) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        String currentUserId = FunctionsAux.getCurrentUserId();
        if (!user.getId().equals(currentUserId)) {
            throw new AccessDeniedException("Access denied");
        }
        user.setUsername(data.username());
        return userRepository.save(user);
    }


    @Transactional
    public void delete(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
            
        String currentUserId = FunctionsAux.getCurrentUserId();
        if (!user.getId().equals(currentUserId)) {
            throw new AccessDeniedException("Access denied");
        }
        userRepository.deleteById(id);
    }
}
