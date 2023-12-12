package com.example.springwebflux.controller;

import com.example.springwebflux.entity.Employee;
import com.example.springwebflux.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Resource
    EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public Mono<Employee> getEmployeeById(@PathVariable String id) {

        return employeeRepository.findEmployeeById(id);
    }

    @GetMapping
    public Flux<Employee> getAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

}
