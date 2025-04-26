package com.example.demo.repository;

import com.example.demo.model.LearningProgress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface LearningProgressRepository extends MongoRepository<LearningProgress , String> {

   List<LearningProgress> findLearningProgressByUserId(String userId);

   // Find distinct userIds where the last progress date is after the given date
   List<LearningProgress> findByDateAfter(LocalDate date);
}
