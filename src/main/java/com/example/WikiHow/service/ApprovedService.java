package com.example.WikiHow.service;


import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Approved;
import com.example.WikiHow.entity.User;

public interface ApprovedService {

    ApiResponse addApproved(Approved approved, User user, Integer postId);

    ApiResponse editWhy(Integer approvedId, TextDto dto);

}
