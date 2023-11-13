package basic.examaple.java._stream;

import lombok.Data;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Show {
    @Data
    static class Student {
        private String name;
        private Double score;
        private String nickName;

        public Student(String name, Double score, String nickName) {
            this.name = name;
            this.score = score;
            this.nickName = nickName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(nickName, student.nickName);
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    public static void main(String[] args) {
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
                ).peek((item) ->  {
                    if (item.getName().equals("sun3")) {item.setScore(100.0);}
                })
                .collect(
                        Collectors.toList()
                );
        System.out.println(collect);
    }
}