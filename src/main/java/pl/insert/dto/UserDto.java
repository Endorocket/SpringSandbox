package pl.insert.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserDto implements Serializable {

    @NotBlank(message = "is required", groups = Group1.class)
    private String username;

    @NotBlank(message = "is required", groups = Group2.class)
    private String password;

    @NotBlank(message = "is required", groups = Group2.class)
    private String matchingPassword;

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

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                '}';
    }

    interface Group1 {
    }

    interface Group2 {
    }
}
