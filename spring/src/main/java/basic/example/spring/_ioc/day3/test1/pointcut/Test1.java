package basic.example.spring._ioc.day3.test1.pointcut;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Test1 {
}
