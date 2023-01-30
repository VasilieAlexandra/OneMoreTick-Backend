package com.app.onemoretick.controller;

import com.app.onemoretick.model.entity.User;
import com.app.onemoretick.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User savedUser = userService.addUser(user);
        if (savedUser != null)
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.IM_USED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loggedUser = userService.logUser(user);
        if (loggedUser != null)
            return new ResponseEntity<>(loggedUser, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/change_pass")
    public ResponseEntity<User> changePassword(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser != null)
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
