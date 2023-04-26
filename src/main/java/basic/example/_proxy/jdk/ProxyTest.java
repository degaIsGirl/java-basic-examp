package basic.example._proxy.jdk;

import org.junit.jupiter.api.Test;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/25 14:23
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */
public class ProxyTest {
    @Test
    public void testProxy() {
        System.out.println("Client 拥有了在执行前后打印日志的能力");
        RealClient realSubject = new RealClient();
        IClient proxyClient = (IClient)DynamicProxy.getInstance().getProxyInstance(realSubject);
        proxyClient.request("client req");

        System.out.println("Server 也拥有了在执行前后打印日志的能力");
        RealServer realServer = new RealServer();
        IServer proxyServer = (IServer)DynamicProxy.getInstance().getProxyInstance(realServer);
        proxyServer.response("server resp");
    }
}
