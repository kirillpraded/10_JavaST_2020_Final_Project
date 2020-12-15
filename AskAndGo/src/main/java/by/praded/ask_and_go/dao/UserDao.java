package by.praded.ask_and_go.dao;

import by.praded.ask_and_go.entity.User;

import java.util.List;

/**
 * @author Kiryl Praded
 * 13.12.2020
 */
public interface UserDao extends Dao<User> {
    User findByUsernameAndPassword(String login, String password);

    List<User> findAll();
}
