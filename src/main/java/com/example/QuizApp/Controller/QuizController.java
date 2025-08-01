package com.example.QuizApp.Controller;

import com.example.QuizApp.Model.Response;
import com.example.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService qs;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestParam String category, @RequestParam int numQ, String title) {
        //return new ResponseEntity<>("success", HttpStatus.CREATED);
        return qs.createQuiz(category,numQ,title);
    }
    @GetMapping("/getQuiz/{no}")
    public ResponseEntity<?> getQuiz(@PathVariable int no) {
        return qs.getQuiz(no);
    }
    @PostMapping("/submit/{no}")
    public ResponseEntity<?> submitQuiz(@PathVariable int no, @RequestBody List<Response> responses) {
        return qs.submitQuiz(no, responses);
    }

}
