package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.dao.Transaction;
import by.praded.ask_and_go.dao.UserDao;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.dao.sql.TransactionFactory;
import by.praded.ask_and_go.dao.sql.TransactionImpl;
import by.praded.ask_and_go.entity.Role;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.AbstractService;
import by.praded.ask_and_go.service.UserService;
import by.praded.ask_and_go.service.exception.BadCredentialsException;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;
import by.praded.ask_and_go.service.util.UserValidator;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Kiryl Praded
 * 17.12.2020
 * <p>
 * Implementation of user service.
 * @see User
 * @see UserService
 * @see AbstractService
 */
public class UserServiceImpl implements UserService {
    /**
     * Method to authenticate user.
     *
     * @param username - username.
     * @param password - user's password.
     * @return instance of user, if pair username/password is correct.
     * @throws BadCredentialsException - throws if pair username/password is invalid.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public User authenticate(String username, String password) throws ConnectionPoolException, DaoException, BadCredentialsException {
        Transaction transaction = null;
        try {
            transaction = new TransactionImpl(true);

            UserDao dao = transaction.createDao(UserDao.class);
            Optional<User> user = Optional.ofNullable(dao.findByUsername(username));
            if (user.isPresent() && BCrypt.checkpw(password, user.get().getPassword())) {
                user.get().setPassword("");
                return user.get();
            }
            //если пара логин\пароль-невалидная
            throw new BadCredentialsException();
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to register user.
     *
     * @param username  - username to add.
     * @param password  - user password.
     * @param firstName - user first name.
     * @param lastName  - user last name.
     * @param email     - user email.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown if user with such username already exists.
     */
    @Override
    public void register(String username, String password, String passwordConfirmation, String firstName, String lastName, String email) throws ConnectionPoolException, DaoException, ValidationException {
        Transaction transaction = null;
        try {

            boolean isUsernameValid = UserValidator.validateUsername(username);
            boolean isPasswordValid = UserValidator.validatePassword(password);
            boolean isPasswordsEquals = UserValidator.validatePasswordConfirmation(password, passwordConfirmation);
            boolean isEmailValid = UserValidator.validateEmail(email);
            boolean isFirstNameValid = UserValidator.validateName(firstName);
            boolean isLastNameValid = UserValidator.validateName(lastName);

            boolean passedValidation = true;

            Map<String, String> validationErrorMap = new HashMap<>();

            if (!isUsernameValid) {
                validationErrorMap.put("username_error", "username.validation.error");
                passedValidation = false;
            }
            if (!isPasswordValid) {
                validationErrorMap.put("password_error", "password.validation.error");
                passedValidation = false;
            }
            if (!isPasswordsEquals) {
                validationErrorMap.put("password_confirmation_error", "password.match.error");
                passedValidation = false;
            }
            if (!isEmailValid) {
                validationErrorMap.put("email_error", "validation.email");
                passedValidation = false;
            }
            if (!isFirstNameValid) {
                validationErrorMap.put("first_name_error", "validation.firstname");
                passedValidation = false;
            }
            if (!isLastNameValid) {
                validationErrorMap.put("last_name_error", "validation.lastname");
                passedValidation = false;
            }

            if (!passedValidation) {
                throw new ValidationException(validationErrorMap);
            }
            transaction = new TransactionImpl(true);

            UserDao dao = transaction.createDao(UserDao.class);

            User user = new User();
            user.setLastName(lastName);
            user.setFirstName(firstName);
            user.setEmail(email);
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            user.setUsername(username);
            user.setRole(Role.WRITER);

            dao.create(user);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }


    }

    /**
     * Method to find all users.
     *
     * @return list of found users.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public List<User> findAllUsers() throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = new TransactionImpl(true);

            UserDao dao = transaction.createDao(UserDao.class);
            return dao.findAll();
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }

    }

    /**
     * Method to find user by identity.
     *
     * @param id - identity to find by.
     * @return user, found by given identity.
     * @throws EntityNotExistsException - can be throw if user with given identity doesn't exists.
     * @throws ConnectionPoolException  - can be thrown on interaction exception with connection.
     * @throws DaoException             - can be thrown on interaction exception with dao.
     */
    @Override
    public User findUserById(Long id) throws EntityNotExistsException, ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = new TransactionImpl(true);

            UserDao dao = transaction.createDao(UserDao.class);
            Optional<User> user = Optional.ofNullable(dao.read(id));

            if (user.isPresent()) {
                //если юзер по такому айди найден - возвращаем его
                return user.get();
            }
            //если такого айди не существует - кидаем исключение
            throw new EntityNotExistsException();

        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to update user role.
     *
     * @param user - user with updated role to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void updateUserRole(User user) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = new TransactionImpl(true);

            UserDao dao = transaction.createDao(UserDao.class);
            dao.updateRole(user);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to delete user by identity.
     *
     * @param id - identity of user to delete.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void deleteUser(Long id) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);

            UserDao dao = transaction.createDao(UserDao.class);
            dao.delete(id);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to update user personal info.
     *
     * @param user - user to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void updatePersonalInfo(User user) throws ConnectionPoolException, DaoException, ValidationException {
        Transaction transaction = null;
        try {

            boolean emailIsValid = UserValidator.validateEmail(user.getEmail());
            boolean firstNameIsValid = UserValidator.validateName(user.getFirstName());
            boolean lastNameIsValid = UserValidator.validateName(user.getLastName());
            Map<String, String> validationErrorMap = new HashMap<>();

            if (!emailIsValid) {
                validationErrorMap.put("email_msg", "validation.email");
            }
            if (!firstNameIsValid) {
                validationErrorMap.put("first_name_msg", "validation.firstname");
            }
            if (!lastNameIsValid) {
                validationErrorMap.put("last_name_msg", "validation.lastname");
            }

            if (!(emailIsValid && firstNameIsValid && lastNameIsValid)) {
                throw new ValidationException(validationErrorMap);
            }

            transaction = TransactionFactory.getInstance().createTransaction(true);
            UserDao dao = transaction.createDao(UserDao.class);

            dao.update(user);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to change password.
     *
     * @param user        - user to update the password.
     * @param newPassword - new password for user.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws BadCredentialsException - can be thrown if pair username/password is invalid.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void changePassword(User user, String newPassword, String newPasswordConfirmation) throws ConnectionPoolException, BadCredentialsException, DaoException, ValidationException {
        Transaction transaction = null;
        try {
            boolean newPasswordIsValid = UserValidator.validatePassword(newPassword);
            boolean passwordConfirmation = UserValidator.validatePasswordConfirmation(newPassword, newPasswordConfirmation);
            Map<String, String> validationErrorMap = new HashMap<>();

            if (!newPasswordIsValid) {
                validationErrorMap.put("error_match", "password.validation.error");
            }
            if (!passwordConfirmation && newPasswordIsValid) {
                validationErrorMap.put("error_match", "password.match.error");
            }

            if (!(newPasswordIsValid && passwordConfirmation)) {
                throw new ValidationException(validationErrorMap);
            }

            transaction = TransactionFactory.getInstance().createTransaction(true);
            UserDao dao = transaction.createDao(UserDao.class);

            Optional<User> optionalUser = Optional.ofNullable(dao.findByUsername(user.getUsername()));
            if (optionalUser.isPresent() && BCrypt.checkpw(user.getPassword(), optionalUser.get().getPassword())) {
                //если логин и пароль - ок
                user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
                dao.updatePassword(user);
            } else {
                //если пара логин\пароль-неверная
                throw new BadCredentialsException();
            }
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }

    /**
     * Method to change user profile image.
     *
     * @param user - user to update the profile image.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public void updateProfileImage(User user) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            UserDao dao = transaction.createDao(UserDao.class);
            dao.updateProfileImage(user);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }
    }
}
