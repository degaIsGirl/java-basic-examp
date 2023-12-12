package basic.example.spring._aspect.day1.entry;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class EntranceAspect {
    @Pointcut("execution(public * basic.example.spring._aspect.day1.entry.DemoShop.*(..))")
    public void cal() {

    }

    @Around("cal()")
    public Object doAround(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        try {
            Object result = pjp.proceed();
            long end = System.currentTimeMillis();
            long useTime = end - start;
            System.out.println("exec "+ pjp.getSignature().getName() +" method escape " + useTime + " mill");
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
