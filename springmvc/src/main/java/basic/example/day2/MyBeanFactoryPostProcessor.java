package basic.example.day2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition myBeanLifeCycle = beanFactory.getBeanDefinition("myBeanLifeCycle");
        myBeanLifeCycle.setDescription("fuck");
        System.out.println("MyBeanLifeCycle => MyBeanFactoryPostProcessor postProcessBeanFactory");
    }
}
