package by.praded.ask_and_go.data_provider;

import org.testng.annotations.DataProvider;

/**
 * @author Kiryl Praded
 * 25.01.2021
 */
public class QuestionServiceImplDataProvider {
    @DataProvider(name = "existingQuestionsData")
    public static Object[][] existingQuestionsData() {
        return new Object[][]{{1L}, {2L}, {3L}};
    }

    @DataProvider(name = "nonExistingIdentities")
    public static Object[][] nonExistingQuestionsData() {
        return new Object[][]{{0L}, {250L}, {213L}};
    }

    @DataProvider(name = "categoryIdentities")
    public static Object[][] categoryIdentitiesData() {
        return new Object[][]{{1L}, {2L}, {3L}};
    }

    @DataProvider(name = "queryForSearch")
    public static Object[][] searchQueryData() {
        return new Object[][]{{""}, {"query"}};
    }

    @DataProvider(name = "validQuestionData")
    public static Object[][] createQuestionValidData() {
        return new Object[][]{
                {1L, 2L, "titlett", "text more than ten chars", "tag1, tag2, tag3"},
                {2L, 2L, "очередной вопрос", "валидный текст вопроса", "java, assembler, bytecode"},
                {2L, 1L, "заголовок вопроса", "текст, который проходит валидацию", ""}
        };
    }

    @DataProvider(name = "invalidQuestionData")
    public static Object[][] createQuestionInvalidData() {
        return new Object[][]{
                {1L, 2L, "t", "invalid", "tag1, tag2, tag3"},
                {2L, 2L, "title is valid", "invalid", ""},
                {2L, 1L, "title is valid", "текст, который проходит валидацию", "w, qweqweqewqweqweqweqewqweqweqweqewqwe"}
        };
    }

    @DataProvider(name = "validQuestionIdentities")
    public static Object[][] closeQuestionData() {
        return new Object[][]{{1L}, {2L}, {3L}};
    }

    @DataProvider(name = "questionIdToDelete")
    public static Object[][] identitesToDeleteData() {
        return new Object[][]{{4L}};
    }

    @DataProvider(name = "updateQuestionValidData")
    public static Object[][] updateValid() {
        return new Object[][]{
                {1L, "валидный тайтл вопроса", "text more than ten chars"},
                {2L, "очередной вопрос", "валидный текст вопроса"},
                {3L, "заголовок вопроса", "текст, который проходит валидацию"}
        };
    }

    @DataProvider(name = "updateQuestionInvalidData")
    public static Object[][] updateInvalid() {
        return new Object[][]{
                {1L, "t", "text more than ten chars"},
                {2L, "r", "invalid"},
                {3L, "valid title", "invalid"}
        };
    }
}
