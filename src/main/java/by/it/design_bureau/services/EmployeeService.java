package by.it.design_bureau.services;

import by.it.design_bureau.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeeById(Long id);

    void deleteEmployee(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);
}
