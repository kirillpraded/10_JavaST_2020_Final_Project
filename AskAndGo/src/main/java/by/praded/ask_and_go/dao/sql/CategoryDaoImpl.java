package by.praded.ask_and_go.dao.sql;

import by.praded.ask_and_go.dao.CategoryDao;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiryl Praded
 * 13.12.2020
 * <p>
 * Category dao implemetation for sql.
 * @see CategoryDao
 * @see by.praded.ask_and_go.dao.Dao
 * @see BaseDaoImpl
 * @see Category
 */
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

    /**
     * Dao class constructor to create dao only with connection.
     *
     * @param connection - for connecting with database.
     */
    public CategoryDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method to create connection in storage.
     *
     * @param category - category to insert.
     * @return generated identity of category.
     * @throws DaoException - exception may occurs during the insertion.
     */
    @Override
    public Long create(Category category) throws DaoException {
        if (category.getParent() != null) {
            return createSubcategory(category);
        } else {
            return createRootCategory(category);
        }
    }

    /**
     * Special case of creation with {@code null} parent category.
     *
     * @param category - category to insert.
     * @return generated identity of category.
     * @throws DaoException - exception may occurs during the insertion.
     */
    private Long createRootCategory(Category category) throws DaoException {
        String rootCategorySql = "INSERT INTO `category` (`name`)" +
                " VALUE (?)";
        try (PreparedStatement statement = connection.prepareStatement(rootCategorySql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                throw new DaoException("There is no autoincremented index after trying to add entry into the table `category`");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Special case of creation with non-null parent category.
     *
     * @param category - category to insert.
     * @return generated identity of category.
     * @throws DaoException - exception may occurs during the insertion.
     */
    private Long createSubcategory(Category category) throws DaoException {
        String sql = "INSERT INTO `category` (`parent_id`, `name`)" +
                "VALUE (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, category.getParent().getId());
            statement.setString(2, category.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                throw new DaoException();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to read category from the database.
     *
     * @param id - identity of the category to read.
     * @return category that its found.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public Category read(Long id) throws DaoException {
        String categorySql = "SELECT `parent_id`, `name` FROM `category` WHERE `id` = ?";
        Category category = null;
        try (PreparedStatement statement = connection.prepareStatement(categorySql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = new Category();
                category.setId(id);
                category.setName(resultSet.getString("name"));
                Long parentId = resultSet.getObject("parent_id", Long.class);
                if (parentId != null) {
                    category.setParent(read(parentId));
                }
                category.setSubcategories(findSubcategoriesByRoot(category));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return category;
    }

    /**
     * Method to update category in database.
     *
     * @param category - category to update
     * @throws DaoException - exception may occurs during the updating.
     */
    @Override
    public void update(Category category) throws DaoException {
        if (category.getParent() != null) {
            updateSubcategory(category);
        } else {
            updateRootCategory(category);
        }
    }

    /**
     * Special case of updating with {@code null} parent category.
     *
     * @param category - category to update
     * @throws DaoException - exception may occurs during the updating.
     */
    private void updateRootCategory(Category category) throws DaoException {
        String parentCategorySql = "UPDATE `category` SET `name` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(parentCategorySql)) {

            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());
            statement.executeUpdate();

            removeParent(category);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Special case of updating with {@code non-null} parent category.
     *
     * @param category - category to update
     * @throws DaoException - exception may occurs during the updating.
     */
    private void updateSubcategory(Category category) throws DaoException {
        String subcategorySql = "UPDATE `category` SET `parent_id` = ?, `name` = ?  WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(subcategorySql)) {
            statement.setLong(1, category.getParent().getId());
            statement.setString(2, category.getName());
            statement.setLong(3, category.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to set parent category null.
     *
     * @param category - category, where to set parent null.
     * @throws DaoException - exception may occurs during the updating.
     */
    private void removeParent(Category category) throws DaoException {
        String sql = "UPDATE `category` SET `parent_id` = NULL where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, category.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to delete category from database.
     *
     * @param id - identity of category to delete.
     * @throws DaoException - exception may occurs during the deleting.
     */
    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `category` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to find all roots categories.
     * Categories, where parent category is null.
     *
     * @return list of root categories.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public List<Category> findAllRoots() throws DaoException {
        String sql = "SELECT `id`, `name` FROM `category` WHERE `parent_id` IS NULL";
        List<Category> rootCategories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setName(resultSet.getString("name"));
                Long id = resultSet.getLong("id");
                category.setId(id);
                rootCategories.add(category);
            }
            return rootCategories;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to find all subcategories by parent category.
     *
     * @param root - parent category to search by.
     * @return list of subcategories.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public List<Category> findSubcategoriesByRoot(Category root) throws DaoException {
        String sql = "SELECT `id`, `name` FROM `category` WHERE `parent_id` = ?";
        List<Category> subcategories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, root.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setName(resultSet.getString("name"));
                category.setId(resultSet.getLong("id"));
                category.setParent(root);
                category.setSubcategories(findSubcategoriesByRoot(category));
                subcategories.add(category);

            }
            return subcategories;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
