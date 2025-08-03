package com.example.QuizApp.Controller;

import com.example.QuizApp.Model.Question;
import com.example.QuizApp.Service.QuestionService;
import com.example.QuizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
	@Autowired
	QuestionService qs;


	@GetMapping("/AllQuestions")
	public ResponseEntity<List<Question>> AllQuestions() {
		return new ResponseEntity<>(qs.getAllQuestions(), HttpStatus.OK);

	}

	@GetMapping("/{lang}")
	public ResponseEntity<List<Question>> QuestionsByCategory(@PathVariable String lang) {
		return new ResponseEntity<>(qs.getQuestionsByCategory(lang), HttpStatus.OK);
	}
	@PostMapping("/add")
	public ResponseEntity<?> addQuestion(@RequestBody Question question) {
		try{qs.addQuestion(question);
		return new ResponseEntity<>(HttpStatus.CREATED);}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@Autowired
	QuestionDao qd;

	//@CacheEvict(value = "questions", key = "'allQuestions'")
	@DeleteMapping("/remove/{qNo}")
	public ResponseEntity<?> remove(@PathVariable int qNo){
		qd.deleteByqNo(qNo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
