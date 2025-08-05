package com.chandu.Question_Service.service.serviceimpl;

import com.chandu.Question_Service.dto.QuestionDTO;
import com.chandu.Question_Service.model.Question;
import com.chandu.Question_Service.model.Response;
import com.chandu.Question_Service.repository.QuestionRepository;
import com.chandu.Question_Service.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<List<Question>> findAll() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> findByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Question> save(Question question) {
        try {
            return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Long> generteQuestion(String  category, int numOfQuestions) {
        List<Long> questions = questionRepository.findRandomQAuestionsByCategory(category, numOfQuestions);

        return questions;
    }

    @Override
    public ResponseEntity<List<QuestionDTO>> getQuestionsByIds(List<Long> questionIds){
        List<QuestionDTO> questions  = new ArrayList<>();

        for(Long questionId : questionIds){
            Question fullQuestion = questionRepository.findById(questionId).orElse(null);

            if(fullQuestion != null){
                QuestionDTO questionDTO = new QuestionDTO();

                questionDTO.setId(fullQuestion.getId());
                questionDTO.setTitle(fullQuestion.getTitle());
                questionDTO.setOption1(fullQuestion.getOption1());
                questionDTO.setOption2(fullQuestion.getOption2());
                questionDTO.setOption3(fullQuestion.getOption3());
                questionDTO.setOption4(fullQuestion.getOption4());

                questions.add(questionDTO);
            }
        }

        return  new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;

        for (Response response : responses) {
            Question question = questionRepository.findById(response.getId()).orElse(null);

            if(question != null && question.getRightAnswer().equals(response.getSubmittedAnswer())) {
                score++;
            }
        }

        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}