package basic.example.day1.requestmapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("debug")
    public String debug() {
        System.out.println("debug");
        return "debug exec";
    }

    @GetMapping("throw")
    public String throwIt() {
        System.out.println("thorw");
        int a = 1/0;
        return "thorwIt";
    }
}
