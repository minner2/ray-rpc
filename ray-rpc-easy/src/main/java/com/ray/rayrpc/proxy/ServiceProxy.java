package com.ray.rayrpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.ray.rayrpc.model.RpcRequest;
import com.ray.rayrpc.model.RpcResponse;
import com.ray.rayrpc.serializer.JdkSerializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: RayMind
 * @Date: 2024/5/26 17:31
 * @Description: 服务代理  JDK 动态代理
 */
public class ServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        JdkSerializer serializer = new JdkSerializer();
        RpcRequest request = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .args(args)
                .argTypes(method.getParameterTypes())
                .build();
        try {
            byte[] bytes = serializer.serialize(request);
            byte[] result = null;
            try(HttpResponse httpResponse = HttpRequest.post("http://localhost:8000")
                    .body(bytes)
                    .execute()){
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return rpcResponse.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
