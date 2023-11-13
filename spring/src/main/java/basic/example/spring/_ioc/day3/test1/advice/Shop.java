package basic.example.spring._ioc.day3.test1.advice;

import basic.example.spring._ioc.day3.IShop;
import org.springframework.stereotype.Component;

@Component
@Test1
public class Shop implements IShop {
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
