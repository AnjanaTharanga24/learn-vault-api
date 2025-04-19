package com.example.demo.service.impl;

import com.example.demo.dto.request.LearningPlanRequest;
import com.example.demo.dto.response.LearningPlanResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.LearningPlan;
import com.example.demo.model.User;
import com.example.demo.repository.LearningPlanRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LearningPlanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LearningPlanServiceImpl implements LearningPlanService {

    private LearningPlanRepository learningPlanRepository;
    private UserRepository userRepository;

    @Override
    public LearningPlanResponse createLearningPlan(LearningPlanRequest learningPlanRequest) throws NotFoundException {

        Optional<User> userOptional = userRepository.findById(learningPlanRequest.getUserId());

        if (!userOptional.isPresent()){
            throw new NotFoundException("user not found with id : " + learningPlanRequest.getUserId());
        }

        LearningPlan learningPlan = new LearningPlan();

        learningPlan.setUserId(learningPlanRequest.getUserId());
        learningPlan.setTitle(learningPlanRequest.getTitle());
        learningPlan.setTopics(learningPlanRequest.getTopics());
        learningPlan.setResources(learningPlanRequest.getResources());
        learningPlan.setStartDate(learningPlanRequest.getStartDate());
        learningPlan.setEndDate(learningPlanRequest.getEndDate());
        learningPlan.setStatus(learningPlanRequest.getStatus());

        LearningPlan createdLearningPlan = learningPlanRepository.save(learningPlan);

        return LearningPlanResponse.builder()
                .id(createdLearningPlan.getId())
                .userId(createdLearningPlan.getUserId())
                .title(createdLearningPlan.getTitle())
                .topics(createdLearningPlan.getTopics())
                .resources(createdLearningPlan.getResources())
                .startDate(createdLearningPlan.getStartDate())
                .endDate(createdLearningPlan.getEndDate())
                .status(createdLearningPlan.getStatus())
                .build();
    }
}
