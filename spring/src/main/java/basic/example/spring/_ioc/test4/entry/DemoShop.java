package basic.example.spring._ioc.test4.entry;

import basic.example.spring._ioc.test4.IShop;
import org.springframework.stereotype.Component;

/**
 * demo例子
 */
@Component
public class DemoShop implements IShop {

    @Override
    public String order(String orderNo) {
        System.out.println("DemoShop order exec , orderNo => " + orderNo);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "DemoShop_Order_Res";
    }

    @Override
    public String stock() {
        System.out.println("SuperMarket stock exec");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "DemoShop_Stock_Res";
    }
}
