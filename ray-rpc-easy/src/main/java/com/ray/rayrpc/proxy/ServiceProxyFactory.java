package com.ray.rayrpc.proxy;

/**
 * @Author: RayMind
 * @Date: 2024/5/26 17:36
 * @Description: 服务代理工厂  用于创建代理对象
 */
public class ServiceProxyFactory {
    /**
     * 根据服务类创建代理对象
     * @param interfaceClass
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> interfaceClass)
    {
        return (T) java.lang.reflect.Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new ServiceProxy());
    }
}
