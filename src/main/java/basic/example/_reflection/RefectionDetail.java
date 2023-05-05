package basic.example._reflection;

import basic.example._stream.StreamDetail;
import org.junit.jupiter.api.Test;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Copyright(C), 2020 - 2023, 小码教育
 *
 * @Date: 2023/4/26 09:11
 * @Description:
 * @Author: mawb<mawb @ xiaoma.cn>
 */
@Validated(RefectionDetail.IClientGroup.class)
@Deprecated
public class RefectionDetail {

    private boolean isDebug;

    public interface IClientGroup {

    }

    static class Person {
        private double salary;

        protected int sex;

        public String name;

        protected String getName() {
            return this.name;
        }

        public int getSex() {
            return this.sex;
        }
    }

    static class Student extends Person implements Comparable<Student> {
        private Double score;

        public int grade;

        private static String schoolName = "default value";

        public static String getSchoolName() {
            return schoolName;
        }

        public static String setSchoolName(String name) {
            return schoolName = name;
        }

        protected int getGrade() {
            return this.grade;
        }

        public double getScore() {
            return this.score;
        }

        public double setScore(double score) {
            this.score = score;
            return this.score;
        }

        @Override
        public int compareTo(Student o) {
            return this.score.compareTo(o.score);
        }

        public Student(Double score, String name) {
            this.score = score;
            this.name = name;
        }
    }

    /**
     * java的面向对象的构成元素
     * 1 class
     *  1.1 属性
     *  1.2 方法
     *  1.3 代码块
     *  1.4 内部类
     *  1.5 构造方法
     * 2 interface
     * 3 enum
     * 4 annotation
     * 5 package
     */

    @Test
    public void testBasicElement() {
        Class<StreamDetail> streamDetailClass = StreamDetail.class;
        // 是否是接口
        assertFalse(streamDetailClass.isInterface());
        // 是否是枚举
        assertFalse(streamDetailClass.isEnum());
        // 是否是注解
        assertFalse(streamDetailClass.isAnnotation());
        // 是否为数据类型
        assertFalse(streamDetailClass.isPrimitive());
        // 是否为数组
        assertFalse(streamDetailClass.isArray());
        // 是否是某一个类的对象
        StreamDetail streamDetail = new StreamDetail();
        assertTrue(streamDetailClass.isInstance(streamDetail));
    }

    private enum SexEnum {
        /**
         * 性别
         */
        MALE(1, "男"),
        FEMALE(2, "女");

        private int code;

        private String desc;

        SexEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    /**
     * 1、包名称
     * 2、注解
     * 3、待实现的接口
     * 4、继承的父类
     * 5、成员变量
     * 6、构造方法
     * 7、普通的方法
     * 8、内部类
     */

    /**
     * 1、包名称
     */
    @Test
    public void testPackages() {
        Class<RefectionDetail> refectionDetailClass = RefectionDetail.class;
        Package aPackage = refectionDetailClass.getPackage();
        System.out.println(aPackage.getName());
    }

    /**
     * 2、注解
     */
    @Test
    public void testAnnotation() {
        Class<RefectionDetail> refectionDetailClass = RefectionDetail.class;
        /**
         * 获取类上面所有的注解
         */
        Annotation[] annotations = refectionDetailClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Validated) {
                System.out.println("true: " + annotation.getClass().getName());
            } else {
                System.out.println("false: " + annotation.getClass().getName());
            }
        }

        /**
         * 指定的注解在类上是否存在
         */
        boolean annotationPresent = refectionDetailClass.isAnnotationPresent(Validated.class);

        /**
         * 如果注解存在, 则调用该注解的对应方法, 获取数据
         */
        if (annotationPresent) {
            Validated annotation = refectionDetailClass.getAnnotation(Validated.class);
            Class<?>[] value = annotation.value();
            for (Class<?> aClass : value) {
                System.out.println(aClass);
            }
        }
    }

    /**
     * 3、接口测试
     */
    @Test
    public void testInterface() {
        Class<Student> studentClass = Student.class;
        Class<?>[] interfaces = studentClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }

        /**
         * 判断是否实现了某个接口
         * isAssignableFrom: 判断是否是某个类的父类
         */
        boolean assignableFrom = Comparable.class.isAssignableFrom(studentClass);
        System.out.println(assignableFrom);
    }

    /**
     * 4、父类
     */
    @Test
    public void testParentClass() {
        Class<Student> studentClass = Student.class;
        Class<? super Student> superclass = studentClass.getSuperclass();
        System.out.println(superclass.getName());

        /**
         * 判断是否是某个类的子类
         */
        boolean assignableFrom = superclass.isAssignableFrom(studentClass);
        System.out.println(assignableFrom);

        int modifiers = superclass.getModifiers();
        System.out.println(modifiers);
    }

    /**
     * 5、成员变量
     */
    @Test
    public void testField() throws NoSuchFieldException, IllegalAccessException {
        Class<Student> studentClass = Student.class;
        System.out.println("仅能够获取当前的类的成员变量, 不能获取父类的成员变量");
        Field[] declaredFields = studentClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        System.out.println("可以获取当前类以及父类的所有的public修饰的成员变量");
        Field[] fields = studentClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

 /*       System.out.println("返回本类&父类中public级别的字段, 找不到就报错");
        Field name = studentClass.getField("name");
        System.out.println(name.getName());
        //由于score是private级别的, 所以会报错
        Field score = studentClass.getField("score");
        System.out.println(score.getName());*/

        System.out.println("返回本类中所有的字段, 找不到就报错");
        Field scorePrivate = studentClass.getDeclaredField("score");
        if (!scorePrivate.isAccessible()) {
            scorePrivate.setAccessible(true);
        }

        /**
         * 非静态属性值是要挂在对象上面的, 所以一定要有对象
         */
        Student student = new Student(100.0, "mawb");
        Double score = (Double)scorePrivate.get(student);
        System.out.println("name is " + scorePrivate.getName() + ", value is " + score);
        scorePrivate.set(student, 200.0);
        score = (Double)scorePrivate.get(student);
        System.out.println("name is " + scorePrivate.getName() + ", value is " + score);

        /**
         * 静态属性值是要挂在类上面的, 所以不需要对象, 对应的值我们填写null即可
         */
        System.out.println("返回本类中静态filed");
        Field schoolName = studentClass.getDeclaredField("schoolName");
        int modifiers = schoolName.getModifiers();
        System.out.println("name is " + schoolName.getName() + ", modifiers is " + modifiers);
        if (!schoolName.isAccessible()) {
            schoolName.setAccessible(true);
        }
        String schoolNameValue = (String)schoolName.get(null);
        System.out.println("name is " + schoolName.getName() + ", value is " + schoolNameValue);
        schoolName.set(null, "mawb");
        schoolNameValue = (String)schoolName.get(null);
        System.out.println("name is " + schoolName.getName() + ", value is " + schoolNameValue);
        // 是否是public
        System.out.println(Modifier.isPublic(modifiers));
        // 是否是static
        System.out.println(Modifier.isStatic(modifiers));
    }

    /**
     * 内部类
     */
    @Test
    public void testInnerClass() throws NoSuchFieldException {
        Class<RefectionDetail> refectionDetailClass = RefectionDetail.class;
        System.out.println(refectionDetailClass.isMemberClass());
        Class<Person> personClass = Person.class;
        System.out.println(personClass.isMemberClass());

        Field[] declaredFields = refectionDetailClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
    }

    /**
     * 6、普通方法
     */
    @Test
    public void testMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<Student> studentClass = Student.class;

        System.out.println("获取当前类的所有方法, 包括public, protected, private, default");
        Method[] declaredMethods = studentClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }

        System.out.println("获取当前类以及父类的所有public方法");
        Method[] methods = studentClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        System.out.println("获取指定的方法");
        Student student = new Student(100.0, "zhangsan");
        Method getGrade = studentClass.getMethod("getScore");
        if (getGrade.isAccessible()) {
            getGrade.setAccessible(true);
        }
        Object invoke = getGrade.invoke(student);
        System.out.println(invoke);

        System.out.println("调用指定的方法并传入值");
        Method setScore = studentClass.getMethod("setScore", double.class);
        if (!setScore.isAccessible()) {
            setScore.setAccessible(true);
        }
        double res = (double)setScore.invoke(student, 200.0);
        System.out.println(res);

        System.out.println("调用静态方法");
        Method getSchoolOfStatic = studentClass.getMethod("getSchoolName");
        String schoolName = (String)getSchoolOfStatic.invoke(null);
        System.out.println(schoolName);

        System.out.println("调用静态方法, 更改静态属性值");
        studentClass.getMethod("setSchoolName", String.class).invoke(null, "another value");
        String getSchoolName = (String) studentClass.getMethod("getSchoolName").invoke(null);
        System.out.println(getSchoolName);
    }

    /**
     * 7、构造方法
     */
    @Test
    public void testConstruct() {
        Class<Student> studentClass = Student.class;
        System.out.println("获取当前类的所有构造方法, 包括public, protected, private, default");
        Constructor<?>[] declaredConstructors = studentClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getName());
        }

        System.out.println("获取当前类以及父类的所有public构造方法");
        Constructor<?>[] constructors = studentClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.getName());
        }

        System.out.println("获取指定的构造方法");
        try {
            Constructor<Student> constructor = studentClass.getConstructor(Double.class, String.class);
            Student student = constructor.newInstance(100.0, "zhangsan");
            System.out.println(student.getName());
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 8、枚举
     */
    @Test
    public void testEnum() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<SexEnum> sexEnumClass = SexEnum.class;
        System.out.println("获取枚举的所有属性");
        Field[] fields = sexEnumClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        SexEnum[] enumConstants = sexEnumClass.getEnumConstants();
        for (SexEnum enumConstant : enumConstants) {
            System.out.println(enumConstant);
        }
    }
}
