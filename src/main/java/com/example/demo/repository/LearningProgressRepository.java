package com.example.demo.repository;

import com.example.demo.model.LearningProgress;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearningProgressRepository extends MongoRepository<LearningProgress , String> {
}
