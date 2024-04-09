package in.krishna.expensetrackerapi.controller;

import in.krishna.expensetrackerapi.entity.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StaticController {
    @GetMapping("/contact")
    public String showForm(Model model) {
        Contact user = new Contact();
        model.addAttribute("user", user);

        return "register_form";
    }

    @PostMapping("/contact")
    public String submitForm(@ModelAttribute("user") Contact user) {
        System.out.println(user);
        return "register_success";
    }

}
