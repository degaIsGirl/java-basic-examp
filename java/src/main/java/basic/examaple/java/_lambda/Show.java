package basic.examaple.java._lambda;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Show {
    @Data
    static class Student {
        private String name;
        private Double score;
        private String nickName;

        public String getName() {
            return name.toUpperCase();
        }

        public String getName(Student student) {
            return student.getName();
        }

        public Student(String name, Double score, String nickName) {
            this.name = name;
            this.score = score;
            this.nickName = nickName;
        }

        public Student(String name) {
            this.name = name;
        }

        public Student() {
        }

        public boolean equals(Student student) {
            if (student.getNickName().equals(this.getNickName())) {
                return true;
            } else {
                return false;
            }
        }

        public Double getScore() {
            return score;
        }

        public String getNickName() {
            return nickName;
        }

        public Student setNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }
    }

    /**
     * 案例1-无参数&无返回值
     */
    @Test
    public void testNoReturnAndParams() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread run");
            }
        }).start();

        // lambda表达式
        new Thread(() -> System.out.println("Thread run")).start();
    }

    /**
     * 案例2-有参数&无返回值
     * @param
     * @return
     */
    @Test
    public void testNoReturnHadParams() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("hello world");

        // lambda表达式
        Consumer<String> consumer1 = (String s) -> {
            System.out.println(s);
        };
        consumer1.accept("hello world");
    }


    public static String convert(Student student) {
        return student.getName();
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
         * Function接口用于类型转换, 接受一个参数, 返回一个结果.
         *
         * 将定现在有一个要求将获取所有学生的姓名, 并转换为大写, 然后放入到一个List中
         *
         */
        Function<Student, String> demo = new Function<Student, String> () {
            /**
             * 这里我们需要一个参数Student, 然后返回一个String; 但是, 很明显Student类的getName方法没有接受任何的参数, 参数不一致但是依然可以使用
             * 因为实例方法有一个隐含的this参数，Student类的getName()方法在实际调用的时候，第一个隐含参数总是传入this，相当于静态方法：
             * public static String getName(this);
             * 这个地方不太好理解, 我们只需要记住:
             *
             * @param student the function argument
             * @return
             */
            @Override
            public String apply(Student student) {
                return student.getName();
            }
        };
        // 我们通过匿名函数实现了Function接口
        List<String> name = getName(students, demo);

        // 我们可以通过lambda表达式实现
        List<String> name1 = getName(students, student -> student.getName());

        /**
         * 我们可以通过方法引用实现, 主要的使用场景, 这个方法在类中已经实现了, 我们只需要调用即可
         * 1.引用静态方法
         * 2.引用实例方法
         *   2.1.引用实例方法, 但是参数一致
         *   2.2.引用实例方法, 但是参数不一致
         * 3.引用构造方法
         */

        // 1.引用静态方法
        Function<Student, String> function1 = Show::convert;

        // 2.1.引用实例方法, 但是参数一致. ::的左面是一个对象, 右面是一个对应的对象方法
        Function<Student, String> function2 = new Student()::getName;

        // 2.2.引用实例方法, 但是参数不一致. ::的左面是一个类名称, 右面是一个对应的对象方法
        // 这里我们记住即可, 其实这个不一致是有要求的，就是函数式接口的参数个数-非静态方法参数个数=1, 这里的1就是this.
        // 如果我们使用Student::getName写法, 可以作一个假设: 如果给Student类的getName方法, 添加一个参数 this, 是否满足当前函数式接口的实现?
        // 如果能实现, 那么就可以使用这种方式

        // String getName(Student this); 记住this是隐含的, 而且只添加到第一个参数的位置上
        Function<Student, String> function3 = Student::getName;
        List<String> list = Show.getName(students, function3);
        System.out.println(list);

        // 3.引用构造方法
        Function<String, Student> function4 = Student::new;

        String[] array = {"wu6", "zheng7"};
        List<Student> students1 = createStudentListByArray(array, function4);
        System.out.println(students1);
        // 通过stream去实现, 更加的简洁和高效
        //List<Student> collect = Arrays.stream(array).map(Student::new).collect(Collectors.toList());
    }

    public static List<String> getName(List<Student> list, Function<Student, String> function) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            Student next = iterator.next();
            stringArrayList.add(function.apply(next));
        }
        return stringArrayList;
    }

    public static List<Student> createStudentListByArray(String[] array, Function<String, Student> function) {
        ArrayList<Student> students = new ArrayList<>();
        for (String s : array) {
            students.add(function.apply(s));
        }
        return students;
    }
}
