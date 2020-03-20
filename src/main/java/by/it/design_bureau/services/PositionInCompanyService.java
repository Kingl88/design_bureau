package by.it.design_bureau.services;

import by.it.design_bureau.entities.PositionInCompany;

import java.util.List;

public interface PositionInCompanyService {
    PositionInCompany createPosition(PositionInCompany position);
    List<PositionInCompany> getAllPosition();
}
