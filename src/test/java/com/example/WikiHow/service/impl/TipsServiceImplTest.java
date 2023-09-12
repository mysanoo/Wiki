package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.Tips;
import com.example.WikiHow.repo.TipsRepo;
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
class TipsServiceImplTest {

    @Mock
    private TipsRepo tipsRepo;
    private TipsServiceImpl tipsService;
    @BeforeEach
    void setUp() {
        tipsService = new TipsServiceImpl(tipsRepo);
    }

    @Test
    void createTips() {

        Post post = Post.builder().id(1).title("sample title").build();
        Tips tips = Tips.builder().id(1).text("sample text").post(post).build();

        when(tipsRepo.save(any(Tips.class))).thenReturn(tips);

        ApiResponse result = tipsService.createTips(tips);

        verify(tipsRepo).save(any(Tips.class));

        Tips savedTips = (Tips) result.getData();
        assertEquals(tips.getText(), savedTips.getText());
        assertEquals(tips.getPost(), savedTips.getPost());
    }

    @Test
    void updateTipsText() {
        Tips tips = Tips.builder().id(1).text("sample text").build();

        TextDto dto = new TextDto("updated text");
        when(tipsRepo.findById(1)).thenReturn(Optional.of(tips));

        ApiResponse result = tipsService.updateTipsText(dto, 1);

        verify(tipsRepo).findById(1);

        Tips updatedTips = (Tips) result.getData();
        assertEquals("updated text", updatedTips.getText());
    }

    @Test
    void deleteTips() {
        Tips tips = Tips.builder().id(1).text("sample text").build();

        when(tipsRepo.findById(1)).thenReturn(Optional.of(tips));

        ApiResponse result = tipsService.deleteTips(1);

        verify(tipsRepo).delete(tips);

        Tips savedTips = (Tips) result.getData();
        assertEquals(tips.getText(), savedTips.getText());
    }
}