package basic.example._proxy.jdk;

import basic.example._proxy.jdk.IClient;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/25 14:09
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */
public class RealClient implements IClient {
    @Override
    public void request(String data) {
        System.out.println("do request, data is: " + data);
    }
}
