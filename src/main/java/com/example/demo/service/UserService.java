package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.exception.NotFoundException;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest) throws AllReadyExistsException;
    UserResponse loginOrRegisterOAuthUser(String name, String email, String imgUrl, String providerId) throws AllReadyExistsException;
    List<UserResponse> getAllUsers();
    UserResponse updateUser(String userId, UserRequest userRequest) throws NotFoundException, AllReadyExistsException;
    void deleteUser(String userId) throws NotFoundException;
    LoginResponse loginUser(LoginRequest loginRequest) throws NotFoundException;
}
