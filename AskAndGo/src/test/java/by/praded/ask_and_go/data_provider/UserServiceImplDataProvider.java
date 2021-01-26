package by.praded.ask_and_go.data_provider;

import org.testng.annotations.DataProvider;

/**
 * @author Kiryl Praded
 * 25.01.2021
 */
public class UserServiceImplDataProvider {
    @DataProvider(name = "registerSuccess")
    public static Object[][] registerSuccessDataProvider() {
        return new Object[][]{{"kirillpraded1", "password", "password", "kirill", "praded", "kirillpraded@bk.ru"},
                {"kirillpraded2", "password", "password", "kirill", "praded", "kirillpraded@bk.ru"},
                {"kirillpraded3", "password", "password", "kirill", "praded", "kirillpraded@bk.ru"}};

    }

    @DataProvider(name = "registerDaoExceptionExpected")
    public static Object[][] registerDaoExcExpect() {
        return new Object[][]{{"testUser1", "password", "password", "kirill", "praded", "kirillpraded@bk.ru"},
                {"testUser2", "password", "password", "kirill", "praded", "kirillpraded@bk.ru"},
                {"testUser3", "password", "password", "kirill", "praded", "kirillpraded@bk.ru"},
                {"testUser4", "password", "password", "kirill", "praded", "kirillpraded@bk.ru"}};
    }

    @DataProvider(name = "registerValidationExceptionExpected")
    public static Object[][] registerValidationExcExpect() {
        return new Object[][]{{"alreadyRegistered", "password", "passwor1d", "kirill", "praded", "kirillpraded@bk.ru"},
                {"al", "pas", "pas", "kirill", "praded", "NOT_EMAIL"},
                {"alreadyRegistered", "password", "password", "VeryLongNameVeryLongNameVeryLongNameVeryLongNameVeryLongName", "praded", "kirillpraded@bk.ru"}};
    }

    @DataProvider(name = "authSuccessTest")
    public static Object[][] authTestData() {
        return new Object[][]{{"testUser1", "password"},
                {"testUser2", "password"},
                {"testUser3", "password"}};
    }

    @DataProvider(name = "badAuthData")
    public static Object[][] badCredentialsData() {
        return new Object[][]{{"noSuchUser", "QWeReWrDSP"},
                {"unknownPanda", "password416"},
                {"undefinedUser", "password"}};
    }

    @DataProvider(name = "wrongIdentities")
    public static Object[][] wrongIdentitiesData() {
        return new Object[][]{{-1L}, {0L}, {999999L}};
    }

    @DataProvider(name = "existingUsersIdentities")
    public static Object[][] existingIdentities() {
        return new Object[][]{{1L}, {2L}, {3L}, {4L}};
    }

    @DataProvider(name = "alreadyRegisteredUsers")
    public static Object[][] alreadyRegistered() {
        return new Object[][]{{"userForTest", "QWeReWrDSP"},
                {"anotherUser", "password416"}};
    }

    @DataProvider(name = "wrongPasswords")
    public static Object[][] wrongPasswords() {
        return new Object[][]{{"testUser1", "password", "password1", "password"},
                {"testUser1", "password", "pass", "pass"},
                {"testUser1", "password", "pa s", "pa s"},
                {"testUser1", "password", null, null},
                {"testUser1", "password", "veryLongPasswordVeryLongPassword", "veryLongPasswordVeryLongPassword"}};
    }

    @DataProvider(name = "invalidPersonalInfo")
    public static Object[][] invalidPersonal() {
        return new Object[][]{{"Invalid name%433d44#$;;dfs; ", "email@email", "testUser1", "password"},
                {"VeryLongFirstNameVeryLongFirstNameVeryLongFirstNameVeryLongFirstNameVeryLongFirstName", "Email with whitespaces", "testUser2", "password"},
                {"VeryLongFirstNameVeryLongFirstNameVeryLongFirstNameVeryLongFirstNameVeryLongFirstName", null, "testUser1", "password"},
                {null, "kirillpraded@email", "testUser1", "password"},
                {null, null, "testUser1", "password"}};
    }

    @DataProvider(name = "credentialsForChangePassword")
    public static Object[][] credsForChange() {
        return new Object[][]{
                {"testUser4", "password"}
        };
    }

}
