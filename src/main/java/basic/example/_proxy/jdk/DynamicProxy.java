package basic.example._proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/25 14:10
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */

/**
 * JDK动态代理
 * 核心的类是Proxy和InvocationHandler
 * Proxy是代理类，InvocationHandler是代理类的调用处理器
 * Jdk被代理的类必须实现接口
 * InvocationHandler的invoke方法是代理类的调用处理器，可以在这里做一些额外的处理
 * @param <T>
 */
public class DynamicProxy<T> implements InvocationHandler {

    T target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before " + method.getName() + " invoke");
        Object invoke = method.invoke(target, args);
        System.out.println("after " + method.getName() + " invoke");
        return invoke;
    }

    public Object getProxyInstance(T target) {
        this.target = target;
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    public static <T> DynamicProxy<T> getInstance() {
        return new DynamicProxy<T>();
    }
}
