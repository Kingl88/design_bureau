package by.it.design_bureau.repositories;

import by.it.design_bureau.entities.Department;
import by.it.design_bureau.entities.PositionInCompany;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionInCompanyRepository extends CrudRepository<PositionInCompany, Long> {
    List<PositionInCompany> findAll();
    List<PositionInCompany> findAllByDepartments(Department department);
}
