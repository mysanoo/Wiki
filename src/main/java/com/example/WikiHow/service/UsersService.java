package com.example.WikiHow.service;

import com.example.WikiHow.base.ApiResponse;
import com.example.WikiHow.entity.User;

public interface UsersService {

    ApiResponse saveUser(User user);
    ApiResponse UsersPosts(Integer id);

    User theUser(Integer id);

}
