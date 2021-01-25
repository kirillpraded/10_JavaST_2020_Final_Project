package by.praded.ask_and_go.service.util;

import by.praded.ask_and_go.entity.Role;
import by.praded.ask_and_go.entity.User;

import java.util.Objects;

/**
 * @author Kiryl Praded
 * 19.12.2020
 * <p>
 * Validator class for users.
 * @see User
 */
public class UserValidator {
    /**
     * Method to validate user password.
     *
     * @param password - password to validate.
     * @return {@code true} if password is valid, {@code false} otherwise.
     * @see User
     */
    public static boolean validatePassword(String password) {
        return Objects.nonNull(password) && !password.isEmpty() && !password.contains(" ") && password.length() >= 6 && password.length() <= 20;
    }

    /**
     * Method to validate password confirmation.
     *
     * @param password     - entered password.
     * @param confirmation - entered password confirmation.
     * @return {@code true} if passwords are equals, {@code false} otherwise.
     * @see User
     */
    public static boolean validatePasswordConfirmation(String password, String confirmation) {
        return Objects.nonNull(password) && Objects.nonNull(confirmation) && password.equals(confirmation);
    }

    /**
     * Method to validate username.
     *
     * @param username - username to validate.
     * @return {@code true} if username is valid, {@code false} otherwise.
     * @see User
     */
    public static boolean validateUsername(String username) {
        return Objects.nonNull(username) && !username.contains(" ") && !username.isEmpty() && username.length() >= 3 && username.length() <= 20;
    }

    /**
     * Method to validate email.
     *
     * @param email - email to validate.
     * @return {@code true} if email is valid, {@code false} otherwise.
     * @see User
     */
    public static boolean validateEmail(String email) {
        return Objects.nonNull(email) &&
                email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$") &&
                email.length() <= 50;
    }

    /**
     * Method to validate user name.
     *
     * @param name - name to validate.
     * @return {@code true} if name is valid, {@code false} otherwise.
     * @see User
     */
    public static boolean validateName(String name) {
        return Objects.nonNull(name) && name.matches("\\D+") && name.length() <= 50;
    }

    /**
     * Method to validate user role.
     *
     * @param user     - user to check cole.
     * @param expected - expected user role.
     * @return {@code true} if user role equals to expected role, {@code false} otherwise.
     * @see User
     */
    public static boolean validateRole(User user, Role expected) {
        return user.getRole() == expected;
    }
}
