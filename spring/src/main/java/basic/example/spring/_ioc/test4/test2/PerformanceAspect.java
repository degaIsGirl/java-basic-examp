package basic.example.spring._ioc.test4.test2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = 1)
public class PerformanceAspect {

    @Pointcut("execution(public * basic.example.spring._ioc.test4..*.*(..))")
    public void log() {

    }

    @Before(value = "log()")
    void deBefore(JoinPoint joinPoint) {
        System.out.println("Performance => @Before");
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            System.out.println("Performance => @Around1");
            Object result= proceedingJoinPoint.proceed();
            System.out.println("Performance => @Around2");
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @AfterReturning(value = "log()", returning = "res")
    public void deAfterReturn(JoinPoint joinPoint, Boolean res) {
        System.out.println("Performance => @AfterReturning, result is: " + res);
    }

    @After(value = "log()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("Performance => @After");
    }

    @AfterThrowing(value = "log()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("Performance => @AfterThrowing");
    }
}