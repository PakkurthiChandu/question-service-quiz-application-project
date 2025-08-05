package com.chandu.Question_Service.controller;


import com.chandu.Question_Service.dto.QuestionDTO;
import com.chandu.Question_Service.model.Question;
import com.chandu.Question_Service.model.Response;
import com.chandu.Question_Service.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@CrossOrigin("*")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.findAll();
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.findByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return questionService.save(question);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Long>> generateQuestion(@RequestParam("category") String category,
                                                          @RequestParam("numQ") Integer numQ) {
        return new ResponseEntity<>(questionService.generteQuestion(category, numQ),  HttpStatus.OK);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestionsByCategory(@RequestBody List<Long> questionIds) {
        return questionService.getQuestionsByIds(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return questionService.getScore(responses);
    }
}