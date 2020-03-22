package by.it.design_bureau.services;

import by.it.design_bureau.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product createProduct (Product product);
    void deleteProduct(Long id);
}
