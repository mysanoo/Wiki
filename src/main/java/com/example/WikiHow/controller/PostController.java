package com.example.WikiHow.controller;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post")
public class PostController {

    private final PostServiceImpl postService;


    @PostMapping()
    public HttpEntity<ApiResponse> create(@RequestBody Post post){

        return ResponseEntity.ok(postService.createPost(post));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id){
        return ResponseEntity.ok(postService.deletePost(id));
    }

    @GetMapping()
    public HttpEntity<ApiResponse> findAll(){
        return ResponseEntity.ok(postService.allPost());
    }

    @PutMapping("/updateTitle/{id}")
    public HttpEntity<ApiResponse> updateTitle(@RequestBody TextDto dto, @PathVariable Integer id) {

        return ResponseEntity.ok(postService.updateTitle(dto, id));
    }

    @PutMapping("/updateDescription/{id}")
    public HttpEntity<ApiResponse> updateDescription(@RequestBody TextDto dto, @PathVariable Integer id) {

        return ResponseEntity.ok(postService.updateDescription(dto, id));
    }

    @PutMapping("/updateTime/{id}")
    public HttpEntity<ApiResponse> updateTimestamp(@RequestParam("time") LocalDateTime time, @PathVariable Integer id) {

        return ResponseEntity.ok(postService.updateTime(time, id));
    }

}
