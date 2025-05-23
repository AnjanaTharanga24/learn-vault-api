package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.OAuthUserRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest) throws AllReadyExistsException;
    LoginResponse loginOrRegisterOAuthUser(OAuthUserRequest oAuthUserRequest);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(String userId, UserRequest userRequest) throws NotFoundException, AllReadyExistsException;
    void deleteUser(String userId) throws NotFoundException;
    LoginResponse loginUser(LoginRequest loginRequest) throws NotFoundException;
    User getUserById (String userId);
}
