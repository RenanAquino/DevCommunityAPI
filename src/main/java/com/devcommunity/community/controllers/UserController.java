package com.devcommunity.community.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcommunity.community.dto.UpdateUserDTO;
import com.devcommunity.community.entities.User;
import com.devcommunity.community.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody UpdateUserDTO data) {
        User updatedUser = userService.update(id, data);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok("Delete successful");
    }
}
