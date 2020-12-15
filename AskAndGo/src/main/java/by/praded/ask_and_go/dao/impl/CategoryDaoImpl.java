package by.praded.ask_and_go.dao.impl;

import by.praded.ask_and_go.dao.CategoryDao;
import by.praded.ask_and_go.entity.Category;

import java.util.List;

/**
 * @author Kiryl Praded
 * 13.12.2020
 */
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {
    @Override
    public boolean create(Category entity) {
        return false;
    }

    @Override
    public Category read(Long id) {
        return null;
    }

    @Override
    public boolean update(Category entity) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }
}
