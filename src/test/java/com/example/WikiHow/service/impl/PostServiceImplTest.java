package com.example.WikiHow.service.impl;

import com.example.WikiHow.entity.Post;
import com.example.WikiHow.repo.PostRepo;
import com.example.WikiHow.repo.UsersRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.AssertionsForClassTypes.*;


@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepo postRepo;
    @Mock
    private UsersRepo usersRepo;
    private PostServiceImpl postService;
    @BeforeEach
    void setUp() {
        postService = new PostServiceImpl(postRepo,usersRepo);
    }

    @Test
    void allPost() {
        postService.allPost();
        org.mockito.Mockito.verify(postRepo).findAll();
    }

    @Test
    void createPost() {
        Post post = Post.builder()
                .description("this is on testing")
                .title("testing")
                .build();

        postService.createPost(post);

        ArgumentCaptor<Post> argumentCaptor = ArgumentCaptor.forClass(Post.class);
        org.mockito.Mockito.verify(postRepo).save(argumentCaptor.capture());
        Post capturedPost = argumentCaptor.getValue();
        org.assertj.core.api.AssertionsForClassTypes.assertThat(capturedPost).isEqualTo(post);


    }
}