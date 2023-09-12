package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Tag;
import com.example.WikiHow.repo.TagsRepo;
import com.example.WikiHow.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {


    private final TagsRepo tagsRepo;
    @Override
    public ApiResponse createTag(Tag tag) {
        Tag newTag = Tag.builder()
                .name(tag.getName())
                .build();
        tagsRepo.save(newTag);
        return ApiResponse.builder()
                .data(newTag)
                .build();
    }

    @Override
    public ApiResponse updateTagName(String name, Integer tagId) {
        Tag tag = tagsRepo.findById(tagId).stream().findFirst().orElse(null);
        assert tag!=null;
        tag.setName(name);
        tagsRepo.save(tag);
        return ApiResponse.builder()
                .data(tag)
                .build();
    }

    @Override
    public ApiResponse deleteTag(Integer tagId) {
        Tag tag = tagsRepo.findById(tagId).stream().findFirst().orElse(null);
        assert tag!=null;
        tagsRepo.delete(tag);
        return ApiResponse.builder()
                .data(tag)
                .build();
    }
}
