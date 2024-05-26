package com.ray.rayrpc.server;

/**
 * @Author: RayMind
 * @Date: 2024/5/26
 * @Description: HTTP服务接口
 */
public interface HttpServer {

    /**
     * 启动服务器
     * @param port 端口
     */
    void doStart(int port);
}
