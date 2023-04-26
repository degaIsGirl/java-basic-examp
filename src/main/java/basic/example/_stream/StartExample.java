package basic.example._stream;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/20 15:54
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */
public class StartExample {

    /**
     * 入门案例
     */
    @Test
    public void begin() {
        List<Student> students = Arrays.asList(new Student("zhao1", 99.0, "1"),
                new Student("qian2", 100.0, "2"),
                new Student("sun3", 98.0, "3"),
                new Student("li4", 99.0, "4"),
                new Student("zhou5", 100.0, "5"),
                new Student("wu6", 98.0, "6"),
                new Student("zheng7", 99.0, "7"));

        /**
         * 去重后按照分数从高到低排序(分数相同-按照名字昵称排序, 昵称字母越小越靠前),
         * 取出前三名, 并转换为list
         */
        List<Student> collect = students.stream()
                .distinct()
                .sorted(
                        Comparator.comparing(
                                        Student::getScore,
                                        Comparator.reverseOrder()
                                )
                                .thenComparing(
                                        Student::getNickName,
                                        Comparator.naturalOrder()
                                )
                )
                .limit(3)
                .collect(
                        Collectors.toList()
                );
        collect.forEach(System.out::println);
    }
}
