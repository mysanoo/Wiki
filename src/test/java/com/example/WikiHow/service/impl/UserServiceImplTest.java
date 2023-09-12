package com.example.WikiHow.service.impl;


import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.Post;
import com.example.WikiHow.entity.PostPart;
import com.example.WikiHow.entity.User;
import com.example.WikiHow.repo.PostRepo;
import com.example.WikiHow.repo.UsersRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UsersRepo usersRepo;
    @Mock
    private PostRepo postRepo;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(usersRepo, postRepo);
    }

    @Test
    void saveUser() {
        User user = User.builder().id(1).username("sample name").build();

        when(usersRepo.save(any(User.class))).thenReturn(user);

        ApiResponse result = userService.saveUser(user);

        verify(usersRepo).save(any(User.class));

        User savedUser = (User) result.getData();

        assertEquals(savedUser.getUsername(), user.getUsername());
    }

    @Test
    void usersPosts() {
        User user1= User.builder().id(1).username("first").build();
        User user2= User.builder().id(2).username("second").build();

        Post post1 = Post.builder().id(1).creator(user1).title("first").build();
        Post post2 = Post.builder().id(2).creator(user1).title("second").build();
        Post post3 = Post.builder().id(3).creator(user2).title("third").build();
        Post post4 = Post.builder().id(4).creator(user2).title("fourth").build();

        List<Post> posts = new ArrayList<>(List.of(post1,post2,post3,post4));

        when(postRepo.findAll()).thenReturn(posts);

        ApiResponse result = userService.UsersPosts(1);

        verify(postRepo).findAll();

        ArrayList<Post> postList = (ArrayList) result.getData();
        assertEquals(postList.size(), 2);
        assertEquals(postList.get(0), post1);


    }
}