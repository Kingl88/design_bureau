package by.it.design_bureau.services.impl;

import by.it.design_bureau.entities.Employee;
import by.it.design_bureau.repositories.EmployeeRepository;
import by.it.design_bureau.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee createEmployee(Employee product) {
        return employeeRepository.save(product);
    }

    @Override
    public Employee updateEmployee(Employee product) {
        return employeeRepository.save(product);
    }
}
