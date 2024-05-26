package com.ray.rayrpc.serializer;

import java.io.*;

/**
 * @Author: RayMind
 * @Date: 2024/5/26 12:03
 * @Description: JDK 序列化器
 */
public class JdkSerializer implements Serializer{
    @Override
    public <T> byte[] serialize(T obj) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(obj);
        objectOutputStream.close();
        return out.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        try {
            return (T) objectInputStream.readObject();
        }catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            objectInputStream.close();
        }
    }
}
