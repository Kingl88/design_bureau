package by.it.design_bureau.services;

import by.it.design_bureau.entities.Drawing;
import by.it.design_bureau.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();
    Product createProduct (Product product);
    void deleteProduct(Long id);
    Optional<Product> getProductById(Long id);
}
