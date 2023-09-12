package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Warnings;
import com.example.WikiHow.repo.WarningsRepo;
import com.example.WikiHow.service.WarningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarningServiceImpl implements WarningService {


    private final WarningsRepo warningsRepo;
    @Override
    public ApiResponse createWarning(Warnings warnings) {
        Warnings newWarning = Warnings.builder()
                .text(warnings.getText())
                .post(warnings.getPost())
                .build();

        warningsRepo.save(newWarning);
        return ApiResponse.builder()
                .data(warnings)
                .build();
    }

    @Override
    public ApiResponse updateWarningText(TextDto dto, Integer warningId) {

        Warnings warnings = warningsRepo.findById(warningId).stream().findFirst().orElse(null);
        assert warnings != null;
        warnings.setText(dto.getText());
        warningsRepo.save(warnings);

        return ApiResponse.builder()
                .data(warnings)
                .build();
    }

    @Override
    public ApiResponse deleteWarning(Integer warningId) {
        Warnings warnings = warningsRepo.findById(warningId).stream().findFirst().orElse(null);
        assert warnings != null;
        warningsRepo.delete(warnings);
        return ApiResponse.builder()
                .data(warnings)
                .build();
    }
}
