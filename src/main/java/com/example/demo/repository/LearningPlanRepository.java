package com.example.demo.repository;

import com.example.demo.model.LearningPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LearningPlanRepository extends MongoRepository<LearningPlan,String> {

    List<LearningPlan> findAllByUserId(String userId);
}
