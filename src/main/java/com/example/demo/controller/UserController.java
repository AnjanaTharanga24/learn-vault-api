package com.example.demo.controller;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest userRequest) throws AllReadyExistsException {
       return userService.registerUser(userRequest);
    }

    @GetMapping("/all-users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) throws NotFoundException, AllReadyExistsException {
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable String userId) throws NotFoundException {
        userService.deleteUser(userId);
    }

    @PostMapping("/login")
    public LoginResponse loginResponse(@RequestBody LoginRequest loginRequest) throws NotFoundException {
        return userService.loginUser(loginRequest);
    }

}
