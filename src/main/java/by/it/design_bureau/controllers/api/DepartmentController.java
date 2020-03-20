package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.Department;
import by.it.design_bureau.services.DepartmentService;
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
@RequestMapping(value = "/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public String departmentList(Model model) {
        List<Department> allDepartment = departmentService.getAllDepartments();
        model.addAttribute("departments", allDepartment);
        return "department/departments";
    }
    @RequestMapping(value = "/addDepartment", method = RequestMethod.GET)
    public String addDepartmentPage(Model model) {
        model.addAttribute("department", new Department());
        return "department/addDepartment";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addDepartmentSubmit(@Validated @ModelAttribute("department") Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "department/addDepartment";
        }
        departmentService.createDepartment(department);
        return "redirect:/departments";
    }
}
