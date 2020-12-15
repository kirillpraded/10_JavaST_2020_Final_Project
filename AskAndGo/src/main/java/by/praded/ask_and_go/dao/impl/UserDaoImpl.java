package by.praded.ask_and_go.dao.impl;

import by.praded.ask_and_go.dao.UserDao;
import by.praded.ask_and_go.entity.Role;
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
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT `id`, `first_name`, `last_name`, `email`, `role`, `reg_date` FROM `user` " +
                "WHERE `username` = ? AND `password` = ?";
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setRegDate(resultSet.getTimestamp("reg_date").toLocalDateTime());
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(Role.values()[(resultSet.getInt("role"))]);
                user.setProfileImage(resultSet.getString("profile_image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT `id`, `username`, `password`, `first_name`, `last_name`," +
                " `email`, `role`, `reg_date`, `profile_image` " +
                "FROM `user` ORDER BY `username`";

        User user;
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setRegDate(resultSet.getTimestamp("reg_date").toLocalDateTime());
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.values()[(resultSet.getInt("role"))]);
                user.setProfileImage(resultSet.getString("profile_image"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO `user` (`username`, `password`, `first_name`, `last_name`, `email`, `role`)" +
                " VALUE (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getRole().getIdentity());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User read(Long id) {
        String sql = "SELECT `username`, `password`, `first_name`, `last_name`, `email`, `role`, `reg_date`, `profile_image`" +
                " FROM `user` WHERE `id` = ?";
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setRegDate(resultSet.getTimestamp("reg_date").toLocalDateTime());
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("username"));
                user.setRole(Role.values()[(resultSet.getInt("role"))]);
                user.setProfileImage(resultSet.getString("profile_image"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE `user` SET `password` = ?, `first_name` = ?," +
                " `last_name` = ?, `email` = ?, `role` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getRole().getIdentity());
            statement.setLong(6, user.getId());
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
        String sql = "DELETE FROM `user` WHERE `id` = ?";
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
}
