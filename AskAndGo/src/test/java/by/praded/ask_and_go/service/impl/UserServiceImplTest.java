package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.data_provider.UserServiceImplDataProvider;
import by.praded.ask_and_go.entity.User;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.UserService;
import by.praded.ask_and_go.service.exception.BadCredentialsException;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Kiryl Praded
 * 25.01.2021
 */
@Test(dataProviderClass = UserServiceImplDataProvider.class)
public class UserServiceImplTest {
    private final UserService userService = ServiceProvider.getInstance().takeService(Service.USER);


    @BeforeTest
    public void fillTablesBefore() throws DaoException, ConnectionPoolException, ValidationException {

    }

    @Test(description = "test success registration", dataProvider = "registerSuccess")
    public void registrationSuccessTest(String username, String password, String passwordConfirmation,
                                        String firstName, String lastName, String email) {
        boolean success = true;
        try {
            userService.register(username, password, passwordConfirmation, firstName, lastName, email);
        } catch (DaoException | ValidationException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }


    @Test(description = "test dao exception when user already exists.", dataProvider = "registerDaoExceptionExpected")
    public void registrationDaoExcTest(String username, String password, String passwordConfirmation, String firstName, String lastName, String email) {
        Assert.assertThrows(DaoException.class, () -> userService.register(username, password, passwordConfirmation, firstName, lastName, email));
    }

    @Test(description = "expect validation exception", dataProvider = "registerValidationExceptionExpected")
    public void registrationValidationExceptionTest(String username, String password, String passwordConfirmation, String firstName, String lastName, String email) {
        Assert.assertThrows(ValidationException.class, () -> userService.register(username, password, passwordConfirmation, firstName, lastName, email));
    }

    @Test(description = "test of correct auth", dataProvider = "authSuccessTest")
    public void authTest(String uname, String password) throws DaoException, ConnectionPoolException, BadCredentialsException {
        Assert.assertEquals(uname, userService.authenticate(uname, password).getUsername());
    }

    @Test(description = "test of bad credentials exc during auth", dataProvider = "badAuthData")
    public void badAuthTest(String uname, String password) {
        Assert.assertThrows(BadCredentialsException.class, () -> userService.authenticate(uname, password));
    }

    @Test(description = "find all users test")
    public void findAllUsersTest() throws ConnectionPoolException, DaoException {
        Assert.assertFalse(userService.findAllUsers().isEmpty());
    }

    @Test(description = "find user by identity than doesn't exists", dataProvider = "wrongIdentities")
    public void findUserByIdExpectEntityNotExistsTest(Long id) {
        Assert.assertThrows(EntityNotExistsException.class, () -> userService.findUserById(id));
    }

    @Test(description = "find user by identity", dataProvider = "existingUsersIdentities")
    public void findUserByIdTest(Long id) {
        boolean success = true;
        try {
            userService.findUserById(id);
        } catch (DaoException | EntityNotExistsException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "success updating personal info test", dataProvider = "authSuccessTest")
    public void updatePersonalInfoTest(String username, String password) {
        boolean success = true;
        try {
            User user = userService.authenticate(username, password);
            user.setFirstName("updatedFirstName");
            userService.updatePersonalInfo(user);
        } catch (ValidationException | BadCredentialsException | ConnectionPoolException | DaoException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "expect validation exception while updating personal info", dataProvider = "invalidPersonalInfo")
    public void updatePersonalInfoExpectValidationExceptionTest(String invalidName, String invalidEmail, String username, String password) throws DaoException, ConnectionPoolException, BadCredentialsException {

        User user = userService.authenticate(username, password);
        user.setFirstName(invalidName);
        user.setEmail(invalidEmail);
        Assert.assertThrows(ValidationException.class, () -> userService.updatePersonalInfo(user));
    }

    @Test(description = "change password test(success)", dataProvider = "credentialsForChangePassword")
    public void updatePasswordTest(String username, String password) {
        boolean success = true;
        try {
            User user = userService.authenticate(username, password);
            user.setPassword(password);
            String newPassword = "newPassword";
            userService.changePassword(user, newPassword, newPassword);
        } catch (DaoException | ConnectionPoolException | BadCredentialsException | ValidationException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "bad credentials while changing password", dataProvider = "badAuthData")
    public void changePasswordExpectBadCredentials(String uname, String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(uname);
        Assert.assertThrows(BadCredentialsException.class, () ->
                userService.changePassword(user, "password", "password"));
    }

    @Test(description = "validation exception while changing password", dataProvider = "wrongPasswords")
    public void changePasswordExpectValidationException(String uname, String uPassword, String password, String confirmation) {
        User user = new User();
        user.setPassword(uname);
        user.setUsername(uPassword);
        Assert.assertThrows(ValidationException.class, () ->
                userService.changePassword(user, password, confirmation));
    }
}
