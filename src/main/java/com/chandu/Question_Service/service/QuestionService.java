package com.chandu.Question_Service.service;

import com.chandu.Question_Service.dto.QuestionDTO;
import com.chandu.Question_Service.model.Question;
import com.chandu.Question_Service.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionService {

    ResponseEntity<List<Question>> findAll();

    ResponseEntity<List<Question>> findByCategory(String category);

    ResponseEntity<Question> save(Question question);

    List<Long> generteQuestion(String category, int numOfQuestions);

    ResponseEntity<List<QuestionDTO>> getQuestionsByIds(List<Long> questionIds);

    ResponseEntity<Integer> getScore(List<Response> responses);
}
