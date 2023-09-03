package com.example.WikiHow.service;


import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.PostPartDto;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.PostPart;

public interface PostPartService {

    ApiResponse addPostPart(PostPart postPart);
    ApiResponse deletePostPart(Integer id);
    ApiResponse updatePartText(TextDto dto, Integer id);
    ApiResponse updatePartTitle(TextDto dto, Integer id);
    ApiResponse updatePostPart(PostPartDto dto);

}
