package by.praded.ask_and_go.dao.impl;

import by.praded.ask_and_go.dao.AnswerDao;
import by.praded.ask_and_go.entity.Answer;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiryl Praded
 * 13.12.2020
 */
public class AnswerDaoImpl extends BaseDaoImpl implements AnswerDao {
    @Override
    public boolean create(Answer answer) {
        String sql = "INSERT INTO `answer` (`text`, `user_id`, `question_id`) VALUE (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, answer.getText());
            statement.setLong(2, answer.getAuthor().getId());
            statement.setLong(3, answer.getQuestion().getId());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Answer read(Long id) {
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
            //TODO LOG EVENT
        }
        return answer;
    }

    @Override
    public boolean update(Answer answer) {
        String sql = "UPDATE `answer` SET `text` = ?, `is_correct` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, answer.getText());
            if (answer.isCorrect()) {
                statement.setInt(2, 1);
            } else {
                statement.setInt(2, 0);
            }
            statement.setLong(3, answer.getId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            // TODO could be thrown to notify controller that update isn't completed o throw new PersistentException(e);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM `answer` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            // TODO could be thrown to notify controller that update isn't completed o throw new PersistentException(e);
        }
        return false;
    }

    @Override
    public List<Answer> findAll() {
        String sql = "SELECT * FROM `answer`";
        List<Answer> answers = new ArrayList<>();
        Answer answer;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answer = new Answer();
                answer.setId(resultSet.getLong("id"));
                answer.setText(resultSet.getString("text"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                answer.setAuthor(user);
                Question question = new Question();
                question.setId(resultSet.getLong("question_id"));
                answer.setQuestion(question);
                answer.setDate(resultSet.getTimestamp("answer_date").toLocalDateTime());
                answer.setCorrect(resultSet.getBoolean("is_correct"));
                answers.add(answer);
            }
        } catch (SQLException e) {
            //TODO LOG
            e.printStackTrace();
        }
        return answers;
    }

    @Override
    public List<Answer> findByQuestionId(Long questionId) {
        String sql = "SELECT `id`, `text`, `user_id`, `answer_date`, `is_correct`" +
                " FROM `answer` WHERE `question_id` = ?";
        List<Answer> answers = new ArrayList<>();
        Answer answer;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                answer = new Answer();
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
        } catch (SQLException e) {
            //TODO LOG
            e.printStackTrace();
        }
        return answers;
    }
}
