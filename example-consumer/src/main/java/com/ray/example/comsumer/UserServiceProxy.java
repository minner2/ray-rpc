package com.ray.example.comsumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.ray.common.model.User;
import com.ray.common.service.UserService;
import com.ray.rayrpc.model.RpcRequest;
import com.ray.rayrpc.model.RpcResponse;
import com.ray.rayrpc.serializer.JdkSerializer;

/**
 * @Author: RayMind
 * @Date: 2024/5/26 14:01
 * @Description:
 */
public class UserServiceProxy implements UserService {

    @Override
    public User getUser(User user) {
        JdkSerializer serializer = new JdkSerializer();
        RpcRequest request = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .args(new Object[]{user})
                .argTypes(new Class[]{User.class})
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
            return (User) rpcResponse.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
