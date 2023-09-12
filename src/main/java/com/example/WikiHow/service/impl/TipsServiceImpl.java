package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Tips;
import com.example.WikiHow.repo.TipsRepo;
import com.example.WikiHow.service.TipsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipsServiceImpl implements TipsService {

    private final TipsRepo tipsRepo;


    @Override
    public ApiResponse createTips(Tips tips) {
        Tips newTips = Tips.builder()
                .post(tips.getPost())
                .text(tips.getText())
                .build();
        tipsRepo.save(newTips);
        return ApiResponse.builder()
                .data(newTips)
                .build();
    }

    @Override
    public ApiResponse updateTipsText(TextDto dto, Integer tipsId) {
        Tips tips = tipsRepo.findById(tipsId).stream().findFirst().orElse(null);
        assert tips != null;
        tips.setText(dto.getText());
        tipsRepo.save(tips);
        return ApiResponse.builder()
                .data(tips)
                .build();
    }

    @Override
    public ApiResponse deleteTips(Integer tipsId) {
        Tips tips = tipsRepo.findById(tipsId).stream().findFirst().orElse(null);
        assert tips != null;
        tipsRepo.delete(tips);
        return ApiResponse.builder()
                .data(tips)
                .build();
    }

}
