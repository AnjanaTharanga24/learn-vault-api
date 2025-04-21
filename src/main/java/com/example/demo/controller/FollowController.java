package com.example.demo.controller;

import com.example.demo.dto.response.FollowResponse;
import com.example.demo.exception.AllReadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/follow")
public class FollowController {

    private final FollowService followService;

    @PatchMapping("/{followerId}/follow/{targetUserId}")
    public ResponseEntity<String> addFollower(@PathVariable String followerId, @PathVariable String targetUserId) throws NotFoundException, AllReadyExistsException {
        followService.followUser(followerId, targetUserId);
        return ResponseEntity.ok("Successfully added follower");
    }

    @PatchMapping("/{followerId}/unfollow/{targetUserId}")
    public ResponseEntity<String> removeFollower(@PathVariable String followerId, @PathVariable String targetUserId) throws NotFoundException {
        followService.unfollowUser(followerId, targetUserId);
        return ResponseEntity.ok("Successfully removed follower");
    }

    @GetMapping("/followers/{userId}")
    public List<FollowResponse> allFollowers(@PathVariable String userId) throws NotFoundException {
        return followService.getAllFollowers(userId);
    }

    @GetMapping("/following/{userId}")
    public List<FollowResponse> allFollowing(@PathVariable String userId) throws NotFoundException {
        return followService.getAllFollowing(userId);
    }
}
