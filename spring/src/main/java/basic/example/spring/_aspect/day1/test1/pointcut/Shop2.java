package basic.example.spring.aspect.day1.test1.pointcut;

import basic.example.spring.aspect.day1.IShop;
import org.springframework.stereotype.Component;

@Component
@Test1
public class Shop2 implements IShop {
    @Override
    @Test1
    public String order(String orderNo) {
        System.out.println("shop class 执行 order 方法");
        return "Shop_Order_Res";
    }

    @Override
    public String stock() {
        System.out.println("shop class 执行 stock 方法");
        int res = 1/0;
        return "Shop_Stock_Res";
    }
}