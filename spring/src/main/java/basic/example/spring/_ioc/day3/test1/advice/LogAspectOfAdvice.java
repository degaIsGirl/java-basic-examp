package basic.example.spring._ioc.day3.test1.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy()
@Order(value = 2)
public class LogAspectOfAdvice {

    /**
     * 创建切入点: 我们可以认为切入点是对特定位置的描述, 解决的是where的问题, 在spring中基本上是用来描述某些特定的方法
     *
     * 无返回值
     * 无参数
     * 重点是看方法上的@Pointcut注解
     *
     * 下面的例子中用来匹配 public 任意返回值 basic.example.spring._ioc.day3.test1包下 任意类 任意方法 任意参数
     * 由于切面表达式规则较多, 我们只讲一些较为常用的:
     *      1、最常用的就是execution表达式了
     *      execution是对符合特定规则方法的描述, execution( <访问修饰符> 返回值 包名.类名.方法名(参数类型) )
     *
     *      2、@target 被代理的目标对象上包含特定的注解
     *
     *      3、@annotation 被拦截的方法上拥有特定的注解
     */
    @Pointcut("execution(public * basic.example.spring._ioc.day3.test1.advice.*.*(..))")
    public void log() {

    }

    /**
     * 前置通知
     *
     * 参数: 参数JoinPoint代表连接点, 必须放在参数的一个位置
     *
     * 方法的返回值是void, 即使有返回值也不会被使用, 所以就直接使用void
     *
     * @param joinPoint
     */
    @Before(value = "log() && args(orderNo)")
    void deBefore(JoinPoint joinPoint, String orderNo) {
        /**
         * 获取被代理的目标对象
         */
        Object target = joinPoint.getTarget();

        /**
         * 获取代理对象
         */
        Object proxyObj = joinPoint.getThis();

        /**
         * 获取方法签名
         */
        Signature signature = joinPoint.getSignature();

        // 参数
        Object[] args = joinPoint.getArgs();

        System.out.println("Log => @Before");
    }

    /**
     * 环绕通知
     *
     * 用来代替目标方法的执行
     *
     * 参数: ProceedingJoinPoint 是JoinPoint的子类, 说明也是一个连接点, 在spring中其实就是具体方法的反射对象,
     * 我们同样可以简单的认为是Method, 只不过它提供了 proceed() 的快捷方法
     *
     * 返回值: 必须有返回值, 一般是被代理方法的返回结果. 不过环绕通知也可以改变改结果并返回
     *
     * @param proceedingJoinPoint
     * @return
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("Log => @Around1");
            Object result= proceedingJoinPoint.proceed();
            result = "change Result";
            System.out.println("Log => @Around2");
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 后置通知 和 异常通知 二选一, 如果发生异常本通知就不会执行
     *
     * 参数: 第一个参数同@Before一致
     *      Object res, 代表业务方法的返回值, 类型需要同业务方法的返回值类型兼容(一般用Object)、@AfterReturning注解中的returning res 需要同形参ret保持一致
     *
     * 注意: 返回结果的类型可以是目标方法的返回类型 或者 更加的宽泛, 一般我们选择Object 这样所有的类型都可以接受.
     *      如果返回结果不满足上述条件, 后置通知同样也不会执行
     *
     * 方法的返回值是void, 即使有返回值也不会被使用, 所以就直接使用void
     */
    @AfterReturning(value = "log()", returning = "res")
    public void deAfterReturn(JoinPoint joinPoint, Boolean res) {
        System.out.println("Log => @AfterReturning, result is: " + res);
    }

    /**
     * 最终通知
     *
     * 这个通知始终会被执行
     *
     * @param joinPoint
     */
    @After(value = "log()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("Log => @After");
    }

    /**
     * 异常通知
     *
     * 参数: 第一个参数同@Before一致
     *      当有异常时才会触发的通知, 异常的形参名称 要和 @AfterThrowing 中的throwing 保持一致.
     *
     * 注意: 异常的类型可以是目标方法抛出的异常类型 或者 更加的宽泛, 我们一般用Exception 这样所有的异常都可以被捕获;
     *      否则改方法同样不会得到执行
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "log()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("Log => @AfterThrowing");
    }

    /**
     * 多个通知并存的情况下的执行顺序:
     *
     * 正常业务流程执行顺序, 无异常抛出 =>
     *      @Around1
     *      @Before
     *      shop class 执行 order 方法
     *      @Around2
     *      @After
     *      @AfterReturning, result is: change Result
     *
     * 业务方法抛出异常的执行顺序 =>
     *      @Around1
     *      @Before
     *      shop class 执行 order 方法
     *      @After
     *      @AfterThrowing
     */
}