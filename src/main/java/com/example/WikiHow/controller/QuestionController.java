package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Question;
import com.example.WikiHow.service.impl.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionServiceImpl questionService;

    @PostMapping("/{postId}")
    public HttpEntity<ApiResponse> createCategory(@RequestBody Question question,
                                                  @PathVariable Integer postId){
        return ResponseEntity.ok(questionService.createQuestion(question, postId));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteQuestion(@PathVariable Integer id){
        return ResponseEntity.ok(questionService.deleteQuestion(id));
    }
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> updateQuestionText(@PathVariable Integer id,
                                                      @RequestBody TextDto dto){
        return ResponseEntity.ok(questionService.updateQuestionText(dto, id));
    }
}
