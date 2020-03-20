package by.it.design_bureau.services;

import by.it.design_bureau.entities.Drawing;

import java.util.List;

public interface DrawingService {
    List<Drawing> gelAllDrawing();
    Drawing createDrawing(Drawing drawing);
}
