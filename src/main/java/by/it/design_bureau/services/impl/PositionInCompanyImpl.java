package by.it.design_bureau.services.impl;

import by.it.design_bureau.entities.Department;
import by.it.design_bureau.entities.PositionInCompany;
import by.it.design_bureau.repositories.PositionInCompanyRepository;
import by.it.design_bureau.services.PositionInCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PositionInCompanyImpl implements PositionInCompanyService {
    @Autowired
    PositionInCompanyRepository positionInCompanyRepository;

    @Override
    public PositionInCompany createOrUpdatePosition(PositionInCompany entity) {
        if (entity.getId() != null && positionInCompanyRepository.findById(entity.getId()).isPresent()) {
            PositionInCompany newPosition = positionInCompanyRepository.findById(entity.getId()).get();
            newPosition.setPositionName(entity.getPositionName());
            return positionInCompanyRepository.save(newPosition);
        } else {
            return positionInCompanyRepository.save(entity);
        }
    }

    @Override
    public List<PositionInCompany> getAllPosition() {
        return positionInCompanyRepository.findAll();
    }

    @Override
    public void deletePosition(Long id) {
        positionInCompanyRepository.deleteById(id);
    }

    @Override
    public Optional<PositionInCompany> findPosition(Long id) {
        return positionInCompanyRepository.findById(id);
    }

    @Override
    public List<PositionInCompany> findPositionsInDepartment(Department department) {
        return positionInCompanyRepository.findAllByDepartments(department);
    }
}
