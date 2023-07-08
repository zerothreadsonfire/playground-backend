package io.github.zerothreadsonfire.playgroundbackend.services;

import io.github.zerothreadsonfire.playgroundbackend.models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);
}
