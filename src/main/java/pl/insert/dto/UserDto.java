package pl.insert.dto;

import pl.insert.validation.FieldMatch;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match", groups = UserDto.Group2.class)
public class UserDto implements Serializable {

  @NotBlank(message = "is required", groups = Group1.class)
  private String username;

  @NotBlank(message = "is required", groups = Group2.class)
  private String password;

  @NotBlank(message = "is required", groups = Group2.class)
  private String matchingPassword;

  private List<String> strings = new ArrayList<>();

  private List<SurveyDto> surveys = new ArrayList<>();

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

  public List<String> getStrings() {
    return strings;
  }

  public void setStrings(List<String> strings) {
    this.strings = strings;
  }

  public List<SurveyDto> getSurveys() {
    return surveys;
  }

  public void setSurveys(List<SurveyDto> surveys) {
    this.surveys = surveys;
  }

    @Override
    public String toString() {
        return "UserDto{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", matchingPassword='" + matchingPassword + '\'' +
            ", strings=" + strings +
            ", surveys=" + surveys +
            '}';
    }

    public interface Group1 {
  }

  public interface Group2 {
  }
}
