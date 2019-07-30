package pl.insert.mvc.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.insert.data.User;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping
public class HelloController {

    @GetMapping
    public String redirectPage() {
        return "redirect:home";
    }

    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("message", "Hello Spring MVC 5!");
        model.addAttribute("standardDate", new Date());

        return "index";
    }

    @GetMapping(value = "/username")
    @ResponseBody
    public String currentUserName(Authentication authentication, HttpSession session) {

        User user = (User) session.getAttribute("user");

        return authentication.getPrincipal().toString();
    }
}
