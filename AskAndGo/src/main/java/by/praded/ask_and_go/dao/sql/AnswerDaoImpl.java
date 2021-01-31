package by.praded.ask_and_go.dao.sql;

import by.praded.ask_and_go.dao.AnswerDao;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Answer;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.User;

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
 * Answer dao implementation for SQL.
 * @see AnswerDao
 * @see by.praded.ask_and_go.dao.Dao
 * @see BaseDaoImpl
 * @see Answer
 */
public class AnswerDaoImpl extends BaseDaoImpl implements AnswerDao {
    private static final int AMOUNT_ON_PAGE = 5;

    /**
     * Dao class constructor to create dao only with connection.
     *
     * @param connection - for connecting with database.
     */
    public AnswerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method to create answer in the database.
     *
     * @param answer - answer to insert.
     * @return generated identity of answer.
     * @throws DaoException - exception may occurs during the insertion.
     */
    @Override
    public Long create(Answer answer) throws DaoException {
        String sql = "INSERT INTO `answer` (`text`, `user_id`, `question_id`) VALUE (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, answer.getText());
            statement.setLong(2, answer.getAuthor().getId());
            statement.setLong(3, answer.getQuestion().getId());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                throw new DaoException("There is no autoincremented indices after trying to add entry into the table `answer`");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to read an answer from database.
     *
     * @param id - identity of the answer to read.
     * @return answer that its found.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public Answer read(Long id) throws DaoException {
        String sql = "SELECT `text`, `user_id`, `question_id`, `answer_date`, `is_correct`" +
                " FROM `answer` WHERE `id` = ?";
        Answer answer = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                answer = new Answer();
                answer.setId(id);
                answer.setText(resultSet.getString("text"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                answer.setAuthor(user);
                Question question = new Question();
                question.setId(resultSet.getLong("question_id"));
                answer.setQuestion(question);
                answer.setDate(resultSet.getTimestamp("answer_date").toLocalDateTime());
                answer.setCorrect(resultSet.getBoolean("is_correct"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return answer;
    }

    /**
     * Declaration of method that update an answer in the database.
     *
     * @param answer - answer to update
     * @throws DaoException - exception may occurs during the updating.
     */
    @Override
    public void update(Answer answer) throws DaoException {
        String sql = "UPDATE `answer` SET `text` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, answer.getText());
            statement.setLong(2, answer.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to delete an answer from database.
     *
     * @param id - identity of the answer to delete.
     * @throws DaoException - exception may occurs during the deleting.
     */
    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `answer` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to find list of answers by the question identity.
     *
     * @param questionId - identity of the question
     * @param page - amount of times to offset results.
     * @return list of answers found by the question id.
     * @throws DaoException - may occurs during the reading.
     * @see by.praded.ask_and_go.entity.Question
     */
    @Override
    public List<Answer> findByQuestionId(Long questionId, int page) throws DaoException {
        String sql = "SELECT `id`, `text`, `user_id`, `answer_date`, `is_correct`" +
                " FROM `answer` WHERE `question_id` = ? LIMIT ? OFFSET ?";
        List<Answer> answers = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, questionId);
            statement.setInt(2, AMOUNT_ON_PAGE);
            statement.setInt(3, AMOUNT_ON_PAGE * (page - 1));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getLong("id"));
                answer.setText(resultSet.getString("text"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                answer.setAuthor(user);
                Question question = new Question();
                question.setId(questionId);
                answer.setQuestion(question);
                answer.setDate(resultSet.getTimestamp("answer_date").toLocalDateTime());
                answer.setCorrect(resultSet.getBoolean("is_correct"));
                answers.add(answer);
            }
            return answers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to make answer correct.
     *
     * @param id - identity of the answer to make it correct.
     * @throws DaoException - may occurs during the updating.
     */
    @Override
    public void makeCorrect(Long id) throws DaoException {
        String sql = "UPDATE `answer` SET `is_correct` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 1);

            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
