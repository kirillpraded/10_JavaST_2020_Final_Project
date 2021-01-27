package by.praded.ask_and_go.dao.sql;

import by.praded.ask_and_go.dao.TagDao;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
 * Tag dao implementation for SQL.
 * @see TagDao
 * @see by.praded.ask_and_go.dao.Dao
 * @see BaseDaoImpl
 * @see Tag
 */
public class TagDaoImpl extends BaseDaoImpl implements TagDao {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(TagDaoImpl.class);

    /**
     * Dao class constructor to create dao only with connection.
     *
     * @param connection - for connecting with database.
     */
    public TagDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method to create tag in database.
     *
     * @param tag - tag to insert.
     * @return generated identity of tag.
     * @throws DaoException - exception may occurs during the insertion.
     */
    @Override
    public Long create(Tag tag) throws DaoException {
        String sql = "INSERT INTO `tag` (`text`) VALUE (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tag.getText());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                throw new DaoException("There is no autoincremented index after trying to add entry into the table `Tag`");
            }
        } catch (SQLException e) {
            return findTagIdByText(tag.getText());
        }
    }

    /**
     * Method to find tag identity by tag text.
     *
     * @param tagContent - tag text to look for.
     * @return identity of the found tag.
     * @throws DaoException - exception may occurs during the reading.
     */
    private Long findTagIdByText(String tagContent) throws DaoException {
        String sql = "SELECT `id` FROM `tag` WHERE `text` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tagContent);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            } else {
                logger.error("Attempt to find non-existent tag.");
                throw new DaoException();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to read tag from database.
     *
     * @param id - identity of the tag to read.
     * @return tag that its found.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public Tag read(Long id) throws DaoException {
        String sql = "SELECT `text` FROM `tag` WHERE `id` = ?";
        Tag tag = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tag = new Tag();
                tag.setId(id);
                tag.setText(resultSet.getString("text"));
            }
            return tag;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method ti update the tag in database.
     *
     * @param entity - tag to update
     * @throws DaoException - exception may occurs during the updating.
     */
    @Override
    public void update(Tag entity) throws DaoException {
        String sql = "UPDATE `tag` SET `text` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getText());
            statement.setLong(2, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }

    }

    /**
     * Method to delete the tag from database.
     *
     * @param id - identity of tag to delete.
     * @throws DaoException - exception may occurs during the deleting.
     */
    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `tag` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to find tag by its text.
     *
     * @param tagText - tag text to search by.
     * @return list of tags found by tag text.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public List<Tag> findByText(String tagText) throws DaoException {
        String sql = "SELECT `id`, `text` FROM `tag` WHERE `text` LIKE ?";
        List<Tag> tags = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + tagText + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tag tag = new Tag();
                tag.setId(resultSet.getLong("id"));
                tag.setText(resultSet.getString("text"));
            }
            return tags;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to find list of tags by question id.
     *
     * @param id - identity of question to search by.
     * @return found list of tags.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public List<Tag> findByQuestionId(Long id) throws DaoException {
        String sql = "SELECT `tag_id` FROM `question_tag` WHERE `question_id` = ?";

        List<Tag> tags = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tag tag = read(resultSet.getLong("tag_id"));
                tags.add(tag);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return tags;
    }
}