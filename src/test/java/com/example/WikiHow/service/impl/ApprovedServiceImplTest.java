package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Approved;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.User;
import com.example.WikiHow.repo.ApprovedRepo;
import com.example.WikiHow.repo.PostRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApprovedServiceImplTest {

    @Mock
    private ApprovedRepo approvedRepo;
    @Mock
    private PostRepo postRepo;

    private ApprovedServiceImpl approvedService;


    @BeforeEach
    void setUp() {
        approvedService = new ApprovedServiceImpl(approvedRepo, postRepo);
    }

    @Test
    void addApproved() {
        User user = User.builder().id(1).username("sample username").build();
        Approved approved = Approved.builder().id(1).why("sample reason").build();

        Post post = Post.builder().id(1).title("sample title").build();

        when(postRepo.findById(1)).thenReturn(Optional.of(post));
        when(approvedRepo.save(any(Approved.class))).thenReturn(approved);

        ApiResponse result = approvedService.addApproved(approved, user, post.getId());

        verify(approvedRepo).save(any(Approved.class));
        verify(postRepo).findById(1);

        Approved resultApproved = (Approved) result.getData();
        assertEquals(resultApproved.getApprovedBy(), user);
    }

    @Test
    void editWhy() {
        TextDto dto = new TextDto("not sample reason");
        Approved approved = Approved.builder().id(1).why("sample reason").build();

        when(approvedRepo.findById(1)).thenReturn(Optional.of(approved));

        ApiResponse result = approvedService.editWhy(1, dto);

        verify(approvedRepo).findById(1);
        verify(approvedRepo).save(any(Approved.class));

        Approved resultApproved = (Approved) result.getData();
        assertEquals(resultApproved.getWhy(), dto.getText());
    }
}