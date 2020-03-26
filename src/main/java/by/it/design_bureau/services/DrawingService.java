package by.it.design_bureau.services;

import by.it.design_bureau.entities.Drawing;
import by.it.design_bureau.entities.Employee;
import by.it.design_bureau.entities.Product;

import java.util.List;
import java.util.Optional;

public interface DrawingService {
    List<Drawing> gelAllDrawing();
    Drawing createDrawing(Drawing drawing);
    void deleteDrawing(Long id);
    Optional<Drawing> getDrawingById(Long id);
    Drawing updateDrawing(Drawing drawing);
    List<Drawing> findAllByProduct(Product product);
    void deleteDrawingByProduct(Product product);
}
