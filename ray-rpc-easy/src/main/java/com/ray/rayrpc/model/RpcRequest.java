package com.ray.rayrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RayMind
 * @Date: 2024/5/26 12:12
 * @Description: RPC 请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RpcRequest {

    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法参数
     */
    private Object[] args;
    /**
     * 方法参数类型
     */
    private Class<?>[] argTypes;
}
