package by.it.design_bureau.repositories;

import by.it.design_bureau.entities.Department;
import by.it.design_bureau.entities.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findAll();
}
