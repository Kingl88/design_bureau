package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.*;
import by.it.design_bureau.services.DepartmentService;
import by.it.design_bureau.services.PositionInCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/departments")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionInCompanyService position;

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
    public String addDepartmentSubmit(@Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "department/addDepartment";
        }
        departmentService.createOrUpdateDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping(value="/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
    @GetMapping(value = "/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Department department;
        try {
            department = departmentService.find(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
        } catch (IllegalArgumentException e) {
            return "redirect:/departments";
        }
        model.addAttribute("department", department);
        return "department/updateDepartment";
    }

    @PostMapping(value = "/update/{id}")
    public String updateDepartment(@PathVariable("id") long id, @Valid Department department, BindingResult result) {
        if (result.hasErrors()) {
            department.setId(id);
            return "department/updateDepartment";
        }
        departmentService.createOrUpdateDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping(value="/{id}")
    public String getAllPositions(@PathVariable Long id, Model model) {
        Department department = departmentService.find(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
        model.addAttribute("positions", position.findPositionsInDepartment(department));
        model.addAttribute("department", department);
        return "positionInCompany/positions";
    }
}
