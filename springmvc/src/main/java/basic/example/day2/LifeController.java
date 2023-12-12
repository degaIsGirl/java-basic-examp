package basic.example.day2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LifeController {
    @Resource
    MyBeanLifeCycle myBeanLifeCycle;

    @GetMapping("life")
    public String life() {
        return this.myBeanLifeCycle.debug();
    }
}
