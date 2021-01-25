package by.praded.ask_and_go.dao;

import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Category;

import java.util.List;

/**
 * @author Kiryl Praded
 * 13.12.2020
 * <p>
 * Dao with methods for category.
 * @see Category
 * @see Dao
 */
public interface CategoryDao extends Dao<Category> {
    /**
     * Declaration of the method to find all roots categories.
     * Categories, where parent category is null.
     *
     * @return list of root categories.
     * @throws DaoException - exception may occurs during the reading.
     */
    List<Category> findAllRoots() throws DaoException;

    /**
     * Declaration of the method to find all subcategories by parent category.
     *
     * @param root - parent category to search by.
     * @return list of subcategories.
     * @throws DaoException - exception may occurs during the reading.
     */
    List<Category> findSubcategoriesByRoot(Category root) throws DaoException;
}
