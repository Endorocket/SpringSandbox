package pl.insert.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.insert.data.User;
import pl.insert.dto.UserDto;
import pl.insert.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/register-old")
public class RegistrationController {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/showRegistrationForm")
    public String showRegistrationForm(Model theModel) {

        theModel.addAttribute("userDto", new UserDto());

        return "registration/register-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Validated({UserDto.Group1.class, UserDto.Group2.class}) @ModelAttribute("userDto") UserDto userDto,
            BindingResult theBindingResult, Model model) {

        String userName = userDto.getUsername();
        logger.info("Processing registration form for: " + userName);

        // form validation
        if (theBindingResult.hasErrors()) {
            return "registration/register-form";
        }

        // check the database if user already exists
        User existing = userService.findByUsername(userName);
        if (existing != null) {
            model.addAttribute("userDto", new UserDto());
            model.addAttribute("registrationError", "User name already exists.");

            logger.warn("User name already exists.");
            return "registration/register-form";
        }
        // create user account
        userService.save(userDto);

        logger.info("Successfully created user: " + userName);

        return "registration/confirmation";
    }
}
