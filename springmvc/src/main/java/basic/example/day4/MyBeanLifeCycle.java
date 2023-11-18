package basic.example.day4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component
public class MyBeanLifeCycle implements ApplicationContextAware, InitializingBean, DisposableBean {
    ApplicationContext applicationContext;

    @Resource
    OtherBean otherBean;

    public String debug() {
        return "fuck";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyBeanLifeCycle => afterPropertiesSet");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("MyBeanLifeCycle => postConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("MyBeanLifeCycle => preDestroy");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("MyBeanLifeCycle => setApplicationContext");
        this.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("MyBeanLifeCycle => destroy");
    }
}
