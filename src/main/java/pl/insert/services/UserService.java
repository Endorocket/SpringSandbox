package pl.insert.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.insert.data.User;
import pl.insert.dto.UserDto;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    void save(UserDto userDto);
}
