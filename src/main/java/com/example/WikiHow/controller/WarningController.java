package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Warnings;
import com.example.WikiHow.service.impl.WarningServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warning")
@RequiredArgsConstructor
public class WarningController {

    private final WarningServiceImpl warningService;

    @PostMapping
    public HttpEntity<ApiResponse> createWarning(@RequestBody Warnings warning){
        return ResponseEntity.ok(warningService.createWarning(warning));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteWarning(@PathVariable Integer id){
        return ResponseEntity.ok(warningService.deleteWarning(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> updateWarningText(@PathVariable Integer id,
                                                  @RequestBody TextDto dto){
        return ResponseEntity.ok(warningService.updateWarningText(dto, id));
    }
}
