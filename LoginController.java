package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private PatientRepository userRepository;
//login.html
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        // Fetch user by email
        Optional<Patient> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        Patient user = userOptional.get();

       
        Map<String, String> response = new HashMap<>();
        response.put("email", loginData.get("email"));
        return ResponseEntity.ok(response);
        
    }
}
