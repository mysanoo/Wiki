package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.User;

import com.example.WikiHow.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/user")
public class UserController {


    private final UserServiceImpl userService;

    @PostMapping
    public HttpEntity<ApiResponse> create(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> userPosts(@PathVariable Integer id){
        return ResponseEntity.ok(userService.UsersPosts(id));
    }
}
