package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.exception.NotFoundException;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest) throws AllReadyExistsException;
    LoginResponse loginUser(LoginRequest loginRequest) throws NotFoundException;
}
