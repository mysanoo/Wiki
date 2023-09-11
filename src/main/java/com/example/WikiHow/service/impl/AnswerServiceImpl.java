package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Answer;
import com.example.WikiHow.repo.AnswerRepo;
import com.example.WikiHow.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepo answerRepo;
    private final QuestionServiceImpl questionService;
    private final UserServiceImpl userService;

    @Override
    public ApiResponse createAnswer(Answer answer, Integer id) {
        return ApiResponse.builder()
                .data(
                        Answer.builder()
                                .text(answer.getText())
                                .question(questionService.theQuestion(answer.getQuestion().getId()))
                                .users(userService.theUser(id))
                        .build()
                )
                .build();
    }

    @Override
    public ApiResponse updateAnswerText(Integer id, TextDto dto) {
        Answer answer = answerRepo.findById(id).stream().findFirst().orElse(null);
        assert answer != null;
        answer.setText(dto.getText());
        answerRepo.save(answer);
        return ApiResponse.builder()
                .data(answer)
                .build();
    }

    @Override
    public ApiResponse deleteAnswer(Integer id) {
        Answer answer = answerRepo.findById(id).stream().findFirst().orElse(null);
        assert answer != null;
        answerRepo.delete(answer);
        return ApiResponse.builder()
                .data(answer)
                .build();
    }

    @Override
    public ApiResponse getQuestionAnswers(Integer questionId) {
        List<Answer> answers = answerRepo.findAll();
        List<Answer> questionAnswers = new ArrayList<>();
        for (Answer answer : answers) {
            if(answer.getQuestion().getId().equals(questionId)){
                questionAnswers.add(answer);
            }
        }
        return ApiResponse.builder()
                .data(questionAnswers)
                .build();
    }
}
