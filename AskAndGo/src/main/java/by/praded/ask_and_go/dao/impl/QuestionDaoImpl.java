package by.praded.ask_and_go.dao.impl;

import by.praded.ask_and_go.dao.QuestionDao;
import by.praded.ask_and_go.entity.Category;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.Tag;
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
public class QuestionDaoImpl extends BaseDaoImpl implements QuestionDao {
    @Override
    public boolean create(Question question) {
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
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Question read(Long id) {
        String sql = "SELECT `title`, `text`, `category_id`, `user_id`, `question_image`," +
                " `ask_date`, `is_closed`, `contains_correct_answer` " +
                " FROM `question` WHERE `id` = ?";
        String sqlForTags = "SELECT `tag_id` FROM `question_tag` WHERE `question_id` = ?";

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
                List<Tag> tags = new ArrayList<>();
                question.setTags(tags);

                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlForTags)) {
                    preparedStatement.setLong(1, id);
                    ResultSet tagsResultSet = preparedStatement.executeQuery();
                    while (tagsResultSet.next()) {
                        Tag tag = new Tag();
                        tag.setId(tagsResultSet.getLong("tag_id"));
                        tags.add(tag);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }

    @Override
    public boolean update(Question question) {
        String sql = "UPDATE `question` SET `title` = ?, `text` = ?, `category_id` = ?," +
                " `question_image` = ?, `is_closed` = ?, `contains_correct_answer` = ?" +
                " WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, question.getTitle());
            statement.setString(2, question.getText());
            statement.setLong(3, question.getCategory().getId());
            statement.setString(4, question.getImageName());
            if (question.isClosed()) {
                statement.setInt(5, 1);
            } else {
                statement.setInt(5, 0);
            }
            if (question.isContainsCorrectAnswer()) {
                statement.setInt(6, 1);
            } else {
                statement.setInt(6, 0);
            }

            statement.setLong(7, question.getId());
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
        String sql = "DELETE FROM `question` WHERE `id` = ?";
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
    public List<Question> findAll() {
        String sql = "SELECT * FROM `question`";
        String sqlForTags = "SELECT `tag_id` FROM `question_tag` WHERE `question_id` = ?";
        List<Question> questions = new ArrayList<>();
        Question question;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                question = new Question();
                long id = resultSet.getLong("id");
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
                List<Tag> tags = new ArrayList<>();
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlForTags)) {
                    preparedStatement.setLong(1, id);
                    ResultSet tagsResultSet = preparedStatement.executeQuery();
                    while (tagsResultSet.next()) {
                        Tag tag = new Tag();
                        tag.setId(tagsResultSet.getLong("tag_id"));
                        tags.add(tag);
                    }
                }
                question.setTags(tags);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
