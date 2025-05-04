package com.example.demo.controller;

import com.example.demo.dto.request.*;
import com.example.demo.dto.response.CommentResponse;
import com.example.demo.dto.response.LearningPlanResponse;
import com.example.demo.dto.response.LearningProgressResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.LearningPlan;
import com.example.demo.model.LearningProgress;
import com.example.demo.model.User;
import com.example.demo.service.LearningPlanService;
import com.example.demo.service.LearningProgressService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/learning")
@CrossOrigin("http://localhost:3000/")
public class LearningController {

    private LearningProgressService learningProgressService;
    private LearningPlanService learningPlanService;

    //learning progress
    //TODO : have to add predefine template
    @PostMapping("/progresses")
    public LearningProgressResponse createLearningProgressPost(@RequestBody LearningProgressRequest learningProgressRequest) throws NotFoundException {
        return learningProgressService.createLearningProgress(learningProgressRequest);
    }

    @GetMapping("/progresses/{user-id}")
    public List<LearningProgressResponse> getLearningProgressByUser(@PathVariable("user-id") String userId) throws NotFoundException{
        return learningProgressService.getLearningProgressByUserId(userId);
    }

    @PutMapping("/progresses/{post-id}")
    public LearningProgressResponse updateLearningProgressPost(@PathVariable("post-id") String postId , @RequestBody UpdateLearningProgressRequest updateLearningProgressRequest) throws NotFoundException{
        return learningProgressService.updateLearningProgress(postId,updateLearningProgressRequest);
    }

    @DeleteMapping("/progresses/{post-id}")
    public String deletePostById(@PathVariable("post-id") String postId)throws NotFoundException{
        return learningProgressService.deleteLearningProgress(postId);
    }

    @GetMapping("/feed")
    public List<LearningProgress> getAllLearningProgress(){
        return learningProgressService.getAllLEarningProgress();
    }


    //learning plan

    @PostMapping("/plans")
    public LearningPlanResponse createLearningPlan(@RequestBody LearningPlanRequest learningPlanRequest) throws NotFoundException{
        return learningPlanService.createLearningPlan(learningPlanRequest);
    }

    @PutMapping("/plans")
    public LearningPlanResponse updateLearningPlanStatus(@RequestBody LearningPlanStatusUpdateRequest learningPlanStatusUpdateRequest) throws NotFoundException{
        return learningPlanService.updateLearningPlanStatus(learningPlanStatusUpdateRequest);
    }

    @PutMapping("/plans/{post-id}")
    public LearningPlanResponse updateLearningPlan(@PathVariable("post-id") String postId ,@RequestBody UpdateLearningPlanRequest updateLearningPlanRequest) throws NotFoundException{
        return learningPlanService.updateLearningPlan(updateLearningPlanRequest , postId);
    }

    @GetMapping("/plans/{user-id}")
    public List<LearningPlanResponse> getAllLearningPlansByUserId(@PathVariable("user-id")String userId)throws NotFoundException{
        return learningPlanService.getAllLearningPlanByUserId(userId);
    }

    @DeleteMapping("/plans/{post-id}")
    public String deleteLearningPlan(@PathVariable("post-id")String postId) throws NotFoundException{
        return learningPlanService.deleteLearningPlanById(postId);
    }

    @GetMapping("/plan/feed")
    public List<LearningPlan> getAllLearningPlans(){
        return learningPlanService.getAllLearningPlan();
    }

    //comments
    @PostMapping("/comment")
    public String addComment(@RequestParam String id,@RequestBody CommentRequest commentRequest) throws NotFoundException {
        return learningPlanService.addComment(id,commentRequest);
    }

//    @PutMapping("/comment")
//    public CommentResponse updateComment(@RequestParam String postId, @RequestParam String userId, @RequestParam String commentId, @RequestBody CommentRequest commentRequest) throws NotFoundException {
//        return postService.updateComment(postId,userId,commentId,commentRequest);
//    }
//
//    @DeleteMapping("/comment")
//    public ResponseEntity<Void> deleteComment(@RequestParam String postId, @RequestParam String commentId, @RequestParam String userId) {
//        postService.deleteComment(postId, commentId, userId);
//        return ResponseEntity.noContent().build();
//    }

}
