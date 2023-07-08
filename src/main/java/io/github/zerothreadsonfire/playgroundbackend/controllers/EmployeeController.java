package io.github.zerothreadsonfire.playgroundbackend.controllers;

import io.github.zerothreadsonfire.playgroundbackend.models.Employee;
import io.github.zerothreadsonfire.playgroundbackend.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @RequestBody maps the HttpRequest body to a domain object, enabling automatic deserialization. This is assuming the
 * appropriate JSON is specified.
 * @AutoWired enables automatic dependency injection, i.e. by declaring all bean dependencies in a spring config file,
 * spring container can autowire relationships between collaborating beans.
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id);
    }
}
