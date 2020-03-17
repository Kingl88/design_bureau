package by.it.design_bureau.services.impl;

import by.it.design_bureau.entities.Drawing;
import by.it.design_bureau.repositories.DrawingRepository;
import by.it.design_bureau.services.DrawingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
