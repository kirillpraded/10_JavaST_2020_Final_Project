package by.praded.ask_and_go.dao;


import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Tag;

import java.util.List;

/**
 * @author Kiryl Praded
 * 13.12.2020
 * <p>
 * Dao with methods declaration for tag.
 * @see Tag
 * @see Dao
 */
public interface TagDao extends Dao<Tag> {
    /**
     * Declaration of the method to find tag by its text.
     *
     * @param tagText - tag text to search by.
     * @return list of tags found by tag text.
     * @throws DaoException - exception may occurs during the reading.
     */
    List<Tag> findByText(String tagText) throws DaoException;

}
