package by.it.design_bureau.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(value = "/")
    public String homePage(Model model) {
        return "home";
    }
    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        return "/login";
    }

}
