package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Approved;
import com.example.WikiHow.entity.User;
import com.example.WikiHow.entity.enums.Status;
import com.example.WikiHow.repo.ApprovedRepo;
import com.example.WikiHow.repo.PostRepo;
import com.example.WikiHow.service.ApprovedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovedServiceImpl implements ApprovedService {

    private final ApprovedRepo approvedRepo;
    private final UserServiceImpl userService;
    private final PostRepo postRepo;

    @Override
    public ApiResponse addApproved(Approved approved, User user, Integer postId) {
        Approved newApproved = Approved.builder()
                .approvedBy(user)
                .post(postRepo.findById(postId).stream().findFirst().orElse(null))
                .status(approved.getStatus())
                .why(approved.getWhy())
                .time(approved.getTime())
                .build();
        approvedRepo.save(newApproved);
        return ApiResponse.builder().data(newApproved).build();
    }

    @Override
    public ApiResponse editWhy(Integer approvedId, TextDto dto) {
        Approved newApproved = approvedRepo.findById(approvedId).stream().findFirst().orElse(null);
        assert newApproved != null;
        newApproved.setWhy(dto.getText());
        approvedRepo.save(newApproved);
        return ApiResponse.builder().data(newApproved).build();
    }
}
