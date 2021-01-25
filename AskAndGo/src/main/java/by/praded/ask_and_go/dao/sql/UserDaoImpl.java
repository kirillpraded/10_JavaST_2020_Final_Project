package by.praded.ask_and_go.dao.sql;

import by.praded.ask_and_go.dao.UserDao;
import by.praded.ask_and_go.dao.exception.DaoException;
import by.praded.ask_and_go.entity.Role;
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
 * User dao implementation for SQL.
 * @see UserDao
 * @see by.praded.ask_and_go.dao.Dao
 * @see BaseDaoImpl
 * @see User
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    /**
     * Dao class constructor to create dao only with connection.
     *
     * @param connection - for connecting with database.
     */
    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method to find user by username and password.
     *
     * @param username - username
     * @param password - user's password
     * @return user, found by username and password.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) throws DaoException {
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
            throw new DaoException(e);
        }
        return user;
    }

    /**
     * Method to find user by username.
     *
     * @param username - username to search by.
     * @return user. found by username.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public User findByUsername(String username) throws DaoException {
        String sql = "SELECT `id`,`username`, `password`, `first_name`, `last_name`, `email`, `role`, `reg_date`, `profile_image`" +
                " FROM `user` WHERE `username` = ?";
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
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
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    /**
     * Method to find all users.
     *
     * @return list of all users.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public List<User> findAll() throws DaoException {
        String sql = "SELECT `id`, `username`, `password`, `first_name`, `last_name`," +
                " `email`, `role`, `reg_date`, `profile_image` " +
                "FROM `user` ORDER BY `username`";

        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
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
            throw new DaoException(e);
        }

        return users;
    }

    /**
     * Method to update user role.
     *
     * @param user - user to update the role.
     * @throws DaoException - exception may occurs during the updating.
     */
    @Override
    public void updateRole(User user) throws DaoException {
        String sql = "UPDATE `user` SET `role` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getRole().getIdentity());
            statement.setLong(2, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to update user password.
     *
     * @param user - user to update the password.
     * @throws DaoException - exception may occurs during the updating.
     */
    @Override
    public void updatePassword(User user) throws DaoException {
        String sql = "UPDATE `user` SET `password` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPassword());
            statement.setLong(2, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to update user profile image.
     *
     * @param user - user to update profile image.
     * @throws DaoException - exception may occurs during the updating.
     */
    @Override
    public void updateProfileImage(User user) throws DaoException {
        String sql = "UPDATE `user` SET `profile_image` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getProfileImage());
            statement.setLong(2, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method to create user in database.
     *
     * @param user - user to insert.
     * @return generated identity of user.
     * @throws DaoException - exception may occurs during the insertion.
     */
    @Override
    public Long create(User user) throws DaoException {
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
            if (resultSet.next()) {
                return resultSet.getLong(1);
            } else {
                throw new DaoException("There is no autoincremented index after trying to add entry into the table `user`");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Method that read user from database.
     *
     * @param id - identity of the user to read.
     * @return user that its found.
     * @throws DaoException - exception may occurs during the reading.
     */
    @Override
    public User read(Long id) throws DaoException {
        String sql = "SELECT `username`, `first_name`, `last_name`, `email`, `role`, `reg_date`, `profile_image`" +
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
                user.setRole(Role.values()[(resultSet.getInt("role"))]);
                user.setProfileImage(resultSet.getString("profile_image"));
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

    /**
     * Method to update user in the database.
     *
     * @param user - user to update
     * @throws DaoException - exception may occurs during the updating.
     */
    @Override
    public void update(User user) throws DaoException {
        String sql = "UPDATE `user` SET `first_name` = ?," +
                " `last_name` = ?, `email` = ? WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setLong(4, user.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    /**
     * Method to delete user from database.
     *
     * @param id - identity of user to delete.
     * @throws DaoException - exception may occurs during the deleting.
     */
    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `user` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
