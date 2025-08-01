package com.example.QuizApp.Service;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Model.QuestionWrapper;
import com.example.QuizApp.Model.Quiz;
import com.example.QuizApp.Model.Response;
import com.example.QuizApp.dao.QuestionDao;
import com.example.QuizApp.dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        try {
            List<Question> questions = questionDao.findRandomQuestionsByLanguage(category)
                .stream()
                .limit(numQ)
                .toList();
            Quiz lastQuiz = quizDao.findTopByOrderByQuizNoDesc();
            int newQuizNo = lastQuiz != null ? lastQuiz.getQuizNo() + 1 : 1;

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quiz.setQuizNo(newQuizNo);

            quizDao.save(quiz);

            return new ResponseEntity<>("Quiz created with QuizNo: " + newQuizNo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating quiz: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getQuiz(int quizNo) {
        Optional<Quiz> quiz = quizDao.findByQuizNo(quizNo);
        if (quiz.isPresent()) {
            List<Question> questions = quiz.get().getQuestions();
            List<QuestionWrapper> wrappedQuestions = new ArrayList<>();
            int i=0;
            for (Question question : questions) {
                wrappedQuestions.add(new QuestionWrapper(
                        ++i,
                        question.getQuestion(),
                        question.getOptionA(),
                        question.getOptionB(),
                        question.getOptionC(),
                        question.getOptionD()
                ));
            }
            return new ResponseEntity<>(wrappedQuestions, HttpStatus.OK);
        }
        return new ResponseEntity<>("Quiz not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> submitQuiz(int quizNo, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findByQuizNo(quizNo);
        if (quiz.isPresent()) {
            List<Question> questions = quiz.get().getQuestions();
            int correctAnswers = 0;

            for (int i = 0; i < questions.size(); i++) {
                if (i < responses.size() &&
                        questions.get(i).getCorrectAnswer()
                                .equalsIgnoreCase(responses.get(i).getResponse())) {
                    correctAnswers++;
                }
            }

            return new ResponseEntity<>("Score: " + correctAnswers + "/" + questions.size(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Quiz not found", HttpStatus.NOT_FOUND);
    }
}
