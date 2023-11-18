package basic.example.spring._ioc.day1.test1OfNoIoc;

import lombok.Data;

@Data
public class School {
    private String schoolName;

    private String address;

    public School() {
        System.out.println("school 的默认构造函数得到执行");
    }
}
