package com.example.WikiHow.service;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Tag;

public interface TagsService {

    ApiResponse createTag(Tag tag);
    ApiResponse updateTagName(String name, Integer id);
    ApiResponse deleteTag(Integer id);
}
