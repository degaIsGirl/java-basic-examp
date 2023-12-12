package basic.example.day2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
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
