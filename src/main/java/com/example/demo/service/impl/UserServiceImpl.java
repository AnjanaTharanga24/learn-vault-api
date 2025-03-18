package com.example.demo.service.impl;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setUsername(userRequest.getUsername());

        userRepository.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        return null;
    }
}
