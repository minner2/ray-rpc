package com.ray.example.provider;

import com.ray.common.service.UserService;
import com.ray.rayrpc.registry.LocalRegistry;
import com.ray.rayrpc.server.VertxHttpServer;

/**
 * @Author: RayMind
 * @Date: 2024/5/26
 * @Description:  简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);
        //启动web服务
        VertxHttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(8000);

    }
}
