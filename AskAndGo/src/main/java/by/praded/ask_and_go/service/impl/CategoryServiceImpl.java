package by.praded.ask_and_go.service.impl;

import by.praded.ask_and_go.dao.AvailableDao;
import by.praded.ask_and_go.dao.CategoryDao;
import by.praded.ask_and_go.dao.Transaction;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.dao.sql.TransactionFactory;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.service.AbstractService;
import by.praded.ask_and_go.service.CategoryService;
import by.praded.ask_and_go.service.exception.EntityNotExistsException;
import by.praded.ask_and_go.service.exception.ValidationException;
import by.praded.ask_and_go.service.util.CategoryValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Kiryl Praded
 * 22.12.2020
 * <p>
 * Implementation of category service.
 * @see Category
 * @see CategoryService
 * @see AbstractService
 */
public class CategoryServiceImpl implements CategoryService {

    /**
     * Method to find all categories.
     *
     * @return list of root categories.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao.
     */
    @Override
    public List<Category> findAllCategories() throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            CategoryDao dao = transaction.createDao(AvailableDao.CATEGORY);

            List<Category> rootCategories = dao.findAllRoots();

            for (Category category : rootCategories) {
                category.setSubcategories(dao.findSubcategoriesByRoot(category));
            }

            return rootCategories;
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }

    }

    /**
     * Method to create category.
     *
     * @param category - category to create.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao, when such category already exists.
     */
    @Override
    public void createCategory(Category category) throws ConnectionPoolException, DaoException, ValidationException {
        Transaction transaction = null;
        try {

            if (!CategoryValidator.validateCategoryName(category.getName())) {
                Map<String, String> validationErrors = new HashMap<>();
                validationErrors.put("category_error", "category.validation.error");
                throw new ValidationException(validationErrors);
            }

                transaction = TransactionFactory.getInstance().createTransaction(true);
            CategoryDao dao = transaction.createDao(AvailableDao.CATEGORY);
            dao.create(category);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }

    }

    /**
     * Method to delete category.
     *
     * @param categoryId - identity of category to delete.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao, when such category already exists.
     */
    @Override
    public void delete(Long categoryId) throws ConnectionPoolException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            CategoryDao dao = transaction.createDao(AvailableDao.CATEGORY);

            dao.delete(categoryId);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }

    }

    /**
     * Method to find category by identity
     *
     * @param id - identity of category to find.
     * @return category found by identity.
     * @throws ConnectionPoolException  - can be thrown on interaction exception with connection.
     * @throws EntityNotExistsException - can be thrown if such category not found.
     * @throws DaoException             - can be thrown on interaction exception with dao.
     */
    @Override
    public Category findCategoryById(Long id) throws ConnectionPoolException, EntityNotExistsException, DaoException {
        Transaction transaction = null;
        try {
            transaction = TransactionFactory.getInstance().createTransaction(true);
            CategoryDao dao = transaction.createDao(AvailableDao.CATEGORY);
            Optional<Category> category = Optional.ofNullable(dao.read(id));
            if (category.isPresent()) {
                return category.get();
            }
            throw new EntityNotExistsException();
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }

    }

    /**
     * Method to update category.
     *
     * @param category - category to update.
     * @throws ConnectionPoolException - can be thrown on interaction exception with connection.
     * @throws DaoException            - can be thrown on interaction exception with dao,
     *                                 when trying to update category to category which is already exists.
     */
    @Override
    public void updateCategory(Category category) throws ConnectionPoolException, DaoException, ValidationException {
        Transaction transaction = null;
        try {

            if (!CategoryValidator.validateCategoryName(category.getName())) {
                Map<String, String> validationErrors = new HashMap<>();
                validationErrors.put("category_error", "category.validation.error");
                throw new ValidationException(validationErrors);
            }
            transaction = TransactionFactory.getInstance().createTransaction(true);
            CategoryDao dao = transaction.createDao(AvailableDao.CATEGORY);

            dao.update(category);
        } finally {
            if (transaction != null) {
                transaction.close();
            }
        }

    }
}
