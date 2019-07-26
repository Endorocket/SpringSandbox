package pl.insert.dao;

import org.springframework.stereotype.Repository;
import pl.insert.data.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        return entityManager.find(User.class, username);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
