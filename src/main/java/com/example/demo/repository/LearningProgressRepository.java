package com.example.demo.repository;

import com.example.demo.model.LearningProgress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LearningProgressRepository extends MongoRepository<LearningProgress , String> {

    Optional<LearningProgress> findLearningProgressByUserId(String userId);
}
