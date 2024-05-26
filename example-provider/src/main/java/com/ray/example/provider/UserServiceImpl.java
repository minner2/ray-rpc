package com.ray.example.provider;

import com.ray.common.model.User;
import com.ray.common.service.UserService;

/**
 * @Author: RayMind
 * @Date: 2024/5/26
 * @Description: 用户服务实现类
 */
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名" + user.getName());
        return user;
    }
}
