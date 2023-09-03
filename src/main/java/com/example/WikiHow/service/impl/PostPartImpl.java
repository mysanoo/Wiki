package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.PostPartDto;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.PostPart;
import com.example.WikiHow.repo.PostPartRepo;
import com.example.WikiHow.repo.PostRepo;
import com.example.WikiHow.service.PostPartService;
import com.example.WikiHow.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostPartImpl implements PostPartService {

    private final PostPartRepo postPartRepo;
    @Override
    public ApiResponse addPostPart(PostPart postpart) {
        postPartRepo.save(postpart);
        return ApiResponse.builder()
                .data(postpart)
                .build();
    }

    @Override
    public ApiResponse deletePostPart(Integer id) {
        PostPart postPart = postPartRepo.findById(id).stream().findFirst().orElse(null);
        assert postPart != null;
        postPartRepo.delete(postPart);
        return ApiResponse.builder()
                .data(postPart)
                .build();
    }

    @Override
    public ApiResponse updatePartText(TextDto dto, Integer id) {
        PostPart postPart = postPartRepo.findById(id).stream().findFirst().orElse(null);
        assert postPart != null;
        postPart.setText(dto.getText());
        postPartRepo.save(postPart);
        return ApiResponse.builder()
                .data(postPart)
                .build();
    }

    @Override
    public ApiResponse updatePartTitle(TextDto dto, Integer id) {
        PostPart postPart = postPartRepo.findById(id).stream().findFirst().orElse(null);
        assert postPart != null;
        postPart.setTitle(dto.getText());
        postPartRepo.save(postPart);
        return ApiResponse.builder()
                .data(postPart)
                .build();
    }

    @Override
    public ApiResponse updatePostPart(PostPartDto dto) {
        PostPart postPart = postPartRepo.findById(dto.getPostPartId()).stream().findFirst().orElse(null);
        assert postPart != null;
        postPart.setTitle(dto.getTitle());
        postPart.setText(dto.getText());
        postPartRepo.save(postPart);
        return ApiResponse.builder()
                .data(postPart)
                .build();
    }
}
