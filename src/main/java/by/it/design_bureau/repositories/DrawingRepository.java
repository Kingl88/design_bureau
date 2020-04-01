package by.it.design_bureau.repositories;

import by.it.design_bureau.entities.Drawing;
import by.it.design_bureau.entities.Employee;
import by.it.design_bureau.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DrawingRepository extends CrudRepository<Drawing, Long> {
    List<Drawing> findAll();
    List<Drawing> findAllByProduct(Product product);
    List<Drawing> findAllByApprovedOrDevelopedOrChecked(Employee employee, Employee employee2, Employee employee3);
    void deleteAllByProduct(Product product);
}
