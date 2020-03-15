package by.it.design_bureau.services.impl;

import by.it.design_bureau.entities.PositionInCompany;
import by.it.design_bureau.repositories.PositionInCompanyRepository;
import by.it.design_bureau.services.PositionInCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PositionInCompanyImpl implements PositionInCompanyService {
    @Autowired
    PositionInCompanyRepository positionInCompanyRepository;

    @Override
    public PositionInCompany createPosition(PositionInCompany position) {
        return positionInCompanyRepository.save(position);
    }

    @Override
    public List<PositionInCompany> getAllPosition() {
        return positionInCompanyRepository.findAll();
    }
}
