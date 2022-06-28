package dao;

import config.DataProvider;
import model.User;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private final DataSource dataSource;

    public UserDao() {
        try {
            this.dataSource = DataProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(User user) {
        saveUser(user);
        saveUserRole(user);
    }

    private void saveUser(User user) {
        final String query = """
                    INSERT INTO 
                        user (username, email, password, firstname, lastname, post_counter, registration_date) 
                    VALUES 
                        (?,?,?,?,?,?,?)
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstname());
            statement.setString(5, user.getLastName());
            statement.setInt(6, 0);
            statement.setObject(7, user.getRegistrationDate());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAllByPostCounter() {
        final String query = """
                SELECT 
                    username, post_counter
                FROM
                    user
                ORDER BY
                    post_counter 
                DESC 
                LIMIT 5
                """;
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("username"),
                        resultSet.getInt("post_counter")
                );
                users.add(user);
            }
            return  users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveUserRole(User user) {
        final String query = """
                INSERT INTO 
                    user_role (username) 
                VALUES 
                    (?)
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(int id) {
        final String query = """
                SELECT
                    id, username, email, password, firstname, lastname, post_counter, registration_date
                FROM
                    user
                WHERE
                    id = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return Optional.of(mapRow(resultSet));
            else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByUsername(String username) {
        final String query = """
                SELECT
                    id, username, email, password, firstname, lastname, post_counter, registration_date
                FROM
                    user
                WHERE
                    username = ?
                """;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRow(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserPostCounter(String username) {
        final String query = """
                UPDATE 
                    user 
                SET 
                    post_counter = post_counter + 1
                WHERE
                    username = ?                   
                """;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        int postCounter = resultSet.getInt("post_counter");
        LocalDateTime registrationDate = resultSet.getObject("registration_date", LocalDateTime.class);
        return new User(id, username, email, password, firstname, lastname, postCounter, registrationDate);
    }
}
