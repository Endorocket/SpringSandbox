package pl.insert.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login-page")
    public String showLoginPage() {

        return "security/login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "security/access-denied";
    }
}
