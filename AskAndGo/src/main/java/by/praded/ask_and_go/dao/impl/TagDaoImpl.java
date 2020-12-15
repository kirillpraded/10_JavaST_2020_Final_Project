package by.praded.ask_and_go.dao.impl;

import by.praded.ask_and_go.dao.TagDao;
import by.praded.ask_and_go.entity.Question;
import by.praded.ask_and_go.entity.Tag;

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
public class TagDaoImpl extends BaseDaoImpl implements TagDao {
    @Override
    public boolean create(Tag tag) {
        String sql = "INSERT INTO `tag` (`text`) VALUE (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tag.getText());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO LOG
        }
        return false;
    }

    @Override
    public Tag read(Long id) {
        String sql = "SELECT `text` FROM `tag` WHERE `id` = ?";
        String sqlForQuestions = "SELECT `question_id` FROM `question_tag` WHERE `tag_id` = ?";
        Tag tag = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                tag = new Tag();
                tag.setId(id);
                tag.setText(resultSet.getString("text"));
                List<Question> questions = new ArrayList<>();
                tag.setQuestions(questions);
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlForQuestions)) {
                    preparedStatement.setLong(1, id);
                    ResultSet questionsResultSet = preparedStatement.executeQuery();
                    while (questionsResultSet.next()) {
                        Question question = new Question();
                        question.setId(resultSet.getLong("question_id"));
                        questions.add(question);
                    }
                }
            }

        } catch (SQLException e) {
            //TODO log
        }
        return tag;
    }

    @Override
    public boolean update(Tag entity) {
        String sql = "UPDATE `tag` SET `text` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getText());
            statement.setLong(2, entity.getId());
            if (statement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException | NullPointerException e) {
            //todo log
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM `tag` WHERE `id` = ?";
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
    public List<Tag> findAll() {
        String sql = "SELECT * FROM `tag`";
        String sqlForQuestions = "SELECT `question_id` FROM `question_tag` WHERE `tag_id` = ?";
        List<Tag> tags = new ArrayList<>();
        Tag tag;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tag = new Tag();
                tag.setId(resultSet.getLong("id"));
                tag.setText(resultSet.getString("text"));
                List<Question> questions = new ArrayList<>();
                tag.setQuestions(questions);
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlForQuestions)) {
                    preparedStatement.setLong(1, tag.getId());
                    ResultSet questionsResultSet = preparedStatement.executeQuery();
                    while (questionsResultSet.next()) {
                        Question question = new Question();
                        question.setId(resultSet.getLong("question_id"));
                        questions.add(question);
                    }
                }
                tags.add(tag);
            }

        } catch (SQLException e) {
            //TODO log
        }
        return tags;
    }
}
