package by.it.design_bureau.services;

import by.it.design_bureau.entities.Department;
import by.it.design_bureau.entities.PositionInCompany;

import java.util.List;
import java.util.Optional;

public interface PositionInCompanyService {
    PositionInCompany createOrUpdatePosition(PositionInCompany position);
    List<PositionInCompany> getAllPosition();
    void deletePosition(Long id);
    Optional<PositionInCompany> findPosition(Long id);
    List<PositionInCompany> findPositionsInDepartment(Department department);

}
