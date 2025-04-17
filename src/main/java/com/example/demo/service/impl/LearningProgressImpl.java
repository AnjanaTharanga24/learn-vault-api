package com.example.demo.service.impl;

import com.example.demo.dto.request.LearningProgressRequest;
import com.example.demo.dto.request.UpdateLearningProgressRequest;
import com.example.demo.dto.response.LearningProgressResponse;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.LearningProgress;
import com.example.demo.model.User;
import com.example.demo.repository.LearningProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LearningProgressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        learningProgress.setDate(LocalDate.now());

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

    @Override
    public LearningProgressResponse getLearningProgressByUserId(String userId) throws NotFoundException {

        Optional<LearningProgress> optionalLearningProgress = learningProgressRepository
        .findLearningProgressByUserId(userId);

        if (!optionalLearningProgress.isPresent()){
            throw new NotFoundException("learning progress notfound with user id : " + userId);
        }

        LearningProgress foundLearningProgress = optionalLearningProgress.get();

        return LearningProgressResponse.builder()
                .userId(foundLearningProgress.getUserId())
                .skill(foundLearningProgress.getSkill())
                .level(foundLearningProgress.getLevel())
                .description(foundLearningProgress.getDescription())
                .date(foundLearningProgress.getDate())
                .build();
    }

    @Override
    public LearningProgressResponse updateLearningProgress(String postId, UpdateLearningProgressRequest updateLearningProgressRequest) throws NotFoundException {

        Optional<LearningProgress> optionalLearningProgress = learningProgressRepository.findById(postId);

        if (!optionalLearningProgress.isPresent()){
            throw new NotFoundException("post not found with id : " + postId);
        }

        LearningProgress foundLearningProgress = optionalLearningProgress.get();

        foundLearningProgress.setSkill(updateLearningProgressRequest.getSkill());
        foundLearningProgress.setLevel(updateLearningProgressRequest.getLevel());
        foundLearningProgress.setDescription(updateLearningProgressRequest.getDescription());
        foundLearningProgress.setDate(LocalDate.now());

        LearningProgress updatedLearningProgress = learningProgressRepository.save(foundLearningProgress);

        return LearningProgressResponse.builder()
                .id(foundLearningProgress.getId())
                .userId(foundLearningProgress.getUserId())
                .skill(updatedLearningProgress.getSkill())
                .level(updatedLearningProgress.getLevel())
                .description(updatedLearningProgress.getDescription())
                .date(updatedLearningProgress.getDate())
                .build();
    }
}
