package com.example.demo.service;

import com.example.demo.dto.request.PostRequest;
import com.example.demo.dto.response.PostResponse;

public interface PostService {

    PostResponse createPost(PostRequest postRequest);

    String deletePost(String postId,String userId);
}
