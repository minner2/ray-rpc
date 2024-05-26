package com.ray.rayrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author: RayMind
 * @Date: 2024/5/26
 * @Description: 本地注册中心
 */
public class LocalRegistry {

    /**
     * 注册服务存储
     */
    private static final Map<String,Class<?>> map = new ConcurrentHashMap<>();

    /**
     * 注册服务
     * @param serviceName 服务名
     * @param implClass 实现类
     */
    public static void register(String serviceName,Class<?> implClass){
        map.put(serviceName,implClass);
    }
    /**
     * 获取服务
     * @param serviceName 服务名
     * @return 实现类
     */
    public static Class<?> get(String serviceName){
        return map.get(serviceName);
    }
    /**
     * 删除服务
     * @param serviceName 服务名
     */
    public static void remove(String serviceName){
        map.remove(serviceName);
    }

}
