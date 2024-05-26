package com.ray.common.service;

import com.ray.common.model.User;

/**
 * @Author: RayMind
 * @Date: 2024/5/25
 * @Description:  用户服务
 */
public interface UserService {

    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user);
}
