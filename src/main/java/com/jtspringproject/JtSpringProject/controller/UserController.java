package com.jtspringproject.JtSpringProject.controller;

import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController // Para endpoints REST
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        boolean exists = this.userService.checkUserExists(user.getEmail());

        if (!exists) {
            String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setRole("ROLE_NORMAL");
            this.userService.addUser(user);
            return ResponseEntity.ok("New user registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already used: " + user.getUsername());
        }
    }

    @PutMapping("user")
    public ResponseEntity<String> update(@RequestBody User user) {
        boolean exists = this.userService.checkUserExists(user.getEmail());

        if (exists) {
            User currentUser = this.userService.getUserByEmail(user.getEmail());
            if (!user.getName().isEmpty()) {
                currentUser.setName(user.getName());
            }
            /*if (!user.getLastName().isEmpty()) {
                currentUser.setLastName(user.getLastName());
            }*/
            if (!user.getAddress().isEmpty()) {
                currentUser.setAddress(user.getAddress());
            }
            this.userService.addUser(currentUser);
            return ResponseEntity.ok("New user registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid user data: " + user.getEmail());
        }
    }

    @GetMapping("profile")
    public ResponseEntity<User> getUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
