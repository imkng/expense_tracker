package in.krishna.expensetrackerapi.controller;

import in.krishna.expensetrackerapi.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StaticController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/contact")
    public String showForm(Model model) {
        Contact user = new Contact();
        model.addAttribute("user", user);

        return "register_form";
    }

    @PostMapping("/contact")
    public String submitForm(@ModelAttribute("user") Contact user) {
        System.out.println(user);
        System.out.println(user.getEmail());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("nandkrishna1999@gmail.com");
        message.setFrom(user.getEmail());
        String subject = user.getName() + " has sent message";
        String mailContent = "Sender Name: " + user.getName() + "\n";
        mailContent += "Sender E-mail: " + user.getEmail() +"\n";
        mailContent += "Subject: " + user.getNote() +"\n";
        mailContent += "Sender E-mail" + user.getEmail() +"\n";

        message.setSubject(subject);
        message.setText(mailContent);

        mailSender.send(message);
        return "register_success";
    }

}
