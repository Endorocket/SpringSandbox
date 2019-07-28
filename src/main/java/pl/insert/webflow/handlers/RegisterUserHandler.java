package pl.insert.webflow.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import pl.insert.data.User;
import pl.insert.dto.UserDto;
import pl.insert.services.UserService;

@Component
public class RegisterUserHandler {

    private final UserService userService;

    @Autowired
    public RegisterUserHandler(UserService userService) {
        this.userService = userService;
    }

    public UserDto init() {
        return new UserDto();
    }

    public String validateUsername(String username, MessageContext error) {
        String transitionValue = "confirm";

        User existing = userService.findByUsername(username);
        if (existing != null) {
            error.addMessage(new MessageBuilder().error()
                    .source("username")
                    .defaultText("That username already exists!")
                    .build());
            return "failure";
        }

        return transitionValue;
    }

    public String save(UserDto model) {
        String transitionValue = "success";

        userService.save(model);

        return transitionValue;
    }
}
