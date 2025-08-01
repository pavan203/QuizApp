package com.example.QuizApp.dao;

import com.example.QuizApp.Model.Question;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends MongoRepository<Question, String> {

    List<Question> findAllByLanguageIgnoreCase(String lang);
    void deleteByqNo(int qNo);

    @Query(value = "{'language': {$regex : ?0, $options: 'i'}}", sort = "{ $natural: -1 }")
    List<Question> findRandomQuestionsByLanguage(String category);
}
