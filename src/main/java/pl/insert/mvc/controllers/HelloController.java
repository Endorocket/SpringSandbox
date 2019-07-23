package pl.insert.mvc.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/home")
public class HelloController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", "Hello Spring MVC 5!");
        model.addAttribute("standardDate", new Date());

        return "index";
    }

    @GetMapping(value = "/username")
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getPrincipal().toString();
    }
}
