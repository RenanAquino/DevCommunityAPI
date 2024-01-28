package com.devcommunity.community.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.devcommunity.community.entities.User;

public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByUsername(String username);
}
