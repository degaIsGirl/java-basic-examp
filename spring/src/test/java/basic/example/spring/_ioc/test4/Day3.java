package basic.example.spring._ioc.test4;

import basic.example.spring._ioc.test4.test1.advice.LogAspectOfAdvice;
import basic.example.spring._ioc.test4.test1.pointcut.Shop2;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 正式学习下aop
 * 1、入门demo
 * 2、关于aop编程思想的介绍, 主要是概念相关的
 * 3、详细介绍
 */
public class Day3 {

    /**
     * 1、入门demo
     * 要求:统计day3包下所有public方法执行耗时
     */
    @Test
    public void entryExam() {
        String path = "day3/entry.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        IShop shop = (IShop)classPathXmlApplicationContext.getBean("demoShop");

        // order 方法的执行耗时
        shop.order("321");
    }

    /**
     * 2、关于aop编程思想的介绍, 主要是概念相关的
     *
     * 2.1、什么是aop? 和oop是什么关系?
     *      这里需要介绍下aop(Aspect Oriented Programming 面向切面编程), 同oop一样也是一种复用代码的方式(往大了说是一种编程思想).
     *
     *      我们可以通过对比的方法来了解aop:
     *      a、核心元素不同:
     *           oop: Object Oriented Programming 面向对象编程, 核心的元素是对象;
     *           aop: Aspect Oriented Programming 面向切面编程, 核心元素是切面;
     *
     *      b、复用代码的方式不同
     *          oop: 主要是通过对象之间的继承、组合完成代码的复用
     *          aop: 主要是通过对切面的织入完成代码的复用
     *
     *      c、使用场景不同
     *          oop: 一般用户完成我们的核心业务逻辑, 例如电商中下订单的场景,其中计算金额、扣减库存、计算优惠等都属于核心业务逻辑
     *          aop: 一般用于对核心业务逻辑的补充和辅助, 同样以电商下订单的场景为例: 其中记录请求的参数日志、记录整个方法完成的耗时、事务的管理等等
     *          由此可见aop是对oop不擅长处理场景的补充, 完成业务代码和辅助代码的隔离
     *
     *  2.2、对aop有一个大致的了解后, 需要理解相关的概念
     *      3个关键的概念 =>
     *          切面: 我们编写辅助功能的地方, 在入门案例中我们编写的LogAspect 就是一个切面, 相关计算耗时的代码都在这里. 解决what的问题
     *          切入点: 切面编写完毕我们需要在那些地方进行使用呢? 切入点解决了where的问题, 参照LogAspect中的@Pointcut注解
     *          通知: what 和 where的问题解决后, 还差一个when, 也就是说我们什么时候织入切面功能, 参照LogAspect中的@Before注解
     *
     *      周边的概念, 知道就好 =>
     *          织入(Weaving): 2.1中我们提到了织入这个关键词, 其实织入就是按照我们的要求在指定的切入点, 指定的时机(通知), 执行切面代码的这个过程;
     *      这里我们只需要了解相关的概念即可,不必陷入细节之中, 后面会详细介绍相关的语法规则.
     *
     *          连接点: 我们可以简单的认为它就是一个具体的切入点, 通常切入点是对满足指定规则方法的描述, 入门案例中:是basic.example.spring._ioc.day3下所有的public方法
     *      其中Shop对象的order方法符合规则, 我们就可以认为他是一个连接点.
     *
     */

    /**
     * 3、详细介绍
     * spring 中如何使用aop
     * a、 引入相关依赖:
     *         <!--spring boot 项目-->
     *         <dependency>
     *             <groupId>org.springframework.boot</groupId>
     *             <artifactId>spring-boot-starter-aop</artifactId>
     *         </dependency>
     *
     *         <!-- spring 项目 -->
     *         <dependency>
     *             <groupId>org.springframework</groupId>
     *             <artifactId>spring-aspects</artifactId>
     *         </dependency>
     *
     * b、开启切面对AspectJ的支持
     *      两种方式:
     *              注解方式 => @EnableAspectJAutoProxy, 主要使用这个
     *              xml方式 => <aop:aspectj-autoproxy/>
     * 3、编写切面、切入点、通知
     *      我们这里以注解为例
     *      声明当前类为切面的注解:@Aspect
     *      定义切入点的注解: @Pointcut
     *      定义通知的注解:
     *          前置通知: @Before, 在业务方法执行前被调用
     *                  JoinPoint连接点,可以简单的认为是Method反射; 要放在第一个参数的位置
     *          后置通知: @AfterReturning, 在业务方法执行之后被调用
     *          环绕通知: @Around 用来代替业务方法的执行
     *          异常通知: @AfterThrowing. 业务方法抛出异常被调用
     *          最终通知: @After, 使用被调用, 相当于finally
     */

    /**
     * 列举了常见的@PointCut的使用方法
     */
    @Test
    public void test1OfPointCut() {
        String path = "day3/test1OfPointCut.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        IShop shop = classPathXmlApplicationContext.getBean("shop2", IShop.class);
        String order = shop.order("123");
        System.out.println(order);
    }

    /**
     * 为五种通知给出了具体demo例子, 和易错点
     */
    @Test
    public void test1OfAdvice() {
        String path = "day3/test1OfAdvice.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        IShop shop = classPathXmlApplicationContext.getBean("shop1", IShop.class);
        String order = shop.order("1234");
        System.out.println(order);
    }

    /**
     * 多个切面是如何执行的
     *
     */
    @Test
    public void test2() {
        String path = "day3/test2.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        IShop shop = classPathXmlApplicationContext.getBean("shop1", IShop.class);
        String order = shop.order("12345");
        System.out.println(order);
    }

    @Test
    public void aspectjTest() {
        Shop2 target = new Shop2();
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
        proxyFactory.setTarget(target);
        // 添加切面
        proxyFactory.addAspect(LogAspectOfAdvice.class);
        Shop2 proxy = proxyFactory.getProxy();
        // 方法调用
        proxy.order("xx");
        System.out.println("\n执行异常的结果：");
        //proxy.throwException();
    }
}
