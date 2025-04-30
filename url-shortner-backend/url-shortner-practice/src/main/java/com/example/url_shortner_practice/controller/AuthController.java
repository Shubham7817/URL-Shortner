package com.example.url_shortner_practice.controller;


import com.example.url_shortner_practice.dtos.LoginRequest;
import com.example.url_shortner_practice.dtos.RegisterRequest;
import com.example.url_shortner_practice.model.User;
import com.example.url_shortner_practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/public/login")
    public ResponseEntity<?> LoginUser(@RequestBody LoginRequest loginrequest){
        return ResponseEntity.ok(userService.authenticateUser(loginrequest));
    }

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setRole("ROLE_USER");

        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
