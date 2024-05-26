package com.ray.rayrpc.serializer;

/**
 * @Author: RayMind
 * @Date: 2024/5/26 12:03
 * @Description: 序列化器接口
 */
public interface Serializer {

    /**
     * 序列化
     * @param obj
     * @return
     * @param <T>
     * @throws Exception
     */
    <T> byte[] serialize(T obj) throws Exception;

    /**
     * 反序列化
     * @param bytes
     * @param type
     * @return
     * @param <T>
     * @throws Exception
     */
    <T> T deserialize(byte[] bytes,Class<T> type) throws Exception;
}
