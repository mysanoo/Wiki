package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Tips;
import com.example.WikiHow.service.impl.TipsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tips")
@RequiredArgsConstructor
public class TipsController {

    private final TipsServiceImpl tipsService;

    @PostMapping
    public HttpEntity<ApiResponse> createTips(@RequestBody Tips tips){
        return ResponseEntity.ok(tipsService.createTips(tips));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteTips(@PathVariable Integer id){
        return ResponseEntity.ok(tipsService.deleteTips(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> updateTipsText(@PathVariable Integer id,
                                                  @RequestBody TextDto dto){
        return ResponseEntity.ok(tipsService.updateTipsText(dto, id));
    }

}
