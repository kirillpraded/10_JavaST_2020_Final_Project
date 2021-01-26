package by.praded.ask_and_go.dao.pool;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiryl Praded
 * 25.01.2021
 */
public class ConnectionPoolTest {

    @BeforeSuite
    public void initPoolTest() throws ConnectionPoolException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/askandgo_db_test?useSSL=false&serverTimezone=UTC",
                "root",
                "root",
                10);
        String removeAllFromQT = "DELETE FROM `question_tag`;";
        String removeAllFromTag = "DELETE FROM `tag`;";
        String removeAllFromAnswer = "DELETE FROM `answer`;";
        String removeAllFromQuestion = "DELETE FROM `question`;";
        String removeAllFromCategory = "DELETE FROM `category`;";
        String removeAllFromUser = "DELETE FROM `user`;";

        try (Connection con = pool.takeConnection();
             PreparedStatement firstStatement = con.prepareStatement(removeAllFromQT);
             PreparedStatement secondStatement = con.prepareStatement(removeAllFromTag);
             PreparedStatement thirdStatement = con.prepareStatement(removeAllFromAnswer);
             PreparedStatement fourthStatement = con.prepareStatement(removeAllFromQuestion);
             PreparedStatement fifthStatement = con.prepareStatement(removeAllFromCategory);
             PreparedStatement sixthStatement = con.prepareStatement(removeAllFromUser)) {
            firstStatement.executeUpdate();
            secondStatement.executeUpdate();
            thirdStatement.executeUpdate();
            fourthStatement.executeUpdate();
            fifthStatement.executeUpdate();
            sixthStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<String> fillValues = new ArrayList<>();

        String firstUserValue = "INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES (1, 'testUser1', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', 1);";
        String secondUserValue = "INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES (2, 'testUser2', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', 1);";
        String thirdUserValue = "INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES (3, 'testUser3', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', 1);";
        String fourthUserValue = "INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES (4, 'testUser4', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', 1);";
        String firstCategoryValue = "INSERT INTO `category` (`id`, `name`) VALUES (1, 'Java');";
        String fourthCategoryValue = "INSERT INTO `category` (`id`, `name`) VALUES (4, 'ToDelete');";
        String oneMoreValue = "INSERT INTO `category` (`id`, `name`) VALUES (5, 'ToEdit');";
        String secondCategoryValue = "INSERT INTO `category` (`id`, `parent_id`, `name`) VALUES (2, 1, 'Collections');";
        String thirdCategoryValue = "INSERT INTO `category` (`id`, `parent_id`, `name`) VALUES (3, 1, 'Multithreading');";
        String firstQuestion = "INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES (1, 'Collections', 'How many collections in java?', 2, 1, 0, 0);";
        String secondQuestion = "INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES (2, 'List', 'How many realizations of List in java?', 2, 2, 0, 0);";
        String thirdQuestion = "INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES (3, 'Threads', 'How to create thread?', 3, 3, 0, 0);";
        String fourthQuestion = "INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES (4, 'Threads', 'How to create thread?', 3, 3, 0, 0);";
        String firstTagValue = "INSERT INTO `tag` (`id`, `text`) VALUES (1, 'collections');";
        String secondTagValue = "INSERT INTO `tag` (`id`, `text`) VALUES (2, 'list');";
        String thirdTagValue = "INSERT INTO `tag` (`id`, `text`) VALUES (3, 'threads');";
        String firstQT = "INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (1, 1);";
        String secondQT = "INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (1, 2);";
        String thirdQT = "INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (2, 1);";
        String fourthQT = "INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (2, 2);";
        String fifthQT = "INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (3, 3);";
        String firstAnswer = "INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES (1, 'there are about 23 realizations', 2, 1, 0);";
        String secondAnswer = "INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES (2, 'about 8 realizations of set', 3, 2, 0);";
        String thirdAnswer = "INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES (3, 'there are few ways, using Thread and Runnable', 1, 3, 0);";
        String fourthAnswer = "INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES (4, 'there are few ways, using Thread and Runnable', 1, 3, 0);";
        fillValues.add(firstUserValue);
        fillValues.add(secondUserValue);
        fillValues.add(thirdUserValue);
        fillValues.add(fourthUserValue);
        fillValues.add(firstCategoryValue);
        fillValues.add(secondCategoryValue);
        fillValues.add(thirdCategoryValue);
        fillValues.add(firstQuestion);
        fillValues.add(thirdQuestion);
        fillValues.add(secondQuestion);
        fillValues.add(firstTagValue);
        fillValues.add(secondTagValue);
        fillValues.add(thirdTagValue);
        fillValues.add(firstQT);
        fillValues.add(secondQT);
        fillValues.add(thirdQT);
        fillValues.add(fourthQT);
        fillValues.add(fifthQT);
        fillValues.add(firstAnswer);
        fillValues.add(secondAnswer);
        fillValues.add(thirdAnswer);
        fillValues.add(fourthCategoryValue);
        fillValues.add(oneMoreValue);
        fillValues.add(fourthQuestion);
        fillValues.add(fourthAnswer);

        try (Connection connection = pool.takeConnection()) {
            for (String sql : fillValues) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @AfterSuite
    public void destroyTestPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().destroyPool();
    }
}
