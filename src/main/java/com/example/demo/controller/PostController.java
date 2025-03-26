package com.example.demo.controller;

import com.example.demo.dto.request.PostRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.PostResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.PostService;
import com.mongodb.internal.bulk.DeleteRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/")
    public PostResponse createPost(@RequestBody PostRequest postRequest) throws AllReadyExistsException {
        return postService.createPost(postRequest);
    }

    @DeleteMapping("/")
    public String deletePost(@RequestParam String postId, String userId) throws NotFoundException {
        return postService.deletePost(postId,userId);
    }

    @PutMapping("/")
    public PostResponse updatePost(@RequestParam String postId,String userId,@RequestBody PostRequest postRequest) throws NotFoundException {
        return postService.updatePost(postId,userId,postRequest);
    }
}
