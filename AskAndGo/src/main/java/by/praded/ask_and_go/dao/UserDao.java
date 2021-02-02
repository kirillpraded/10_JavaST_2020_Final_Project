package by.praded.ask_and_go.dao;

import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Kiryl Praded
 * 13.12.2020
 * <p>
 * Dao with methods declaration for user.
 * @see User
 * @see Dao
 */
public interface UserDao extends Dao<User> {
    /**
     * Declaration of the method to find user by username and password.
     *
     * @param login    - username
     * @param password - user's password
     * @return optional of user, found by username and password.
     * @throws DaoException - exception may occurs during the reading.
     */
    Optional<User> findByUsernameAndPassword(String login, String password) throws DaoException;

    /**
     * Declaration of the method to find user by username.
     *
     * @param username - username to search by.
     * @return optional of user. found by username.
     * @throws DaoException - exception may occurs during the reading.
     */
    Optional<User> findByUsername(String username) throws DaoException;

    /**
     * Declaration of the method to find all users.
     *
     * @return list of all users.
     * @throws DaoException - exception may occurs during the reading.
     */
    List<User> findAll() throws DaoException;

    /**
     * Declaration of the method to update user role.
     *
     * @param user - user to update the role.
     * @throws DaoException - exception may occurs during the updating.
     */
    void updateRole(User user) throws DaoException;

    /**
     * Declaration of the method to update user password.
     *
     * @param user - user to update the password.
     * @throws DaoException - exception may occurs during the updating.
     */
    void updatePassword(User user) throws DaoException;

    /**
     * Declaration of the method to update user profile image.
     *
     * @param user - user to update profile image.
     * @throws DaoException - exception may occurs during the updating.
     */
    void updateProfileImage(User user) throws DaoException;
}
