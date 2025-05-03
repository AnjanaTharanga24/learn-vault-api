package com.example.demo.controller;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.OAuthUserRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.MotivationMessageResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
//import com.example.demo.service.AiService;


import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
//import com.example.demo.service.AiService;

import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("http://localhost:3000/")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
//    private final AiService aiService;

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
    public ResponseEntity<String> deleteUser(@PathVariable String userId) throws NotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully !!");
    }

    @PostMapping("/login")
    public LoginResponse loginResponse(@RequestBody LoginRequest loginRequest) throws NotFoundException {
        return userService.loginUser(loginRequest);
    }


//    @GetMapping("/motivation")
//    public MotivationMessageResponse getMotivation() {
//        return aiService.getMotivationMessage();
//    }


    @PostMapping("/login-or-signup-by-oauth")
    public LoginResponse loginOrRegisterByOAuth(@RequestBody OAuthUserRequest oAuthUserRequest) {
        return userService.loginOrRegisterOAuthUser(oAuthUserRequest);

    }

    @GetMapping("/{user-id}")
    public User getUserById(@PathVariable("user-id") String userId){
        return userService.getUserById(userId);

    }

}
