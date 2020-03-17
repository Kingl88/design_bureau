package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.Department;
import by.it.design_bureau.entities.Product;
import by.it.design_bureau.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping
    public String productList(Model model) {
        List<Product> allProduct = productService.getAllProduct();
        model.addAttribute("products", allProduct);
        return "product/products";
    }
    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "product/addProduct";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addProductSubmit(@Validated @ModelAttribute("product") Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/addProduct";
        }
        productService.createProduct(product);
        return "redirect:/products";
    }
}
