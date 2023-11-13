package basic.example.spring._ioc.day2;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Day2 {
    /**
     * 1、入门案例
     *
     * 通过组件扫描的方式, 添加bean对象
     * 通过@Value注解完成简单属性的注入 (@Value 中可以使用el表达式), 具体的el表达可以单独的讲一下
     * 通过@Resource 或者 @Autowired 方式完成引用属性的注入
     */
    @Test
    public void test1() {
        String path = "day2/test1.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        Student student = (Student) classPathXmlApplicationContext.getBean("student");
        System.out.println(student);
    }

    /**
     * 2、@Autowired和@Resource都是完成引用类型的注入, 有什么区别么?
     * @Autowired 是先基于类型注入, 发现有多个类型实现的时候, 再基于beanName注入, beanName参考属性名称;
     * 如果我们希望脱离属性名称, 指定beanName需要配合@Qulifier(beanName) 这个注解
     *
     * @Resource 是基于beanName进行注入, beanName默认就是属性名称, 如果对应的beanName不存在, 在使用beanType;
     * 如果我们希望脱离属性名称, 指定beanName, 可以直接在@Resource(name = beanName), 无需其它注解进行搭配
     *
     * 另一个比较显著的区别是, @Autowired 可以使用在构造函数上, 从而指定ioc容器创建对象时执行的构造函数, 而@Resource不可以
     */
    @Test
    public void test2() {
        String path = "day2/test1.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        Teacher teacher = (Teacher)classPathXmlApplicationContext.getBean("teacher");
        System.out.println(teacher);
    }
}
