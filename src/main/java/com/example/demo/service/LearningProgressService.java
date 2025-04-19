package com.example.demo.service;

import com.example.demo.dto.request.LearningProgressRequest;
import com.example.demo.dto.request.UpdateLearningProgressRequest;
import com.example.demo.dto.response.LearningProgressResponse;
import com.example.demo.exception.NotFoundException;

public interface LearningProgressService {

    LearningProgressResponse createLearningProgress(LearningProgressRequest learningProgressRequest) throws NotFoundException;
    LearningProgressResponse getLearningProgressByUserId(String userId) throws NotFoundException;
    LearningProgressResponse updateLearningProgress( String postId ,UpdateLearningProgressRequest updateLearningProgressRequest) throws NotFoundException;
    String deleteLearningProgress(String postId) throws NotFoundException;

}
