package by.praded.ask_and_go.dao;

import by.praded.ask_and_go.entity.Entity;

import java.util.List;

/**
 * @author Kiryl Praded
 * 12.12.2020
 */
public interface Dao<Type extends Entity> {

    boolean create(Type entity);

    Type read(Long id);

    boolean update(Type entity);

    boolean delete(Long id);

    List<Type> findAll();
}
