package by.it.design_bureau.services.impl;

import by.it.design_bureau.entities.Drawing;
import by.it.design_bureau.entities.Employee;
import by.it.design_bureau.entities.Product;
import by.it.design_bureau.repositories.DrawingRepository;
import by.it.design_bureau.services.DrawingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DrawingServiceImpl implements DrawingService {
    @Autowired
    DrawingRepository drawingRepository;

    @Override
    public List<Drawing> gelAllDrawing() {
        return drawingRepository.findAll();
    }

    @Override
    public Drawing createDrawing(Drawing drawing) {
        return drawingRepository.save(drawing);
    }

    @Override
    public void deleteDrawing(Long id) {
        drawingRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Drawing> getDrawingById(Long id) {
        return drawingRepository.findById(id);
    }

    @Override
    public Drawing updateDrawing(Drawing drawing) {
        return drawingRepository.save(drawing);
    }

    @Override
    public List<Drawing> findAllByProduct(Product product) {
        return drawingRepository.findAllByProduct(product);
    }

    @Override
    public void deleteDrawingByProduct(Product product) {
        drawingRepository.deleteAllByProduct(product);
    }

    @Override
    public List<Drawing> findAllByEmployee(Employee employee) {
        return drawingRepository.findAllByApprovedOrDevelopedOrChecked(employee, employee, employee);
    }
}
