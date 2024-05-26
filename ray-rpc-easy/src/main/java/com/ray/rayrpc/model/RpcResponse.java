package com.ray.rayrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: RayMind
 * @Date: 2024/5/26 12:12
 * @Description: RPC 相应
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse {

    /**
     * 相应数据
     */
    private Object data;
    /**
     * 相应信息
     */
    private String message;
    /**
     * 相应数据类型
     */
    private Class<?> dataType;
    /**
     * 异常信息
     */
    private Exception exception;
}
