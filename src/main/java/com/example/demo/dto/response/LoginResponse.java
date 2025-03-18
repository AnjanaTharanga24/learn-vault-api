package com.example.demo.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String id;
    private String name;
    private String email;
    private String password;
    private String username;
}
