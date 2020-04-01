package by.it.design_bureau.services;

import by.it.design_bureau.entities.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    Optional<Department> find(Long id);
    List<Department> getAllDepartments();
    Department createOrUpdateDepartment(Department department);
    void deleteDepartment(Long id);
}
