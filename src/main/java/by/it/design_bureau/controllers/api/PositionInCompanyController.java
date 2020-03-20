package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.PositionInCompany;
import by.it.design_bureau.services.PositionInCompanyService;
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
@RequestMapping(value = "/positions")
public class PositionInCompanyController {
    @Autowired
    PositionInCompanyService position;

    @GetMapping
    public String positionList(Model model) {
        List<PositionInCompany> allPosition = position.getAllPosition();
        model.addAttribute("positions", allPosition);
        return "positionInCompany/positions";
    }
    @RequestMapping(value = "/addPosition", method = RequestMethod.GET)
    public String addPositionPage(Model model) {
        model.addAttribute("position", new PositionInCompany());
        return "positionInCompany/addPosition";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addPositionSubmit(@Validated @ModelAttribute("position") PositionInCompany position, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "positionInCompany/addPosition";
        }
        this.position.createPosition(position);
        return "redirect:/positions";
    }
}
