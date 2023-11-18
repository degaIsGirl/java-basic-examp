package basic.example.spring._ioc.test4.test1.advice;

import basic.example.spring._ioc.test4.IShop;
import org.springframework.stereotype.Component;

@Component
public class Shop1 implements IShop {
    @Override
    public String order(String orderNo) {
        System.out.println("shop class 执行 order 方法");
        return null;
    }

    @Override
    public String stock() {
        System.out.println("shop class 执行 stock 方法");
        int res = 1/0;
        return null;
    }
}
