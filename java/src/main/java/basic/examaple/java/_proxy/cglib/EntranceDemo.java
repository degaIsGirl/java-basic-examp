package basic.examaple.java._proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class EntranceDemo {

    public static class MyMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object object,
                                Method method,
                                Object[] objects,
                                MethodProxy methodProxy) throws Throwable {
            System.out.println("start exec method " + method.getName());
            Long start = System.currentTimeMillis();
            // 没有被代理的对象, 通过调用父方法完成业务方法的调用
            methodProxy.invokeSuper(object, objects);
            Long end = System.currentTimeMillis();
            long useTime = end - start;
            System.out.println("end exec method " + method.getName() + " use time " + useTime);
            return "change result";
        }
    }

    /**
     * 需求: 需要计算出IShop接口方法中的耗时
     *
     * cglib动态代理的核心是Enhancer, 底层通过extend的方式动态的创建一个子类去执行方法
     *
     * 实现原理可以参考下面的伪代码:
     * @// TODO: 2023/11/12
     * public class Jd$Proxy extend Jd {
     *
     * }
     *
     * @param args
     */
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 设置类加载器
        enhancer.setClassLoader(Jd.class.getClassLoader());
        // 设置动态代理的父类
        enhancer.setSuperclass(Jd.class);
        // 设置动态代理的回调, 所有动态代理能够继承到的方法, 都会被转发到intercept中
        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor();
        enhancer.setCallback(myMethodInterceptor);
        // 创建动态代理对象
        Jd jdProxy = (Jd)enhancer.create();

        // 执行被代理的方法
        String res = jdProxy.pay("123");
        System.out.println(res);

        // 经过测试发现 final 方法并没有被代理, 如果Jd 是final将会直接报错, 错误信息如下:
        // Cannot subclass final class basic.examaple.java._proxy.cglib.Jd
        boolean stock = jdProxy.stock();
        System.out.println(stock);
    }
}
