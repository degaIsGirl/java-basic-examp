package basic.example.spring._ioc;

import basic.example.spring._ioc.day1.test1OfNoIoc.School;
import basic.example.spring._ioc.day1.test1OfNoIoc.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

public class Day1 {
    /**
     * 没有ioc之前, 依靠程序员手动创建对象并解决对象之间的依赖问题
     */
    @Test
    public void test1OfNoIoc() {
        Student student = new Student("zhangsan");
        student.setAge(18);
        School school = new School();
        school.setSchoolName("小码教育");
        school.setAddress("浙江省杭州市余杭区");
        student.setSchool(school);
        System.out.println(student);
    }

    /**
     * 有了ioc容器我们的工作发生了变化, 由原来的手动创建对象变成了如何定义bean, 让ioc容器基于bean定义来创建对象
     */
    @Test
    public void test2OfXmlIoc() {
        // 这里我们将bean的定义放在了xml中, 大家可以认为xml是bean的配置文件, 这样可以降低对源代码的影响
        String path = "_ioc/day1/test2OfXmlIoc.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        Student student = (Student) classPathXmlApplicationContext.getBean("student");

        // 运行后发现school属性也是有值的, 这是因为ioc容器帮我们完成了属性的自动注入, 我们可以看到在xml 中 autowire="byType"
        System.out.println(student);
    }

    @Test
    public void test3OfXmlAndAnnotation() {
        // 这里我们将bean的定义放在了xml中, 大家可以认为xml是bean的配置文件, 这样可以降低对源代码的影响
        String path = "_ioc/day1/test3OfXmlAndAnnotation.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        basic.example.spring._ioc.day1.test3XmlAndAnnotationIoc.Student student = (basic.example.spring._ioc.day1.test3XmlAndAnnotationIoc.Student) classPathXmlApplicationContext.getBean("student");

        // 运行后发现school属性也是有值的, 这是因为ioc容器帮我们完成了属性的自动注入, 我们可以看到在xml 中 autowire="byType"
        System.out.println(student);
    }

    @Test
    public void test4OfAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("basic.example.spring._ioc.day1.test4OfAnnotationIoc");
        context.refresh();
        basic.example.spring._ioc.day1.test4OfAnnotationIoc.Student bean = context.getBean(basic.example.spring._ioc.day1.test4OfAnnotationIoc.Student.class);
        System.out.println(bean);
    }

    /**
     * 细节:
     * 1、ioc容器是如何创建对象的, 在创建对象的过程中使用的究竟是哪一个构造函数?
     * 2、属性是如何注入的?
     *      2.1、基于类型注入:
     *          基于构造注入:
     *
     *          基于set注入:
     *      2.2、基于名称注入:
     *          基于构造注入:
     *
     *          基于set注入:
     * 3、如何获取的?
     *   3.1、基于beanName获取:
     *
     *   3.2、基于beanType获取:
     */

    /**
     * 1、ioc容器是如何创建对象的, 在创建对象的过程中使用的究竟是哪一个构造函数?
     */
    @Test
    public void test2() {
        String path = "_ioc/day1/test2.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        Student student = (Student) classPathXmlApplicationContext.getBean("student");
        System.out.println(student);

        Student student1 = (Student)classPathXmlApplicationContext.getBean("student1");
        System.out.println(student1);
    }

    /**
     * 2、属性是如何注入的?
     *      2.1、基于类型注入:
     *          byType
     *      2.2、基于名称注入:
     *          byName
     */
}
