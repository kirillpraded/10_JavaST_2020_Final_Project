package by.praded.ask_and_go.dao;

import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Entity;

/**
 * @author Kiryl Praded
 * 12.12.2020
 * <p>
 * Default Dao interface with declarations of CRUD operations.
 */
public interface Dao<Type extends Entity> {

    /**
     * Declaration of method that creates entity in storage.
     *
     * @param entity - entity to insert.
     * @return generated identity of entity.
     * @throws DaoException - exception may occurs during the insertion.
     */
    Long create(Type entity) throws DaoException;

    /**
     * Declaration of method that read an entity from storage.
     *
     * @param id - identity of the entity to read.
     * @return entity that its found.
     * @throws DaoException - exception may occurs during the reading.
     */
    Type read(Long id) throws DaoException;

    /**
     * Declaration of method that update an entity in storage.
     *
     * @param entity - entity to update
     * @throws DaoException - exception may occurs during the updating.
     */
    void update(Type entity) throws DaoException;

    /**
     * Declaration of the method that delete an entity from storage.
     *
     * @param id - identity of entity to delete.
     * @throws DaoException - exception may occurs during the deleting.
     */
    void delete(Long id) throws DaoException;
}
