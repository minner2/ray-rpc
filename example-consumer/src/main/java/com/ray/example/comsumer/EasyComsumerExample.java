package com.ray.example.comsumer;

import com.ray.common.model.User;
import com.ray.common.service.UserService;
import com.ray.rayrpc.proxy.ServiceProxyFactory;
import com.ray.rayrpc.registry.LocalRegistry;

import java.util.Objects;

/**
 * @Author: RayMind
 * @Date: 2024/5/26
 * @Description: 服务消费者示例
 */
public class EasyComsumerExample {

    public static void main(String[] args) {
        User user = new User();
//        UserServiceProxy userServiceProxy = new UserServiceProxy();
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        user.setName("ray");
        User newUser = userService.getUser(user);
        if(Objects.nonNull(newUser)){
            System.out.println(newUser.getName());
        }else{
            System.out.println("user == null");
        }
    }
}
