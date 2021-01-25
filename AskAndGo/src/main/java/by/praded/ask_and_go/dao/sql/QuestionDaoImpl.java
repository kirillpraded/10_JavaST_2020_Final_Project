package by.praded.ask_and_go.dao.sql;

import by.praded.ask_and_go.dao.QuestionDao;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.Tag;
import by.praded.ask_and_go.entity.User;
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
 * Question dao implementation for SQL.
 * @see QuestionDao
 * @see by.praded.ask_and_go.dao.Dao
 * @see BaseDaoImpl
 * @see Question
 */
public class QuestionDaoImpl extends BaseDaoImpl implements QuestionDao {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(QuestionDaoImpl.class);

    /**
     * Dao class constructor to create dao only with connection.
     *
     * @param connection - for connecting with database.
     */
    public QuestionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method to create question in database.
     *
     * @param question - question to insert.
     * @return generated identity of question.
     * @throws DaoException - exception may occurs during the insertion.
     */
    @Override
    public Long create(Question question) throws DaoException {
        String sql = "INSERT INTO `question` (`title`, `text`, `category_id`, `user_id`, `question_image`)" +
                " VALUE (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, question.getTitle());
            statement.setString(2, question.getText());
            statement.setLong(3, question.getCategory().getId());
            statement.setLong(4, question.getAuthor().getId());
            statement.setString(5, question.getImageName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                Long id = resultSet.getLong(1);
                question.setId(id);
                addTags(question);
                return id;
            } else {
                throw new DaoException("There is no autoincremented index after trying to add entry into the table `question`");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to read question from database.
     *
     * @param id - identity of the question to read.
     * @return question that its found.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public Question read(Long id) throws DaoException {
        String sql = "SELECT `title`, `text`, `category_id`, `user_id`, `question_image`," +
                " `ask_date`, `is_closed`, `contains_correct_answer` " +
                " FROM `question` WHERE `id` = ?";
        Question question = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                question = new Question();
                question.setId(id);
                question.setTitle(resultSet.getString("title"));
                question.setText(resultSet.getString("text"));
                Category category = new Category();
                category.setId(resultSet.getLong("category_id"));
                question.setCategory(category);
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                question.setAuthor(user);
                question.setImageName(resultSet.getString("question_image"));
                question.setDate(resultSet.getTimestamp("ask_date").toLocalDateTime());
                question.setClosed(resultSet.getBoolean("is_closed"));
                question.setContainsCorrectAnswer(resultSet.getBoolean("contains_correct_answer"));
            }
            return question;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    /**
     * Method to update question in database.
     *
     * @param question - question to update
     * @throws DaoException - exception may occurs during the updating.
     */
    @Override
    public void update(Question question) throws DaoException {
        String sql = "UPDATE `question` SET `title` = ?, `text` = ?" +
                " WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, question.getTitle());
            statement.setString(2, question.getText());
            statement.setLong(3, question.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    /**
     * Method to delete question from database.
     *
     * @param id - identity of question to delete.
     * @throws DaoException - exception may occurs during the deleting.
     */
    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `question` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    /**
     * Method to insert tag and question identities into common table.
     *
     * @param question - question where to tage tags.
     */
    private void addTags(Question question) {
        String sql = "INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUE (?, ?)";
        for (Tag tag : question.getTags()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, question.getId());
                statement.setLong(2, tag.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error(e.getMessage(), "Trying to add an existing tag.");
            }
        }
    }

    /**
     * Method to find questions by identity of category.
     *
     * @param categoryId - identity of category to search by.
     * @return list of questions in current category.
     * @throws DaoException - exception may occurs during the reading.
     * @see by.praded.ask_and_go.entity.Category
     */
    @Override
    public List<Question> findByCategoryId(Long categoryId) throws DaoException {
        String sql = "SELECT `id`, `title`, `text`, `user_id`, `ask_date`, `is_closed`, `contains_correct_answer` FROM `question` WHERE `category_id` = ?";
        List<Question> questions = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getLong("id"));
                question.setTitle(resultSet.getString("title"));
                question.setText(resultSet.getString("text"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));

                question.setAuthor(user);
                question.setDate(resultSet.getTimestamp("ask_date").toLocalDateTime());
                question.setClosed(resultSet.getBoolean("is_closed"));
                question.setContainsCorrectAnswer(resultSet.getBoolean("contains_correct_answer"));

                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to find questions by title.
     *
     * @param title - title to search by.
     * @return list of questions found by given title.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public List<Question> findByTitle(String title) throws DaoException {
        String sql = "SELECT `id`, `title`, `text`, `user_id`, `ask_date`,`category_id`, `is_closed`,"
                + " `contains_correct_answer` FROM `question` WHERE `title` LIKE ?";
        List<Question> questions = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + title + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getLong("id"));
                question.setTitle(resultSet.getString("title"));
                question.setText(resultSet.getString("text"));

                User user = new User();
                user.setId(resultSet.getLong("user_id"));

                question.setAuthor(user);
                question.setDate(resultSet.getTimestamp("ask_date").toLocalDateTime());
                question.setClosed(resultSet.getBoolean("is_closed"));
                question.setContainsCorrectAnswer(resultSet.getBoolean("contains_correct_answer"));

                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to find questions by its tag identity.
     *
     * @param tagId - identity of the tag to search by.
     * @return list of questions that matches given tag.
     * @throws DaoException - exception may occurs during the reading.
     * @see by.praded.ask_and_go.entity.Tag
     */
    @Override
    public List<Question> findByTagId(Long tagId) throws DaoException {
        String sql = "SELECT `title`, `text`, `user_id`, `ask_date`,`category_id`, `is_closed`,"
                + " `contains_correct_answer` FROM `question` WHERE `id` = ?";
        String commonTableSql = "SELECT `question_id` FROM `question_tag` WHERE `tag_id` = ?";

        List<Question> questions = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(commonTableSql)) {
            statement.setLong(1, tagId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getLong("id"));

                try (PreparedStatement questionStatement = connection.prepareStatement(sql)) {
                    questionStatement.setLong(1, question.getId());

                    ResultSet query = questionStatement.executeQuery();

                    question.setTitle(query.getString("title"));
                    question.setText(query.getString("text"));

                    User user = new User();
                    user.setId(query.getLong("user_id"));

                    question.setAuthor(user);
                    question.setDate(query.getTimestamp("ask_date").toLocalDateTime());
                    question.setClosed(query.getBoolean("is_closed"));
                    question.setContainsCorrectAnswer(query.getBoolean("contains_correct_answer"));

                    questions.add(question);
                }

            }
            return questions;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    /**
     * Method to close question.
     *
     * @param id - question identity to close.
     * @throws DaoException - exception may occurs during the updating.
     */
    @Override
    public void closeQuestion(Long id) throws DaoException {
        String sql = "UPDATE `question` SET `is_closed` = ?" +
                " WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 1);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    /**
     * Method to mark that it contains correct answer.
     *
     * @param id - identity of the question to mark.
     * @throws DaoException - exception may occurs during the updating.
     * @see by.praded.ask_and_go.entity.Answer
     */
    @Override
    public void makeContainsCorrect(Long id) throws DaoException {
        String sql = "UPDATE `question` SET `contains_correct_answer` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 1);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
