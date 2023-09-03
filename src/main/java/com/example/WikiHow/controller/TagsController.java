package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Tag;
import com.example.WikiHow.service.impl.TagsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.metrics.StartupStep;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagsServiceImpl tagsService;

    @PostMapping
    public HttpEntity<ApiResponse> createTags(@RequestBody Tag tag){
        return ResponseEntity.ok(tagsService.createTag(tag));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteTag(@PathVariable Integer id){
        return ResponseEntity.ok(tagsService.deleteTag(id));
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> updateTagName(@PathVariable Integer id,
                                                 @RequestParam String name){
        return ResponseEntity.ok(tagsService.updateTagName(name, id));
    }

}
