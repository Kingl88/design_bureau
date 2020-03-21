package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.Drawing;
import by.it.design_bureau.services.DrawingService;
import by.it.design_bureau.services.EmployeeService;
import by.it.design_bureau.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/drawings")
public class DrawingController {
    @Autowired
    DrawingService drawingService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProductService productService;
    @GetMapping
    public String drawingList(Model model) {
        List<Drawing> allDrawing = drawingService.gelAllDrawing();
        model.addAttribute("drawings", allDrawing);
        return "drawing/drawings";
    }
    @RequestMapping(value = "/addDrawing", method = RequestMethod.GET)
    public String addDrawingPage(Model model) {
        model.addAttribute("drawing", new Drawing());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("products", productService.getAllProduct());
        return "drawing/addDrawing";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addDrawingSubmit(@Validated @ModelAttribute("drawing") Drawing drawing, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "drawing/addDrawing";
        }
        drawingService.createDrawing(drawing);
        return "redirect:/drawings";
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    public String deleteDrawing(@PathVariable Long id) {
        drawingService.deleteDrawing(id);
        return "redirect:/drawings";
    }
}
