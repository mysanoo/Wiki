package com.example.WikiHow.service;


import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Approved;

public interface ApprovedService {

    ApiResponse postApproved(Approved approved);

}
