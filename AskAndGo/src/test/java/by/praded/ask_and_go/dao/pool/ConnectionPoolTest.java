package by.praded.ask_and_go.dao.pool;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Kiryl Praded
 * 25.01.2021
 */
public class ConnectionPoolTest {

    @BeforeSuite
    public void initPoolTest() throws ConnectionPoolException {
        ConnectionPool pool = ConnectionPool.getInstance();
        ResourceBundle bundle = ResourceBundle.getBundle("test", Locale.getDefault());
        pool.init(bundle.getString("test.database.driver"),
                bundle.getString("test.database.url"),
                bundle.getString("test.database.user"),
                bundle.getString("test.database.password"),
                Integer.parseInt(bundle.getString("test.database.poolSize")));
        List<String> deleteCommands = new ArrayList<>();
        deleteCommands.add("DELETE FROM `question_tag`;");
        deleteCommands.add("DELETE FROM `tag`;");
        deleteCommands.add("DELETE FROM `answer`;");
        deleteCommands.add("DELETE FROM `question`;");
        deleteCommands.add("DELETE FROM `category`;");
        deleteCommands.add("DELETE FROM `user`;");


        try (Connection con = pool.takeConnection()) {
            for (String command : deleteCommands) {
                PreparedStatement statement = con.prepareStatement(command);
                statement.executeUpdate();
                statement.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<String> fillValues = new ArrayList<>();


        fillValues.add("INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES (1, 'testUser1', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', 1);");
        fillValues.add("INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES (2, 'testUser2', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', 1);");
        fillValues.add("INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES (3, 'testUser3', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', 1);");
        fillValues.add("INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `email`, `role`) VALUES (4, 'testUser4', '$2a$10$6RNnktdw45mWm.C654qzI.Oq9UtLZQvmfbCn6rSFgt8Yp17lctlGu', 'kirill', 'praded', 'kirillpraded@bk.ru', 1);");
        fillValues.add("INSERT INTO `category` (`id`, `name`) VALUES (1, 'Java');");
        fillValues.add("INSERT INTO `category` (`id`, `parent_id`, `name`) VALUES (2, 1, 'Collections');");
        fillValues.add("INSERT INTO `category` (`id`, `parent_id`, `name`) VALUES (3, 1, 'Multithreading');");
        fillValues.add("INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES (1, 'Collections', 'How many collections in java?', 2, 1, 0, 0);");
        fillValues.add("INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES (3, 'Threads', 'How to create thread?', 3, 3, 0, 0);");
        fillValues.add("INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES (2, 'List', 'How many realizations of List in java?', 2, 2, 0, 0);");
        fillValues.add("INSERT INTO `tag` (`id`, `text`) VALUES (1, 'collections');");
        fillValues.add("INSERT INTO `tag` (`id`, `text`) VALUES (2, 'list');");
        fillValues.add("INSERT INTO `tag` (`id`, `text`) VALUES (3, 'threads');");
        fillValues.add("INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (1, 1);");
        fillValues.add("INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (1, 2);");
        fillValues.add("INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (2, 1);");
        fillValues.add("INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (2, 2);");
        fillValues.add("INSERT INTO `question_tag` (`question_id`, `tag_id`) VALUES (3, 3);");
        fillValues.add("INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES (1, 'there are about 23 realizations', 2, 1, 0);");
        fillValues.add("INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES (2, 'about 8 realizations of set', 3, 2, 0);");
        fillValues.add("INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES (3, 'there are few ways, using Thread and Runnable', 1, 3, 0);");
        fillValues.add("INSERT INTO `category` (`id`, `name`) VALUES (4, 'ToDelete');");
        fillValues.add("INSERT INTO `category` (`id`, `name`) VALUES (5, 'ToEdit');");
        fillValues.add("INSERT INTO `question` (`id`, `title`, `text`, `category_id`, `user_id`, `is_closed`, `contains_correct_answer`) VALUES (4, 'Threads', 'How to create thread?', 3, 3, 0, 0);");
        fillValues.add("INSERT INTO `answer` (`id`, `text`, `user_id`, `question_id`, `is_correct`) VALUES (4, 'there are few ways, using Thread and Runnable', 1, 3, 0);");

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
