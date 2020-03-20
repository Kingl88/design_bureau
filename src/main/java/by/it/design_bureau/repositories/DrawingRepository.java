package by.it.design_bureau.repositories;

import by.it.design_bureau.entities.Drawing;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DrawingRepository extends CrudRepository<Drawing, Long> {
    List<Drawing> findAll();
}
