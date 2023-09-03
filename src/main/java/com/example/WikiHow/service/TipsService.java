package com.example.WikiHow.service;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Tips;

public interface TipsService {

    ApiResponse createTips(Tips tips);
    ApiResponse updateTipsText(TextDto dto, Integer id);
    ApiResponse deleteTips(Integer id);


}
