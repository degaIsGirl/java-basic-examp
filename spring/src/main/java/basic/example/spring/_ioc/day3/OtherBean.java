package basic.example.spring._ioc.day3;

import org.springframework.stereotype.Component;

@Component
public class OtherBean {
    public String getInfo() {
        return "otherBean";
    }
}
