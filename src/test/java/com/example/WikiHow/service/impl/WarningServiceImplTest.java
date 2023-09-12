package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.Warnings;
import com.example.WikiHow.repo.WarningsRepo;
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
class WarningServiceImplTest {

    @Mock
    private WarningsRepo warningsRepo;
    private WarningServiceImpl warningService;

    @BeforeEach
    void setUp() {
        warningService = new WarningServiceImpl(warningsRepo);
    }

    @Test
    void createWarning() {
        Post post = Post.builder().id(1).title("sample title").build();
        Warnings warning = Warnings.builder().id(1).text("sample warning").post(post).build();

        when(warningsRepo.save(any(Warnings.class))).thenReturn(warning);

        ApiResponse result = warningService.createWarning(warning);

        verify(warningsRepo).save(any(Warnings.class));

        Warnings savedWarning = (Warnings) result.getData();
        assertEquals(savedWarning.getText(), warning.getText());
    }

    @Test
    void updateWarningText() {
        Warnings warning = Warnings.builder().id(1).text("sample warning").build();
        TextDto dto = new TextDto("updated text");

        when(warningsRepo.findById(1)).thenReturn(Optional.of(warning));

        ApiResponse result = warningService.updateWarningText(dto,1);

        verify(warningsRepo).save(warning);
        verify(warningsRepo).findById(1);

        Warnings savedWarning = (Warnings) result.getData();
        assertEquals(savedWarning.getText(), dto.getText());

    }

    @Test
    void deleteWarning() {
        Warnings warning = Warnings.builder().id(1).text("sample warning").build();


        when(warningsRepo.findById(1)).thenReturn(Optional.of(warning));

        ApiResponse result = warningService.deleteWarning(1);

        verify(warningsRepo).delete(warning);
        verify(warningsRepo).findById(1);

        Warnings savedWarning = (Warnings) result.getData();
        assertEquals(savedWarning.getText(), warning.getText());
    }
}