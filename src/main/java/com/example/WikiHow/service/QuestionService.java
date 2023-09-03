package com.example.WikiHow.service;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Question;

public interface QuestionService {

    ApiResponse createQuestion(Question question, Integer id);
    Question theQuestion(Integer id);
    ApiResponse deleteQuestion(Integer id);
    ApiResponse updateQuestionText(TextDto dto, Integer id);
    ApiResponse helpFull(Integer id);
    ApiResponse NotHelpFull(Integer id);
}
