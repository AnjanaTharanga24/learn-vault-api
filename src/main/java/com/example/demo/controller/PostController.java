package com.example.demo.controller;

import com.example.demo.dto.request.PostRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.PostResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public PostResponse register(@RequestBody PostRequest postRequest) throws AllReadyExistsException {
        return postService.createPost(postRequest);
    }

}
