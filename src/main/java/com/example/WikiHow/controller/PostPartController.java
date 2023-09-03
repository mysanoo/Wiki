package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.PostPart;
import com.example.WikiHow.service.impl.PostPartImpl;
import com.example.WikiHow.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/postPart")
public class PostPartController {
    private final PostPartImpl postPartimpl;
    private final PostServiceImpl postService;

    @PostMapping("/{id}")
    public HttpEntity<ApiResponse> create(@PathVariable Integer id,
            @RequestBody PostPart postPart){
        PostPart newPart = PostPart.builder()
                .post(postService.thePost(id))
                .title(postPart.getTitle())
                .build();
        return ResponseEntity.ok(postPartimpl.addPostPart(newPart));
    }


}
