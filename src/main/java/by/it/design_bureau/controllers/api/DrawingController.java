package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.Drawing;
import by.it.design_bureau.entities.Employee;
import by.it.design_bureau.entities.Role;
import by.it.design_bureau.entities.User;
import by.it.design_bureau.services.DrawingService;
import by.it.design_bureau.services.EmployeeService;
import by.it.design_bureau.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @RequestMapping(value = "/addDrawing/{id}", method = RequestMethod.GET)
    public String addDrawingPage(Model model, @PathVariable Long id) {
        model.addAttribute("drawing", new Drawing());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("product", productService.getProductById(id).get());
        return "drawing/addDrawing";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addDrawingSubmit(@Valid Drawing drawing, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "drawing/addDrawing";
        }
        drawingService.createDrawing(drawing);
        Long id = drawing.getProduct().getId();
        return "redirect:/products/" + id;
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteDrawing(@PathVariable Long id) {
        drawingService.deleteDrawing(id);
        return "redirect:/drawings";
    }

    @GetMapping(value = "/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Drawing drawing = drawingService.getDrawingById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("drawing", drawing);
        return "drawing/updateDrawing";
    }

    @PostMapping(value = "/update/{id}")
    public String updateDrawing(@PathVariable("id") long id, @Valid Drawing drawing,
                                 BindingResult result) {
        if (result.hasErrors()) {
            drawing.setId(id);
            return "drawing/updateDrawing";
        }
        drawingService.updateDrawing(drawing);
        Long id1 = drawing.getProduct().getId();
        return "redirect:/products/" + id1;
    }
}
