package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Approved;
import com.example.WikiHow.entity.User;
import com.example.WikiHow.service.impl.ApprovedServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/approved")
@RequiredArgsConstructor
public class ApprovedController {

    private final ApprovedServiceImpl approvedService;

    @PostMapping(value = "/{postId}")
    public HttpEntity<ApiResponse> addApproved(@RequestBody Approved approved,
                                               @RequestBody User user,
                                               @PathVariable Integer postId){
        return ResponseEntity.ok(approvedService.addApproved(approved, user, postId));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editApprovedWhy(@PathVariable Integer id, @RequestBody TextDto dto){
        return ResponseEntity.ok(approvedService.editWhy(id, dto));
    }
}
