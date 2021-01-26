package by.praded.ask_and_go.data_provider;

import org.testng.annotations.DataProvider;

/**
 * @author Kiryl Praded
 * 25.01.2021
 */
public class CategoryServiceImplDataProvider {

    @DataProvider(name = "correctCategories")
    public static Object[][] correctCategoriesData() {
        return new Object[][]{
                {"C#", null},
                {"List", 1L},
                {"Assembler", null},
                {"C++", null}};

    }

    @DataProvider(name = "invalidCategoryNames")
    public static Object[][] invalidCategoryData() {
        return new Object[][]{
                {null, null},
                {"more than 50 chars 123123123123123123more than 50 chars 123123123123123123", null},
                {"", null}};
    }

    @DataProvider(name = "sameCategories")
    public static Object[][] sameCategoriesData() {
        return new Object[][]{
                {"Collections", 1L},
                {"Multithreading", 1L}};
    }

    @DataProvider(name = "toDeleteCategory")
    public static Object[][] toDeleteData() {
        return new Object[][]{{4L}, {123123L}};
    }

    @DataProvider(name = "findCategoryByIdData")
    public static Object[][] toFindData() {
        return new Object[][]{{1L, "Java"}, {2L, "Collections"}, {3L, "Multithreading"}};
    }

    @DataProvider(name = "categoryToEditData")
    public static Object[][] toEditData() {
        return new Object[][]{{5L, "updated category"}};

    }

    @DataProvider(name = "toEditInvalidData")
    public static Object[][] toEditInvalidData() {
        return new Object[][]{{5L, ""}, {5L, null}, {5L, ""},
                {5L, "more than 50 chars 123123123123123123more than 50 chars 123123123123123123"}};

    }
}
