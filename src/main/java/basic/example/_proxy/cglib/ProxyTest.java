package basic.example._proxy.cglib;

import org.junit.jupiter.api.Test;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/25 19:02
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */
public class ProxyTest {

    /**
     * 动态代理
     * 是和静态代理相对的，静态代理是在编译期间就确定了代理类，而动态代理是在运行期间确定代理类
     * 之所以说是动态,是因为代理对象是在运行时动态生成的, 我们无需编写源代码
     *
     * 动态代理有什么好处呢?
     * 1、当多个被代理对象需要增加相同的功能时，可以使用动态代理，只需要编写一个代理类，就可以为多个被代理类提供相同的功能
     * 2、当被代理对象的功能发生变化时，只需要修改代理类，不需要修改被代理类
     */
    @Test
    public void testProxy() {
        System.out.println("Client 拥有了在执行前后打印日志的能力");
        RealClient realClient = new RealClient();
        RealClient proxyClient = DynamicProxy.getInstance().getProxyInstance(realClient);
        proxyClient.request("client req");

        System.out.println("Server 拥有了在执行前后打印日志的能力");
        RealServer realServer = new RealServer();
        RealServer proxyServer = DynamicProxy.getInstance().getProxyInstance(realServer);
        proxyServer.response("server resp");
    }
}
