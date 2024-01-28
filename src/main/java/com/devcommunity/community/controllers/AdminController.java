package com.devcommunity.community.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcommunity.community.entities.User;
import com.devcommunity.community.services.AdminService;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = adminService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        User obj = adminService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
