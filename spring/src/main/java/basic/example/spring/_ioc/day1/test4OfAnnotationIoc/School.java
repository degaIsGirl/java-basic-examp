package basic.example.spring._ioc.day1.test4OfAnnotationIoc;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class School {
    private String schoolName;

    private String address;

    public School() {
        System.out.println("school 的默认构造函数得到执行");
    }
}
