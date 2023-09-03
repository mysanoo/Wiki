package com.example.WikiHow.service.impl;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.User;
import com.example.WikiHow.repo.PostRepo;
import com.example.WikiHow.repo.UsersRepo;
import com.example.WikiHow.service.UsersService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UsersService {

    private final UsersRepo usersRepo;
    private final PostRepo postRepo;

    @Override
    public ApiResponse saveUser(User user){
        User newUser = User.builder()
                .email(user.getEmail())
                .job(user.getJob())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        usersRepo.save(newUser);
        return ApiResponse.builder()
                .data(newUser)
                .build();
    }

    @Override
    public User theUser(Integer id) {
        return usersRepo.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public ApiResponse UsersPosts(Integer id) {
        List<Post> posts = postRepo.findAll();
        List<Post> userPosts = new ArrayList<>();
        for (Post post : posts) {
            if(post.getCreator().getId().equals(id)){
                userPosts.add(post);
            }
        }
        return ApiResponse.builder()
                .data(userPosts)
                .build();
    }

    @Override
    public ApiResponse blockUser(Integer id) {
        return null;
    }
}
