package com.example.demo.service.impl;

import com.example.demo.dto.request.LearningProgressRequest;
import com.example.demo.dto.response.LearningProgressResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.LearningProgress;
import com.example.demo.model.User;
import com.example.demo.repository.LearningProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LearningProgressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LearningProgressImpl implements LearningProgressService {

    private LearningProgressRepository learningProgressRepository;
    private UserRepository userRepository;

    @Override
    public LearningProgressResponse createLearningProgress(LearningProgressRequest learningProgressRequest) throws NotFoundException {

        Optional<User> userOptional = userRepository.findById(learningProgressRequest.getUserId());

        if (!userOptional.isPresent()){
            throw new NotFoundException("user not found with id : " + learningProgressRequest.getUserId());
        }

        LearningProgress learningProgress = new LearningProgress();

        learningProgress.setUserId(learningProgressRequest.getUserId());
        learningProgress.setSkill(learningProgressRequest.getSkill());
        learningProgress.setLevel(learningProgressRequest.getLevel());
        learningProgress.setDescription(learningProgressRequest.getDescription());
        learningProgress.setDate(learningProgressRequest.getDate());

        LearningProgress savedLearningProgress = learningProgressRepository.save(learningProgress);


        return LearningProgressResponse.builder()
                .id(savedLearningProgress.getId())
                .userId(savedLearningProgress.getUserId())
                .skill(savedLearningProgress.getSkill())
                .level(savedLearningProgress.getLevel())
                .description(savedLearningProgress.getDescription())
                .date(savedLearningProgress.getDate())
                .build();
    }
}
