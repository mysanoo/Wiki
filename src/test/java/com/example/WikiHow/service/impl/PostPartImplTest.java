package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.PostPartDto;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.PostPart;
import com.example.WikiHow.repo.PostPartRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostPartImplTest {

    @Mock
    private PostPartRepo postPartRepo;
    private PostPartImpl underTest;
    @BeforeEach
    void setUp() {
        underTest = new PostPartImpl(postPartRepo);
    }

    @Test
    void addPostPart() {
        PostPart postPart = PostPart.builder().id(1).build();

        when(postPartRepo.save(postPart)).thenReturn(postPart);

        ApiResponse result = underTest.addPostPart(postPart);

        verify(postPartRepo).save(postPart);

        assertEquals(result.getData(), postPart);
    }

    @Test
    void deletePostPart() {
        PostPart postPart = PostPart.builder().id(1).build();

        when(postPartRepo.findById(1)).thenReturn(Optional.of(postPart));

        ApiResponse result = underTest.deletePostPart(1);

        verify(postPartRepo).delete(postPart);

        assertEquals(result.getData(), postPart);

    }

    @Test
    void updatePartText() {
        PostPart postPart = PostPart.builder().id(1).title("used text").build();

        TextDto dto = new TextDto("sample text");

        postPart.setText(dto.getText());

        when(postPartRepo.findById(1)).thenReturn(Optional.of(postPart));

        ApiResponse result = underTest.updatePartText(dto,1);

        verify(postPartRepo).save(postPart);
        verify(postPartRepo).findById(1);

        postPart = (PostPart) result.getData();
        assertEquals(postPart.getText(), dto.getText());


    }

    @Test
    void updatePartTitle() {
        PostPart postPart = PostPart.builder().id(1).title("used text").build();

        TextDto dto = new TextDto("sample text");

        postPart.setText(dto.getText());

        when(postPartRepo.findById(1)).thenReturn(Optional.of(postPart));

        ApiResponse result = underTest.updatePartTitle(dto,1);

        verify(postPartRepo).save(postPart);
        verify(postPartRepo).findById(1);

        postPart = (PostPart) result.getData();
        assertEquals(postPart.getTitle(), dto.getText());
    }

    @Test
    void updatePostPart() {
        PostPart postPart = PostPart.builder().title("title").text("text").build();
        PostPartDto postPartChanging = new PostPartDto(1,"second text","second title");

        when(postPartRepo.findById(1)).thenReturn(Optional.of(postPart));

        ApiResponse result = underTest.updatePostPart(postPartChanging);

        verify(postPartRepo).findById(1);
        verify(postPartRepo).save(postPart);

        assertEquals(result.getData(), postPart);
        assertEquals(postPart.getTitle(), "second title");
    }
}