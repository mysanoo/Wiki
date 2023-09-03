package com.example.WikiHow.service;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Warnings;

public interface WarningService {

    ApiResponse createWarning(Warnings warnings);
    ApiResponse updateWarningText(TextDto dto, Integer id);
    ApiResponse deleteWarning(Integer id);
}
