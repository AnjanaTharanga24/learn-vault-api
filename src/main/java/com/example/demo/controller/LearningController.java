package com.example.demo.controller;

import com.example.demo.dto.request.LearningProgressRequest;
import com.example.demo.dto.response.LearningProgressResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.LearningProgressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/learning")
public class LearningController {

    private LearningProgressService learningProgressService;

    @PostMapping("/progresses")
    public LearningProgressResponse createLearningProgressPost(@RequestBody LearningProgressRequest learningProgressRequest) throws NotFoundException {
        return learningProgressService.createLearningProgress(learningProgressRequest);
    }

    @GetMapping("/progresses/{user-id}")
    public LearningProgressResponse getLearningProgressByUser(@PathVariable("user-id") String userId) throws NotFoundException{
        return learningProgressService.getLearningProgressByUserId(userId);
    }
}
