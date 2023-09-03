package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.dto.TextDto;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.Tag;
import com.example.WikiHow.repo.PostRepo;
import com.example.WikiHow.repo.UsersRepo;
import com.example.WikiHow.service.PostService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final UsersRepo usersRepo;

    @Override
    public Post thePost(Integer id) {
        return postRepo.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public ApiResponse createPost(Post post){

        Post newPost = Post.builder()
                .category(post.getCategory())
                .title(post.getTitle())
                .description(post.getDescription())
                .references(post.getReferences())
                .build();
        postRepo.save(newPost);
        return ApiResponse.builder()
                .data(newPost)
                .build();
    }

    @Override
    public ApiResponse deletePost(Integer id) {
        Post post = thePost(id);
        postRepo.delete(post);
        return ApiResponse.builder()
                .data(post)
                .build();
    }

    @Override
    public ApiResponse allPost() {
        return ApiResponse.builder()
                .data(postRepo.findAll())
                .build();
    }

    @Override
    public ApiResponse updateTitle(TextDto dto, Integer id) {
        Post post = thePost(id);
        post.setTitle(dto.getText());
        postRepo.save(post);
        return ApiResponse.builder()
                .data(post)
                .build();
    }

    @Override
    public ApiResponse updateTime(LocalDateTime time, Integer id) {
        Post post = thePost(id);
        post.setTimestamp(time);
        postRepo.save(post);
        return ApiResponse.builder()
                .data(post)
                .build();
    }

    @Override
    public ApiResponse updateDescription(TextDto dto, Integer id) {
        Post post = thePost(id);
        post.setDescription(dto.getText());
        postRepo.save(post);
        return ApiResponse.builder()
                .data(post)
                .build();
    }

    @Override
    public ApiResponse setTags(Tag tag, Integer postId) {
        return null;
    }
}
