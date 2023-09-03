package com.example.WikiHow.service;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Answer;

public interface AnswerService {

    ApiResponse createAnswer(Answer answer, Integer id);
    ApiResponse updateAnswerText(Integer id, TextDto dto);
    ApiResponse deleteAnswer(Integer id);
    ApiResponse getQuestionAnswers(Integer id);

}
