package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Answer;
import com.example.WikiHow.repo.AnswerRepo;
import com.example.WikiHow.service.AnswerService;
import com.example.WikiHow.service.impl.AnswerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerServiceImpl answerService;

    @PostMapping("/{id}")
    public HttpEntity<ApiResponse> createAnswer(@RequestBody Answer answer,
                                                @PathVariable Integer id){
        return ResponseEntity.ok(answerService.createAnswer(answer, id));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteAnswer(@PathVariable Integer id){
        return ResponseEntity.ok(answerService.deleteAnswer(id));
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResponse> getQuestionAnswers(@PathVariable Integer id){
        return ResponseEntity.ok(answerService.getQuestionAnswers(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> updateAText(@PathVariable Integer id,
                                               @RequestBody TextDto dto){
        return ResponseEntity.ok(answerService.updateAnswerText(id, dto));
    }

}
