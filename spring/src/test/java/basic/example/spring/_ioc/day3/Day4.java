package basic.example.spring._ioc.day3;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * bean 的声明周期
 * => 1、beandefenition定义 (可以通过xml、注解方式)
 *
 * => 2、将BeanDefinition 读取&加载 到ioc容器中
 *
 * => 3、BeanFactoryPostProcessor:void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) 完成对BeaDefination的自定义处理
 *
 * => 4、构造函数实例化Bean对象(构造函数依赖注入对象), 一般是通过无参构造完成实例化, 当然也可以指定@Autowire注解指定有参构造方法, 此时其实也完成了依赖注入中的构造注入
 *
 * => 5、完成以来注入:
 * 	属性注入的优先级:
 * 		a、set完成属性依赖注入
 * 		b、完成Aware相关接口的注入
 *
 * => 6、BeanPostProcessor: 在bean初始化之前调用
 * 			default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
 * 				return bean;
 *          }
 * => 7、执行初始化操作:
 * 	初始化的优先级:
 * 		1、@PostConstruct 注解修饰的方法
 * 		2、实现了InitializingBean接口:void afterPropertiesSet() throws Exception
 * 		3、@bean指定的initMethod方法
 *
 * => 8、BeanPostProcessor: 在bean初始化后调用
 * 	        default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
 * 		        return bean;
 * 	        }
 *
 * => 9、使用bean对象进行业务操作
 *
 * => 10、容器关闭执行销毁bean的操作
 * 	销毁bean的优先级:
 * 		1、首先执行@PreDestory
 * 		2、是否实现了DisposableBean接口:void destroy() throws Exception;
 * 		3、@bean指定的destroyMethod方法
 */
public class Day4 {
    @Test
    public void test1() {
        String path = "day4/entry.xml";
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(path);
        MyBeanLifeCycle myBeanLifeCycle = classPathXmlApplicationContext.getBean("myBeanLifeCycle", MyBeanLifeCycle.class);
        classPathXmlApplicationContext.close();
    }
}
