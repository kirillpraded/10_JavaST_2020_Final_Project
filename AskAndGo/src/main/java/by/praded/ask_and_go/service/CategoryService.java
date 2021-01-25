package by.praded.ask_and_go.service;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;

import java.util.List;

/**
 * @author Kiryl Praded
 * 22.12.2020
 * <p>
 * Service with methods for actions on categories.
 * @see Category
 * @see by.praded.ask_and_go.dao.CategoryDao
 * @see AbstractService
 */
public interface CategoryService extends AbstractService {
    /**
     * Declaration of method to find all categories.
     *
     * @return list of root categories.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    List<Category> findAllCategories() throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to create category.
     *
     * @param category - category to create.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao, when such category already exists.
     */
    void createCategory(Category category) throws ConnectionPoolException, DaoException, ValidationException;

    /**
     * Declaration of method to delete category.
     *
     * @param categoryId - identity of category to delete.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao, when such category already exists.
     */
    void delete(Long categoryId) throws ConnectionPoolException, DaoException;

    /**
     * Declaration of method to find category by identity
     *
     * @param id - identity of category to find.
     * @return category found by identity.
     * @throws ConnectionPoolException  - can be thrown on interaction exception with connection.
     * @throws EntityNotExistsException - can be thrown if such category not found.
     * @throws DaoException             - can be thrown on interaction exception with dao.
     */
    Category findCategoryById(Long id) throws ConnectionPoolException, EntityNotExistsException, DaoException;

    /**
     * Declaration of method to update category.
     *
     * @param category - category to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao,
     *                                 when trying to update category to category which is already exists.
     */
    void updateCategory(Category category) throws ConnectionPoolException, DaoException, ValidationException;
}
