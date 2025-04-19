package com.example.demo.service;

import com.example.demo.dto.request.LearningPlanRequest;
import com.example.demo.dto.response.LearningPlanResponse;
import com.example.demo.exception.NotFoundException;

public interface LearningPlanService {

    LearningPlanResponse createLearningPlan(LearningPlanRequest learningPlanRequest) throws NotFoundException;
}
