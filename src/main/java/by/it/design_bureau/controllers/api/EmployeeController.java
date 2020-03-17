package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.Department;
import by.it.design_bureau.entities.Employee;
import by.it.design_bureau.entities.PositionInCompany;
import by.it.design_bureau.entities.User;
import by.it.design_bureau.services.DepartmentService;
import by.it.design_bureau.services.EmployeeService;
import by.it.design_bureau.services.PositionInCompanyService;
import by.it.design_bureau.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return "employee/addEmployee";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addEmployeeSubmit(@Validated @ModelAttribute("employee") Employee employee,
                                    @ModelAttribute("user") User user,
                                    @ModelAttribute("department") Department department,
                                    @ModelAttribute("position") PositionInCompany position,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee/addEmployee";
        }
        employee.setPosition(position);
        if (!department.getPositions().contains(position) && !position.getDepartments().contains(department)) {
            department.getPositions().add(position);
            position.getDepartments().add(department);
        }
        department.getEmployees().add(employee);
        employee.setDepartment(department);
        user.setEmployee(employee);
        userService.createUser(user);
        employeeService.createEmployee(employee);
        employee.setUser(user);

        return "redirect:/employees";
    }
}
