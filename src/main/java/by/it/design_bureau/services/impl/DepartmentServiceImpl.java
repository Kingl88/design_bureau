package by.it.design_bureau.services.impl;

import by.it.design_bureau.entities.Department;
import by.it.design_bureau.repositories.DepartmentRepository;
import by.it.design_bureau.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Optional<Department> find(Long id) {
        return departmentRepository.findById(id);
    }

    @Override @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
