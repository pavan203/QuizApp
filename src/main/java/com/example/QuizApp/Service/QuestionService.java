package com.example.QuizApp.Service;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionService {
    @Autowired
    QuestionDao qd;

    //@Cacheable(value = "questions", key = "'allQuestions'")
    public List<Question>getAllQuestions(){
        System.out.println("Fetching from DataBase - not from cache");

        return qd.findAll();

    }

    public List<Question> getQuestionsByCategory(String lang) {
        return qd.findAllByLanguageIgnoreCase(lang);
    }

    //@CacheEvict(value = "questions", key = "'allQuestions'")
    public void addQuestion(Question question) {
        System.out.println("Received qNo: " + question.getQNo());
        qd.save(question);
    }


}
