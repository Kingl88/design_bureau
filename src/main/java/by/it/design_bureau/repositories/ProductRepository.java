package by.it.design_bureau.repositories;

import by.it.design_bureau.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    Product findByProductName(String productName);
}
