package com.example.QuizApp.dao;

import com.example.QuizApp.Model.Quiz;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizDao extends MongoRepository<Quiz, String> {
    Optional<Quiz> findByQuizNo(int quizNo);

    // To get the last quiz number (optional helper)
    Quiz findTopByOrderByQuizNoDesc();
}
