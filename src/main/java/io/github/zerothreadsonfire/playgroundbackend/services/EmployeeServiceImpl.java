package io.github.zerothreadsonfire.playgroundbackend.services;

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
        if(employee.getEmployeeID() == null || employee.getEmailId().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }

        employees.add(employee);
        return employee;
    }
}
