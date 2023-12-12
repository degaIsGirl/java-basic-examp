package basic.example.day3;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/arthas")
@Slf4j
public class ArthasDebug {

    @Value("${access-token:default_token}")
    private String accessToken;

    @Resource
    Children children;

    /**
     * jad --source-only basic.example.day3.ArthasDebug > /tmp/ArthasDebug.java
     *
     * mc -d /tmp  /tmp/ArthasDebug.java
     *
     * redefine 编译后的class路径
     *
     * 我们可以动态的去添加下日志
     * @return
     */
    @GetMapping("/get-time")
    public HashMap<String, String> getTime() {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("currentMillin", System.currentTimeMillis() + "");
        log.info("log data: {}", stringStringHashMap);
        return stringStringHashMap;
    }

    @Data
    private static class Student {
        private String name;

        private String school;

        private Integer age;

        public void getById(int id) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        public boolean delStudent(int id) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }

    /**
     * watch 命令
     *
     * watch basic.example.day3.ArthasDebug addStudent "{params}" -b  -x 2
     *
     * 输出接受到的参数值, 输出对象的深度最大为2
     *
     * watch basic.example.day3.ArthasDebug addStudent "{returnObj}"  -x 1
     * 输出返回结果
     *
     * watch basic.example.day3.ArthasDebug addStudent "{target}"  -x 3
     * 输出调用当前方法对象的信息
     *
     * watch basic.example.day3.ArthasDebug addStudent "target.accessToken"
     * 输出对象的属性信息
     *
     * @param student
     * @return
     */
    @GetMapping("/add-student")
    public Student addStudent(Student student) {
        student.setAge(student.getAge() + 1);
        student.setName(this.children.getName());
        return student;
    }

    /**
     * trace 命令 计算耗时, 注意: 每次只能跟踪一级方法的调用链路
     *
     * trace basic.example.day3.ArthasDebug delStudent
     *
     * trace basic.example.day3.ArthasDebug delStudent '#cost > 200'
     * 添加过滤条件, 当方法执行耗时超过指定时间(单位是毫秒)才记录
     *
     * @param id
     * @return
     */
    @GetMapping("/del-student")
    public Student delStudent(int id) {
        Student student = new Student();
        student.getById(id);
        student.delStudent(id);
        if (id%2 == 1) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    /**
     * tt (timetunnel) 时空隧道, 是一个非常厉害的命令: 可以实现流量重放
     *
     * -t, --time-tunnel                                             Record the method invocation within time fragments
     * -n, --limits <value>                                          Threshold of execution times
     * -p, --play                                                    Replay the time fragment specified by index
     * -i, --index <value>                                           Display the detailed information from specified time fragment
     *
     * tt -t -n 3 basic.example.day3.ArthasDebug getTime
     * 表示将basic.example.day3.ArthasDebug getTime方法记录到时空隧道中, 只记录3次, 避免流量过大导致jvm异常
     *
     * tt -t -n 3 basic.example.day3.ArthasDebug delStudent "params[0]%2 == 1"
     * 表示将basic.example.day3.ArthasDebug delStudent方法记录到时空隧道中, 但是必须满足条件params[0]%2 == 1, 也就是说接受的参数需要是一个奇数
     *
     * tt -i 时间碎片编号 -p
     * 表示以 指定的时间碎片的编号的入参信息 重新执行一遍, 注意这里需要加上-p参数, 否则不会真正的执行
     */
}
