package basic.example._proxy.jdk;

import basic.example._proxy.jdk.IServer;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/25 14:27
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */
public class RealServer implements IServer {
    @Override
    public void response(String data) {
        System.out.println("do response, data is: " + data);
    }
}
