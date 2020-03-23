package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.*;
import by.it.design_bureau.services.DepartmentService;
import by.it.design_bureau.services.EmployeeService;
import by.it.design_bureau.services.PositionInCompanyService;
import by.it.design_bureau.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionInCompanyService position;

    @GetMapping
    public String employeeList(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        return "employee/employees";
    }

    @GetMapping(value = "/addEmployee")
    public String addEmployeePage(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("positions", position.getAllPosition());
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "employee/addEmployee";
    }

    @PostMapping(value = "/add")
    public String addEmployeeSubmit(@Valid Employee employee,
                                    @Valid User user,
                                    @ModelAttribute("role") Role role,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "employee/addEmployee";
        }
        userService.createUser(user, role);
        employee.setUser(user);
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping(value = "/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("employee", employee);
        model.addAttribute("user", employee.getUser());
        model.addAttribute("roles", Role.values());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("positions", position.getAllPosition());
        return "employee/updateEmployee";
    }

    @PostMapping(value = "/update/{id}")
    public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee, @Valid User user,
                                 @ModelAttribute("role") Role role,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "employee/updateEmployee";
        }
        userService.deleteUser(id);
        userService.createUser(user, role);
        employee.setUser(user);
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }
}