package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String id;
    private String name;
    private String email;
    private String username;
    private String token;
    private String userType;
    private Integer followerCount;
    private Integer followingCount;
}
