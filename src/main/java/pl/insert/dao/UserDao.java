package pl.insert.dao;

import pl.insert.data.User;

public interface UserDao {

    User findByUsername(String username);

    void save(User user);
}
