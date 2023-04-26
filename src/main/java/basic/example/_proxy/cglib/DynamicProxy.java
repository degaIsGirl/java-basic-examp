package basic.example._proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/25 17:56
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */

/**
 * Cglib动态代理
 * 同jdk动态代理不同的是，cglib动态代理不需要被代理的类实现接口, 但是被代理的类不能是final的;
 * Cglib动态代理的原理是通过继承被代理的类，然后重写被代理的方法，然后在重写的方法中做一些额外的处理;
 * Cglib动态代理的核心类是Enhancer和MethodInterceptor
 * Enhancer会生成一个被代理class的子类, 这个子类是被代理类的代理class
 *      核心方法主要有: setSuperclass(设置被代理的子类), setCallback(设置回调, 用于实现被代理方法功能上的增强), create(创建代理类)
 */
public class DynamicProxy<T> implements MethodInterceptor {
    public <T> T getProxyInstance(T target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before " + method.getName() + " invoke");
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("after " + method.getName() + " invoke");
        return o;
    }

    public static <T> DynamicProxy<T> getInstance() {
        return new DynamicProxy<>();
    }
}
