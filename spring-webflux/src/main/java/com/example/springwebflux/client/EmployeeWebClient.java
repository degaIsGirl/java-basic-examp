package com.example.springwebflux.client;

import com.example.springwebflux.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class EmployeeWebClient {
    private WebClient client = WebClient.create("http://127.0.0.1:8080/");

    public void consume() {

        Mono<Employee> employeeMono = client.get()
                .uri("/employees/{id}", "1")
                .retrieve()
                .bodyToMono(Employee.class);

        employeeMono.subscribe(employee -> log.info("Employee-mono: {}", employee));

        Flux<Employee> employeeFlux = client.get()
                .uri("/employees")
                .retrieve()
                .bodyToFlux(Employee.class);

        employeeFlux.subscribe(employee -> log.info("Employee-flux: {}", employee));
    }
}
