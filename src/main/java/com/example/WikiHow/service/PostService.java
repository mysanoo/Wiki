package com.example.WikiHow.service;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface PostService {

    ApiResponse createPost(Post post);

    ApiResponse allPost();

    Post thePost(Integer id);
    ApiResponse deletePost(Integer id);

    ApiResponse updateTitle(TextDto dto, Integer id);
    ApiResponse updateTime(LocalDateTime time, Integer id);
    ApiResponse updateDescription(TextDto dto, Integer id);
    ApiResponse setTags(Tag tag, Integer postId);

    ApiResponse deleteAnyPosts(ArrayList<Integer> posts);
}
