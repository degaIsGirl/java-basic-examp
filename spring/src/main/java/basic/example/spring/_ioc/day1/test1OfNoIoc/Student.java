package basic.example.spring._ioc.day1.test1OfNoIoc;

import lombok.Data;

@Data
public class Student {
    private String name;

    private int age;

    private School school;

    public Student() {
        System.out.println("student 的默认构造函数执行");
    }

    public Student(String name) {
        System.out.println("student 带有name参数的构造方法得到执行");
        this.name = name;
    }

    public Student(School school) {
        System.out.println("student 带有school参数的构造方法得到执行");
        this.school = school;
    }
}
