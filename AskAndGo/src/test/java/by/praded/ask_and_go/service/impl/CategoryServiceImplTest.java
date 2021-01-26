package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.data_provider.CategoryServiceImplDataProvider;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.service.CategoryService;
import by.praded.ask_and_go.service.Service;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

/**
 * @author Kiryl Praded
 * 25.01.2021
 */
@Test(dataProviderClass = CategoryServiceImplDataProvider.class)
public class CategoryServiceImplTest {
    private final CategoryService categoryService = ServiceProvider.getInstance().takeService(Service.CATEGORY);


    @Test(description = "testing method to find all categories")
    public void findAllCategoriesTest() {
        boolean success = true;
        try {
            categoryService.findAllCategories();
        } catch (DaoException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "successful creating category test", dataProvider = "correctCategories")
    public void createCategorySuccessTest(String categoryName, Long parentId) {
        Category category = new Category();
        category.setName(categoryName);
        if (Objects.nonNull(parentId)) {
            Category parent = new Category();
            parent.setId(parentId);
            category.setParent(parent);
        }
        boolean success = true;
        try {
            categoryService.createCategory(category);
        } catch (ConnectionPoolException | DaoException | ValidationException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "expect validation exception during creation categy", dataProvider = "invalidCategoryNames")
    public void createCategoryExpectValidationException(String name, Long parentId) {
        Category category = new Category();
        category.setName(name);
        if (Objects.nonNull(parentId)) {
            Category parent = new Category();
            parent.setId(parentId);
            category.setParent(parent);
        }
        Assert.assertThrows(ValidationException.class, () -> categoryService.createCategory(category));
    }

    @Test(description = "expect dao exception during trying to add same categories", dataProvider = "sameCategories")
    public void createCategoryExpectDaoExc(String name, Long parentId) {
        Category category = new Category();
        category.setName(name);
        if (Objects.nonNull(parentId)) {
            Category parent = new Category();
            parent.setId(parentId);
            category.setParent(parent);
        }
        Assert.assertThrows(DaoException.class, () -> categoryService.createCategory(category));
    }

    @Test(description = "deleting test without exceptions", dataProvider = "toDeleteCategory")
    public void deleteCategoryTest(Long id) {
        boolean success = true;
        try {
            categoryService.delete(id);
        } catch (DaoException | ConnectionPoolException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "find category by identity test", dataProvider = "findCategoryByIdData")
    public void findByIdentityTest(Long id, String name) {
        try {
            String nameDb = categoryService.findCategoryById(id).getName();
            Assert.assertEquals(nameDb, name);
        } catch (ConnectionPoolException | EntityNotExistsException | DaoException e) {
            Assert.fail();
        }
    }

    @Test(description = "test successful editing category", dataProvider = "categoryToEditData")
    public void updateCategoryTest(Long id, String newName) {
        Category category = new Category();
        category.setId(id);
        category.setName(newName);
        boolean success = true;
        try {
            categoryService.updateCategory(category);
        } catch (ConnectionPoolException | DaoException | ValidationException e) {
            success = false;
        }
        Assert.assertTrue(success);
    }

    @Test(description = "Test updating category with invalid data", dataProvider = "toEditInvalidData")
    public void updateCategoryExpectValidationException(Long id, String newName) {
        Category category = new Category();
        category.setId(id);
        category.setName(newName);
        Assert.assertThrows(ValidationException.class, () -> categoryService.updateCategory(category));
    }

}
