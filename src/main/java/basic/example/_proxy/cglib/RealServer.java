package basic.example._proxy.cglib;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/25 17:55
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */
public class RealServer {
    public void response(String data) {
        System.out.println("do response, data is: " + data);
    }
}
