package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.Product;
import by.it.design_bureau.services.DrawingService;
import by.it.design_bureau.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    DrawingService drawingService;
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

    @GetMapping(value="/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        drawingService.deleteDrawingByProduct(productService.getProductById(id).get());
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping(value="/{id}")
    public String getAllDrawing(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("drawings", drawingService.findAllByProduct(product));
        return "drawing/drawings";
    }
}
