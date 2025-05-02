package com.example.demo.controller;

import com.example.demo.dto.request.CommentRequest;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.dto.response.FollowResponse;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.FollowService;
import com.example.demo.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class InteractivityController {

    private final FollowService followService;
    private final PostService postService;

    @PatchMapping("/follow/{followerId}/follow/{targetUserId}")
    public ResponseEntity<String> addFollower(@PathVariable String followerId, @PathVariable String targetUserId) throws NotFoundException, AllReadyExistsException {
        followService.followUser(followerId, targetUserId);
        return ResponseEntity.ok("Successfully added follower");
    }

    @PatchMapping("/follow/{followerId}/unfollow/{targetUserId}")
    public ResponseEntity<String> removeFollower(@PathVariable String followerId, @PathVariable String targetUserId) throws NotFoundException {
        followService.unfollowUser(followerId, targetUserId);
        return ResponseEntity.ok("Successfully removed follower");
    }

    @GetMapping("/follow/followers/{userId}")
    public List<FollowResponse> allFollowers(@PathVariable String userId) throws NotFoundException {
        return followService.getAllFollowers(userId);
    }

    @GetMapping("/follow/following/{userId}")
    public List<FollowResponse> allFollowing(@PathVariable String userId) throws NotFoundException {
        return followService.getAllFollowing(userId);
    }

    //comments
    @PostMapping("/comment")
    public String addComment(@RequestParam String postId,@RequestBody CommentRequest commentRequest) throws NotFoundException {
        return postService.addComment(postId,commentRequest);
    }

    @PutMapping("/comment")
    public CommentResponse updateComment(@RequestParam String postId, @RequestParam String userId, @RequestParam String commentId, @RequestBody CommentRequest commentRequest) throws NotFoundException {
        return postService.updateComment(postId,userId,commentId,commentRequest);
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Void> deleteComment(@RequestParam String postId,@RequestParam String commentId,@RequestParam String userId) {
        postService.deleteComment(postId, commentId, userId);
        return ResponseEntity.noContent().build();
    }
}
