package basic.example.spring._ioc.day3.test1.pointcut;

import org.springframework.stereotype.Component;

@Component
public class SubShop  extends Shop2 {
    public String order(String orderNo) {
        System.out.println("SubShop orderNo " + orderNo);
        return "SubShop_Order_Res";
    }
}
