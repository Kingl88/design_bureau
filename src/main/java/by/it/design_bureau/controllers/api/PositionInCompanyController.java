package by.it.design_bureau.controllers.api;

import by.it.design_bureau.entities.PositionInCompany;
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
        this.position.createOrUpdatePosition(position);
        return "redirect:/positions";
    }

    @GetMapping(value="/delete/{id}")
    public String deletePosition(@PathVariable Long id) {
        position.deletePosition(id);
        return "redirect:/positions";
    }
    @GetMapping(value = "/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        PositionInCompany position;
        try {
            position = this.position.findPosition(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid position Id:" + id));
        } catch (IllegalArgumentException e) {
            return "redirect:/positions";
        }
        model.addAttribute("position", position);
        return "positionInCompany/updatePosition";
    }

    @PostMapping(value = "/update/{id}")
    public String updatePosition(@PathVariable("id") long id, @Valid PositionInCompany position, BindingResult result) {
        if (result.hasErrors()) {
            position.setId(id);
            return "positionInCompany/updatePosition";
        }
        this.position.createOrUpdatePosition(position);
        return "redirect:/positions";
    }
}
