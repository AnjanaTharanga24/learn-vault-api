package com.example.demo.dto.request;

import lombok.Data;

@Data
public class OAuthUserRequest {

    private String name;
    private String email;
    private String imgUrl;
    private String providerId;

}
