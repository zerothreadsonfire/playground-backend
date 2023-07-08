package io.github.zerothreadsonfire.playgroundbackend.services;

import io.github.zerothreadsonfire.playgroundbackend.exceptions.EmployeeNotFoundException;
import io.github.zerothreadsonfire.playgroundbackend.models.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Service is a special type of @Component, which allows us to auto-detect implementation classes through
 * classpath scanning.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees = new ArrayList<>();
    public Employee saveEmployee(Employee employee) {
        if(employee.getEmployeeId() == null || employee.getEmailId().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }

        employees.add(employee);
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee getEmployeeById(String id) {
        return employees.stream()
                .filter(employee -> employee.getEmployeeId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));
    }
}
