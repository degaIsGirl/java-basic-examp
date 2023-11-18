package basic.example.day1.httrequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import javax.annotation.Resource;
import java.util.Collections;

@Configuration
public class MyConfiguration {
    @Resource
    MyHttpHandler handler;

    @Bean
    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        simpleUrlHandlerMapping.setUrlMap(Collections.singletonMap(
                "/first",
                handler
        ));
        simpleUrlHandlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return simpleUrlHandlerMapping;
    }
}
