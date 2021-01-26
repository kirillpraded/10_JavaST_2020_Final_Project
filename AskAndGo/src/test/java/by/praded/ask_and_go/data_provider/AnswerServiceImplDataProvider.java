package by.praded.ask_and_go.data_provider;

import org.testng.annotations.DataProvider;

/**
 * @author Kiryl Praded
 * 26.01.2021
 */
public class AnswerServiceImplDataProvider {
    @DataProvider(name = "addAnswerValidData")
    public static Object[][] addAnswerValidData() {
        return new Object[][]{
                {1L, "Валидный текст ответа", 1L},
                {2L, "больше десяти символов", 1L},
                {1L, "it is valid text", 2L},
                {2L, "this text is more than ten symbols", 2L}
        };
    }

    @DataProvider(name = "addAnswerInvalidData")
    public static Object[][] addAnswerInvalidData() {
        return new Object[][]{
                {1L, "1", 1L},
                {2L, "", 1L},
                {1L, "rjnrrer", 2L},
                {2L, "Very Long Text Very Long Text Very Long Text Very Long " +
                        "Text Very Long Text Very Long Text Very Long Text Very" +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        " Very Long Text Very Long Text Very Long Text Very Long" +
                        " Text Very Long Text Very Long Text Very Long Text Very" +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        " Very Long Text Very Long Text Very Long Text Very Long" +
                        " Text Very Long Text Very Long Text Very Long Text " +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        " Very Long Text Very Long Text Very Long Text Very Long" +
                        " Text Very Long Text Very Long Text Very Long Text Very" +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        " Very Long Text Very Long Text Very Long Text Very Long" +
                        " Text Very Long Text Very Long Text Very Long Text Very" +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        "Very Long Text Very Long Text Very Long Text Very Long Text Very Long Text " +
                        "Very Long Text Very Long Text Very Long Text Very Long Text Very Long Text " +
                        "Very Long Text Very Long Text Very Long Text Very Long Text Very Long Text " +
                        "Very Long Text Very Long Text Very Long Text Very Long Text Very Long Text  ", 2L}
        };
    }

    @DataProvider(name = "markCorrectTestData")
    public static Object[][] markCorrectTestData() {
        return new Object[][]{
                {1L, 1L},
                {2L, 2L},
                {3L, 3L}
        };
    }

    @DataProvider(name = "toDeleteData")
    public static Object[][] toDeleteData() {
        return new Object[][]{{4L}};
    }

    @DataProvider(name = "validAnswerIdentities")
    public static Object[][] validAnswerIdentitiesData() {
        return new Object[][]{{1L}, {2L}, {3L}};
    }

    @DataProvider(name = "invalidAnswerIdentities")
    public static Object[][] invalidAnswerIdentitiesData() {
        return new Object[][]{
                {0L}, {999L}, {10000L}
        };
    }

    @DataProvider(name = "updateAnswerTextValidData")
    public static Object[][] updateAnswerTextValidData() {
        return new Object[][]{{1L, "text that passes validation"},
                {2L, "one more test case"},
                {3L, "валидный текст ответа"}};
    }

    @DataProvider(name = "updateAnswerTextInvalidData")
    public static Object[][] updateAnswerTextInvalidData() {
        return new Object[][]{{1L, "text"},
                {2L, ""},
                {3L, "Very Long Text Very Long Text Very Long Text Very Long " +
                        "Text Very Long Text Very Long Text Very Long Text Very" +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        " Very Long Text Very Long Text Very Long Text Very Long" +
                        " Text Very Long Text Very Long Text Very Long Text Very" +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        " Very Long Text Very Long Text Very Long Text Very Long" +
                        " Text Very Long Text Very Long Text Very Long Text " +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        " Very Long Text Very Long Text Very Long Text Very Long" +
                        " Text Very Long Text Very Long Text Very Long Text Very" +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        " Very Long Text Very Long Text Very Long Text Very Long" +
                        " Text Very Long Text Very Long Text Very Long Text Very" +
                        " Long Text Very Long Text Very Long Text Very Long Text" +
                        "Very Long Text Very Long Text Very Long Text Very Long Text Very Long Text " +
                        "Very Long Text Very Long Text Very Long Text Very Long Text Very Long Text " +
                        "Very Long Text Very Long Text Very Long Text Very Long Text Very Long Text " +
                        "Very Long Text Very Long Text Very Long Text Very Long Text Very Long Text  "}};
    }
}
