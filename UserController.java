package com.example.demo.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/profile")
public class UserController {
    @Autowired
    private UserService userService;
//profile.html,shopping.html
    @GetMapping("/{email}")
    public ResponseEntity<Optional<User>> getProfile(@PathVariable String email) {
        Optional<User> user = userService.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
