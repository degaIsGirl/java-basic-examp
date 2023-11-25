package basic.example.spring.aspect.day1.test1.pointcut;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy()
public class LogAspectOfPointCut {

    /**
     * 创建切入点: 我们可以认为切入点是对特定位置的描述, 解决的是where的问题, 在spring中基本上是用来描述某些特定的方法
     *
     * 无返回值
     * 无参数
     * 重点是看方法上的@Pointcut注解
     *
     * 下面的例子中用来匹配 public 任意返回值 basic.example.spring._ioc.day1.test1包下 任意类 任意方法 任意参数
     *
     * 由于切面表达式规则较多, 我们只讲一些较为常用的:
     *      1、最常用的就是execution表达式了
     *      execution是对符合特定规则方法的描述, 具体的规则如下:
     *          execution（modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern（param-pattern） throws-pattern?）
     *          modifiers-pattern:  访问修饰符
     *                              对应下面的public
     *                              非必填, 忽略表示匹配所有的访问修饰符
     *
     *          ret-type-pattern: 返回类型模式, 用于描述方法的返回类型
     *                            对应下面的 *, 表示匹配任何返回类型
     *                            必填,不可忽略
     *
     *          declaring-type-pattern: 用于匹配方法的包、类这两个部分
     *                                  对应下面的basic.example.spring._ioc.day1.test1.*, 表示basic.example.spring._ioc.day1.test1这个包下所有的类
     *                                  也支持basic.example.spring._ioc.day1.test1..*,(-> *前面可是.. <-) 表示basic.example.spring._ioc.day1.test1这个包及其子包中所有的类
     *                                  非必填,可以忽略, 忽略情况下表示所有包下的所有类
     *
     *
     *          name-pattern:  名字模式, 用于描述方法名.
     *                         对应下面的*, 表示匹配所有的方法名称.
     *                         也支持test*, 表示所有以test开头的方法
     *                         必填
     *
     *          param-pattern: 参数模式, 用于描述方法参数.
     *                         对应下面的(..), 表示任意类型、任意个参数;
     *                         也可以是(), 表示无参数;
     *                         也可以是(String) , 表示参数为String类型
     *                         必填
     *
     *      2、target 被代理的对象符合特定的类型
     *
     *      3、@target 被代理的目标对象上包含特定的注解
     *
     *      4、@annotation 被拦截的方法上拥有特定的注解
     */
    @Pointcut("execution(public * basic.example.spring._ioc.day4.test1.*.*(..))")
    public void log() {

    }
    @Before("log()")
    public void testExecution() {
        System.out.println("Log => @Before, testExecution");
    }

    // 要求被代理的目标类上面拥有basic.example.spring._ioc.day1.test1.pointcut.Test1 这个注解
    @Pointcut("@target(basic.example.spring.aspect.day1.test1.pointcut.Test1)")
    public void targetAnnotation() {

    }
    @Before(value = "targetAnnotation()")
    void testTargetAnnotation() {
        System.out.println("Log => @Before, testTargetAnnotation");
    }

    // 要求被代理的对象是Shop类或者其子类
    // +表示含Shop 的子类
    @Pointcut("target(basic.example.spring.aspect.day1.test1.pointcut.Shop2+)")
    public void targetDemo() {

    }
    @Before(value = "targetDemo()")
    public void testTarget() {
        System.out.println("Log => @Before, testTarget");
    }

    // 要求被代理的方法上拥有basic.example.spring._ioc.day1.test1.pointcut.Test1 这个注解
    @Pointcut("@annotation(basic.example.spring.aspect.day1.test1.pointcut.Test1)")
    public void methodAnnotation() {
    }
    @Before("methodAnnotation()")
    void testMethodAnnotation() {
        System.out.println("Log => @Before, methodAnnotation");
    }

}