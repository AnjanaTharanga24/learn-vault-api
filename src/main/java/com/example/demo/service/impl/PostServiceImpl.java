package com.example.demo.service.impl;

import com.example.demo.dto.request.PostRequest;
import com.example.demo.dto.response.PostResponse;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;



    @Override
    public PostResponse createPost(PostRequest postRequest) {
        // Check if imageUrls contains more than 3 strings and throw an error if so.
        if (postRequest.getImageUrls() != null && postRequest.getImageUrls().size() > 3) {
            throw new IllegalArgumentException("Maximum allowed image URLs is 3.");
        }

        // Create a new Post instance and set its fields from the PostRequest DTO
        Post post = new Post();
        post.setDescription(postRequest.getDescription());
        post.setVideoUrl(postRequest.getVideoUrl());
        post.setPostDate(new Date());

        // Since we've validated that the list is not larger than 3, we can set it directly
        if (postRequest.getImageUrls() != null) {
            post.setImageUrls(new ArrayList<>(postRequest.getImageUrls()));
        }

        // Retrieve the user from the repository based on the user ID provided in the request
        User user = userRepository.findById(postRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + postRequest.getUserId()));
        post.setUser(user);

        // Save the post in the database
        postRepository.save(post);


        return PostResponse.builder()
                .postId(post.getPostId())
                .description(post.getDescription())
                .imageUrls(post.getImageUrls())
                .username(user.getUsername())
                .videoUrl(post.getVideoUrl())
                .build();
    }

    @Override
    public String deletePost(String postId, String userId) {
        // Find the post by its ID or throw an exception if not found
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        // Check if the requesting user is the creator of the post
        // This assumes your User class has a getUserId() method.
        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this post.");
        }

        // Delete the post
        postRepository.deleteById(postId);
        return "Post deleted successfully";
    }

    @Override
    public PostResponse updatePost(String postId, String userId,PostRequest updateRequest ) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

        // Verify that the requester is the creator of the post
        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not authorized to update this post.");
        }

        // Validate image URLs: if more than 3 URLs provided, throw an exception
        if (updateRequest.getImageUrls() != null && updateRequest.getImageUrls().size() > 3) {
            throw new IllegalArgumentException("Maximum allowed image URLs is 3.");
        }

        // Update the post fields using the values from postRequest
        post.setDescription(updateRequest.getDescription());
        post.setVideoUrl(updateRequest.getVideoUrl());
        if (updateRequest.getImageUrls() != null) {
            post.setImageUrls(new ArrayList<>(updateRequest.getImageUrls()));
        }

        // Save the updated post
        postRepository.save(post);

        return PostResponse.builder()
                .postId(post.getPostId())
                .postDate(post.getPostDate())
                .description(post.getDescription())
                .imageUrls(post.getImageUrls())
                .videoUrl(post.getVideoUrl())
                .build();
    }
}
