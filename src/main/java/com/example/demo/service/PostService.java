package com.example.demo.service;

import com.example.demo.dto.request.CommentRequest;
import com.example.demo.dto.request.PostRequest;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.dto.response.PostResponse;

public interface PostService {

    PostResponse createPost(PostRequest postRequest);

    String deletePost(String postId,String userId);

    PostResponse updatePost(String postId, String userId,PostRequest updateRequest);

    String addComment(String postId,CommentRequest commentRequest);

}
