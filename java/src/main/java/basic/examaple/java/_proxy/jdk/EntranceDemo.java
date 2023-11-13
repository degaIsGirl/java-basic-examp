package basic.examaple.java._proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EntranceDemo {

    public static class MyCallback implements InvocationHandler {

        private Object target;

        public MyCallback(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Long start = System.currentTimeMillis();
            method.invoke(this.target, args);
            Long end = System.currentTimeMillis();
            long useTime = end - start;
            System.out.println("exec method " + method.getName() + " use time " + useTime);
            return null;
        }
    }

    /**
     * 需求: 需要计算出IShop接口方法中的耗时
     *
     * Jdk 动态代理的核心是Proxy和Invocation
     *     Proxy是用来创建动态代理对象的, Invocation 可以认为是回调: 当我们调用被代理对象的方法时该方法都会被调用
     *
     * 局限性:
     *      a、被代理的类必须实现至少一个接口
     *      b、被代理的方法范围仅限于接口中的方法
     *
     * 注意点:
     *      通过Proxy创建的代理对象, 强转后的类型只能为接口类型, 而不能是具体的实现
     *
     * 实现的大致原理:
     *  我们可以认为JdkProxy默认帮我们生成了如下的一段代码:
     *
     *     public class Jd$Proxy implements IShop {
     *
     *         private InvocationHandler invocationHandler;
     *
     *         public String pay(String orderNo) {
     *                 return invocationHandler.invoke(
     *                      this,
     *                      method,
     *                      args[]
     *                 );
     *         }
     *     }
     *
     * @param args
     */
    public static void main(String[] args) {
        Jd jd = new Jd();
        // 这里如果我们换成(Jd)将会报错
        IShop jdShop = (IShop) Proxy.newProxyInstance(
                IShop.class.getClassLoader(),
                new Class[]{IShop.class},
                new MyCallback(jd)
        );
        jdShop.pay("123");

        Tb tb = new Tb();
        IShop tbShop = (IShop) Proxy.newProxyInstance(
                IShop.class.getClassLoader(),
                new Class[]{IShop.class},
                new MyCallback(tb)
        );
        tbShop.pay("321");
    }
}
