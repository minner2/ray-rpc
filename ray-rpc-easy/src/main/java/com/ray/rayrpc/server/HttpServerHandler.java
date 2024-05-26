package com.ray.rayrpc.server;

import com.ray.rayrpc.model.RpcRequest;
import com.ray.rayrpc.model.RpcResponse;
import com.ray.rayrpc.registry.LocalRegistry;
import com.ray.rayrpc.serializer.JdkSerializer;
import com.ray.rayrpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author: RayMind
 * @Date: 2024/5/26 12:17
 * @Description:
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest httpServerRequest) {
        //指定序列化器
        final Serializer serializer = new JdkSerializer();
        //记录日志
        System.out.println("Receive request "+httpServerRequest.method()+" "+httpServerRequest.uri());
        //异步处理HTTP请求
        httpServerRequest.bodyHandler(body -> {
            //反序列化请求对象
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(body.getBytes(), RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            RpcResponse rpcResponse = null;
            if(Objects.isNull(rpcRequest)){
                rpcResponse.setMessage("rpcRequest is null");
                rpcResponse.setData(null);
                doResponse(httpServerRequest,rpcResponse,serializer);
                return;
            }
            //利用反射构造响应结果对象
            try {
                Class<?> aClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = aClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getArgTypes());
                Object result = method.invoke(aClass.newInstance(), rpcRequest.getArgs());
                rpcResponse.setMessage("success");
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setData(result);
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(httpServerRequest,rpcResponse,serializer);
        });
    }

    /**
     * 响应
     * @param httpServerRequest
     * @param rpcResponse
     * @param serializer
     */
    public void doResponse(HttpServerRequest httpServerRequest,RpcResponse rpcResponse,Serializer serializer){
        HttpServerResponse response = httpServerRequest.response().putHeader("Content-type","application/json");
        try {
            byte[] bytes = serializer.serialize(rpcResponse.getData());
            response.end(Buffer.buffer(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            response.end(Buffer.buffer());
        }
    }
}
