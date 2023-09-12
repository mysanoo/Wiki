package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Tag;
import com.example.WikiHow.repo.TagsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagsServiceImplTest {

    @Mock
    private TagsRepo tagsRepo;
    private TagsServiceImpl tagsService;

    @BeforeEach
    void beforeEach(){
        tagsService = new TagsServiceImpl(tagsRepo);
    }


    @Test
    void createTag() {
        Tag tag = Tag.builder().id(1).name("sample name").build();

        when(tagsRepo.save(any(Tag.class))).thenReturn(tag);

        ApiResponse result = tagsService.createTag(tag);

        verify(tagsRepo).save(any(Tag.class));

        Tag savedTag = (Tag)result.getData();
        assertEquals(tag.getName(), savedTag.getName());

    }

    @Test
    void updateTagName() {
        Tag tag = Tag.builder().id(1).name("sample name").build();

        when(tagsRepo.findById(1)).thenReturn(Optional.of(tag));

        ApiResponse result = tagsService.updateTagName("updated name", 1);

        verify(tagsRepo).findById(1);
        verify(tagsRepo).save(any(Tag.class));

        Tag savedTag = (Tag)result.getData();
        assertEquals("updated name", savedTag.getName());

    }

    @Test
    void deleteTag() {
        Tag tag = Tag.builder().id(1).name("sample name").build();

        when(tagsRepo.findById(1)).thenReturn(Optional.of(tag));

        ApiResponse result = tagsService.deleteTag(1);

        verify(tagsRepo).delete(tag);

        Tag savedTag = (Tag)result.getData();
        assertEquals(tag.getName(), savedTag.getName());
    }
}