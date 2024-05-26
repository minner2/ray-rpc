package com.ray.rayrpc.server;

import io.vertx.core.Vertx;

/**
 * @Author: RayMind
 * @Date: 2024/5/26
 * @Description:
 */
public class VertxHttpServer implements HttpServer {
    public void doStart(int port) {
        //创建实例
        Vertx vertx = Vertx.vertx();
        //创建HTTP服务器
        io.vertx.core.http.HttpServer httpServer = vertx.createHttpServer();
        //处理请求
        httpServer.requestHandler(new HttpServerHandler());
        //监听端口，开启服务
        httpServer.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.out.println("Failed to start server " + result.cause());
            }
        });
    }
}
