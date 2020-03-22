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

    @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
    public String addEmployeePage(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("positions", position.getAllPosition());
        model.addAttribute("employee", new Employee());
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "employee/addEmployee";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addEmployeeSubmit(@Validated @ModelAttribute("employee") Employee employee,
                                    @ModelAttribute("user") User user,
                                    @ModelAttribute("department") Department department,
                                    @ModelAttribute("position") PositionInCompany position,
                                    @ModelAttribute("role") Role role,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/addEmployee";
        }
        if (userService.createUser(user, role)) {
            employee.setPosition(position);
            department.getEmployees().add(employee);
            employee.setDepartment(department);
            user.setEmployee(employee);
            employeeService.createEmployee(employee);
            employee.setUser(user);
            return "redirect:/employees";
        } else {
            return "redirect:/employees/addEmployee";
        }
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "employee/employees";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String updateEmployee(@PathVariable("id") long id, @Valid Employee employee,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.setId(id);
            return "updateEmployee";
        }
        model.addAttribute("employee", employee);
        model.addAttribute("roles", Role.values());
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("positions", position.getAllPosition());
        employeeService.createEmployee(employee);
        return "employee/employees";
    }
}
