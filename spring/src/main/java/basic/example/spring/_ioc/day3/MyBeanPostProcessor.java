package basic.example.spring._ioc.day3;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("myBeanLifeCycle".equals(beanName)) {
            System.out.println("MyBeanLifeCycle => MyBeanPostProcessor postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("myBeanLifeCycle".equals(beanName)) {
            System.out.println("MyBeanLifeCycle => MyBeanPostProcessor postProcessAfterInitialization");
        }
        return bean;
    }
}
