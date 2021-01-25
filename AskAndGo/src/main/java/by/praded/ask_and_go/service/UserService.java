package by.praded.ask_and_go.service;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.exception.BadCredentialsException;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;

import java.util.List;

/**
 * @author Kiryl Praded
 * 17.12.2020
 * <p>
 * Service with methods for actions on users.
 * @see by.praded.ask_and_go.dao.UserDao
 * @see User
 * @see AbstractService
 */
public interface UserService extends AbstractService {
    /**
     * Declaration of method to authenticate user.
     *
     * @param username - username.
     * @param password - user's password.
     * @return instance of user, if pair username/password is correct.
     * @throws BadCredentialsException - throws if pair username/password is invalid.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    User authenticate(String username, String password)
            throws BadCredentialsException, ConnectionPoolException, DaoException;

    /**
     * Declaration of method to find user by identity.
     *
     * @param id - identity to find by.
     * @return user, found by given identity.
     * @throws EntityNotExistsException - can be throw if user with given identity doesn't exists.
     * @throws ConnectionPoolException  - can be thrown on interaction exception with connection.
     * @throws DaoException             - can be thrown on interaction exception with dao.
     */
    User findUserById(Long id) throws EntityNotExistsException, ConnectionPoolException, DaoException;

    /**
     * Declaration of method to find all users.
     *
     * @return list of found users.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    List<User> findAllUsers() throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to register user.
     *
     * @param username  - username to add.
     * @param password  - user password.
     * @param firstName - user first name.
     * @param lastName  - user last name.
     * @param email     - user email.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown if user with such username already exists.
     */
    void register(String username, String password, String passwordConfirmation, String firstName, String lastName, String email)
            throws ConnectionPoolException, DaoException, ValidationException;

    /**
     * Declaration of method to update user role.
     *
     * @param user - user with updated role to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void updateUserRole(User user) throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to delete user by identity.
     *
     * @param id - identity of user to delete.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void deleteUser(Long id) throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to update user personal info.
     *
     * @param user - user to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void updatePersonalInfo(User user) throws ConnectionPoolException, DaoException, ValidationException;

    /**
     * Declaration of method to change password.
     *
     * @param user        - user to update the password.
     * @param newPassword - new password for user.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws BadCredentialsException - can be thrown if pair username/password is invalid.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void changePassword(User user, String newPassword, String newPasswordConfirmation) throws ConnectionPoolException, BadCredentialsException, DaoException, ValidationException;

    /**
     * Declaration of method to change user profile image.
     *
     * @param user - user to update the profile image.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    void updateProfileImage(User user) throws ConnectionPoolException, DaoException;
}

