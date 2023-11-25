package basic.example.spring._env;

import basic.example.spring._env.day1.Demo;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Environment 包含两个重要的部分:profiles and properties,
 * profiles A profile is a named, logical group of bean definitions to be registered with the container only
 * if the given profile is active. Beans may be assigned to a profile whether defined in XML or via annotation.
 * @Profile annotation
 *
 * properties: Properties play an important role in almost all applications, and may originate from a variety of sources:
 * properties files, JVM system properties, system environment variables, JNDI, servlet context parameters,
 * ad-hoc Properties objects, Maps, and so on
 */
public class Day1 {

    /**
     * StandardEnvironment
     */
    @Test
    public void test1OfDemo() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //设置激活的环境
        context.getEnvironment().setActiveProfiles("gray");
        context.register(Demo.class);
        context.refresh();
        ConfigurableEnvironment env = context.getEnvironment();

        // 获取系统属性
        String property = env.getProperty("java.version");
        System.out.println(property);

        // 获取自定义属性
        String city = env.getProperty("city");
        System.out.println(city);
    }

    /**
     * 添加自定义属性方式2
     */
    @Test
    public void testEnv() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext();
        ConfigurableEnvironment env = classPathXmlApplicationContext.getEnvironment();

        // 如何添加自定义的属性
        MutablePropertySources propertySources = env.getPropertySources();
        String propPath = "classpath:/_env/demo.properties";
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(propPath);
        if (resource.exists() && resource.isFile()) {
            try {
                Properties properties = new Properties();
                properties.load(resource.getInputStream());
                Map<String, Object> dataSource = new HashMap<>();
                properties.forEach((key, value) -> {
                    dataSource.put(key.toString(), value);
                });
                propertySources.addLast(new MapPropertySource("self", dataSource));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //读取自定义变量
        String property = env.getProperty("city", "beijing");

        // 解析数据
        String result = "${name} ${work}";
        String s = env.resolvePlaceholders(result);
        System.out.println(s);
    }
}
