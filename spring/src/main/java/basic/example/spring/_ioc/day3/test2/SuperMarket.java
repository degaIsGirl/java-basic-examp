package basic.example.spring._ioc.day3.test2;

import basic.example.spring._ioc.day3.IShop;
import org.springframework.stereotype.Component;

@Component
public class SuperMarket implements IShop {
    @Override
    public String order(String orderNo) {
        System.out.println("\tSuperMarket order exec , orderNo => " + orderNo);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "SuperMarket_Order_Res";
    }

    @Override
    public String stock() {
        System.out.println("SuperMarket stock exec");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
