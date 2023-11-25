package basic.example.spring._env.day1;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/_env/demo.properties")
@Profile(value = {"gray", "prod"})
public class Demo {
}
