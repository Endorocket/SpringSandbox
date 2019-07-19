package pl.insert.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping
public class HelloController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", "Hello Spring MVC 5!");
        model.addAttribute("standardDate", new Date());

        return "index";
    }
}
