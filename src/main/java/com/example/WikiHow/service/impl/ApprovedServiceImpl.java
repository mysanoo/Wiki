package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Approved;
import com.example.WikiHow.repo.ApprovedRepo;
import com.example.WikiHow.service.ApprovedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovedServiceImpl implements ApprovedService {

    private final ApprovedRepo approvedRepo;

    @Override
    public ApiResponse postApproved(Approved approved) {
        approvedRepo.save(approved);
        return ApiResponse.builder()
                .data(approved)
                .build();
    }
}
