package basic.example.day1.requestmapping;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private String time = System.currentTimeMillis() + "";

    @GetMapping("debug1")
    public String debug(String name) {
        System.out.println("debug");
        return "debug exec " + name;
    }

    @GetMapping("throw")
    public String throwIt() {
        System.out.println("thorw");
        int a = 1/0;
        return "thorwIt";
    }

    private static class Student {
        private String name;

        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

    @GetMapping("/get-student")
    public String getStudent(Student student, @RequestHeader("fuck") String fuck) {
        return student.getName();
    }

    @GetMapping("/sleep")
    public String sleep(Student student, @RequestHeader("fuck") String fuck) {
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return student.getName();
    }
}
