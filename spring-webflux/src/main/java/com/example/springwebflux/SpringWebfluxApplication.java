package com.example.springwebflux;

import com.example.springwebflux.client.EmployeeWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class SpringWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxApplication.class, args);
        employeeWebClient().consume();
    }

    @Bean
    public static EmployeeWebClient employeeWebClient() {
        return new EmployeeWebClient();
    }
}
