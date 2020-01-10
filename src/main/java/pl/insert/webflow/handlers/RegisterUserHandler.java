package pl.insert.webflow.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import pl.insert.data.User;
import pl.insert.dto.SurveyDto;
import pl.insert.dto.UserDto;
import pl.insert.services.UserService;

import java.util.Arrays;

@Component
public class RegisterUserHandler {

  private final UserService userService;

  @Autowired
  public RegisterUserHandler(UserService userService) {
    this.userService = userService;
  }

  public UserDto init() {
    UserDto user = new UserDto();
    user.setUsername("Mateusz");
    user.setStrings(Arrays.asList("a", "b", "c"));
    user.setSurveys(Arrays.asList(new SurveyDto(10, "Insert SA"), new SurveyDto(5, "ABC SA")));

    return user;
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

  public void beforePassword(UserDto userDto) {

    System.out.println(userDto);
  }
}
