package basic.example.spring._ioc.day2;

import basic.example.spring._ioc.day1.School;
import com.xmw.component.common.util.JsonUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Resource 和 @Autowired 之间的区别是什么?
 * 1、@Autowired 默认byType 注入: 先按照类型寻找, 如果有一个直接注入即可以了, 如果有多个, 在按照属性的名字寻找对应的beanName
 *    @Resource 默认byName 注入: 先找名字寻找, 如果找到尝试注入, 如果找不到再按照类型寻找, 进行注入;
 * 2、
 */
@Component
@PropertySource("classpath:application.properties")
@Getter
@Setter
public class Student {
    /**
     * 简单值的注入, 通过@Value注解
     */
    @Value("${name}")
    private String name;

    @Value("${age}")
    private String age;

    /**
     * 引用类型的注入通过@Autowired 或者 @Resource
     */
    @Autowired
    private Universe universe;

    @Resource
    private PrimarySchool primarySchool;

    @Override
    public String toString() {
        return JsonUtil.formatToStr(this);
    }
}
