package com.spring.shorty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shorty.dto.ApiResponse;
import com.spring.shorty.dto.UserDTO;
import com.spring.shorty.entities.UserEntity;
import com.spring.shorty.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) throws Exception {
    	return ResponseEntity.ok(userService.getUser(id));
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDTO user) throws Exception{
    	return ResponseEntity.ok(userService.saveUser(user));
    }
}