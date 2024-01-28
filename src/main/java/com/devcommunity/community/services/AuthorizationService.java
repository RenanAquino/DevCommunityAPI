package com.devcommunity.community.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.devcommunity.community.exceptions.user.UserNotFoundException;
import com.devcommunity.community.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        return repository.findByUsername(username);
    }
    
}
