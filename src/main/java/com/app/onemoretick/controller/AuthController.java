package com.app.onemoretick.controller;

import com.app.onemoretick.model.dto.UserDto;
import com.app.onemoretick.model.entity.User;
import com.app.onemoretick.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userRequest) {
        User savedUser = userService.addUser(modelMapper.map(userRequest, User.class));
        if (savedUser != null)
            return new ResponseEntity<>(modelMapper.map(savedUser, UserDto.class), HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.IM_USED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@Valid @RequestBody UserDto userRequest) {
        User loggedUser = userService.logUser(modelMapper.map(userRequest, User.class));
        if (loggedUser != null)
            return new ResponseEntity<>(modelMapper.map(loggedUser, UserDto.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/change_pass")
    public ResponseEntity<UserDto> changePassword(@Valid @RequestBody UserDto userRequest) {
        User updatedUser = userService.updateUser(modelMapper.map(userRequest, User.class));
        if (updatedUser != null)
            return new ResponseEntity<>(modelMapper.map(updatedUser, UserDto.class), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
