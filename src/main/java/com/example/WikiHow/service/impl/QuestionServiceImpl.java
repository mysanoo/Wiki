package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Question;
import com.example.WikiHow.repo.QuestionRepo;
import com.example.WikiHow.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;
    private final UserServiceImpl userService;
    private final PostServiceImpl postService;

    @Override
    public ApiResponse createQuestion(Question question, Integer id) {

        Question newQuestion = Question.builder()
                .user(userService.theUser(question.getUser().getId()))
                .post(postService.thePost(id))
                .text(question.getText())
                .build();
        return ApiResponse.builder()
                .data(newQuestion)
                .build();
    }

    @Override
    public Question theQuestion(Integer id) {
        return questionRepo.findById(id).stream().findFirst().orElse(null);

    }

    @Override
    public ApiResponse deleteQuestion(Integer id) {
        Question question = theQuestion(id);
        questionRepo.delete(question);
        return ApiResponse.builder()
                .data(question)
                .build();
    }

    @Override
    public ApiResponse updateQuestionText(TextDto dto, Integer id) {
        Question question = theQuestion(id);
        question.setText(dto.getText());
        questionRepo.save(question);
        return ApiResponse.builder()
                .data(question)
                .build();
    }

    @Override
    public ApiResponse helpFull(Integer questionId) {
        Question question = theQuestion(questionId);
        if(question.getHelpful()==null){
            question.setHelpful(1);
        }else question.setHelpful(question.getHelpful()+1);
        questionRepo.save(question);
        return ApiResponse.builder()
                .data(question)
                .build();
    }

    @Override
    public ApiResponse NotHelpFull(Integer questionId) {
        Question question = theQuestion(questionId);
        if(question.getNot_helpful()==null){
            question.setNot_helpful(1);
        }else question.setNot_helpful(question.getNot_helpful()+1);
        questionRepo.save(question);
        return ApiResponse.builder()
                .data(question)
                .build();
    }
}
