package dao;

import config.DataProvider;
import model.Post;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDao {

    private final DataSource dataSource;

    public PostDao() {
        try {
            this.dataSource = DataProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Post post) {
        final String query = "INSERT INTO " +
                "               post (title, contents, date_added, category_id, user_id)" +
                "             VALUES (?,?,?,?,?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContents());
            statement.setObject(3, post.getDateAdded());
            statement.setInt(4,post.getCategoryId());
            statement.setInt(5, post.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                post.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> findAll() {
        final String query = """
                SELECT
                    id, title, contents, date_added, category_id, user_id
                FROM
                    post p
                """;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Post> allPosts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = mapRow(resultSet);
                allPosts.add(post);
            }
            return allPosts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> findByPostId(int postId) {
        final String query = """
                SELECT
                    id, title, contents, date_added, category_id, user_id
                FROM
                    post
                WHERE
                    id = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();
            List<Post> singlePostList = new ArrayList<>();
            while (resultSet.next()) {
                Post post = mapRow(resultSet);
                singlePostList.add(post);
            }
            return singlePostList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Post> findByUserId(int userId) {
        final String query = """
                SELECT
                    id, title, contents, date_added, category_id, user_id
                FROM
                    post
                WHERE
                    user_id = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = mapRow(resultSet);
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Post> findByCategoryId(int categoryId) {
        final String query = """
                SELECT 
                    id, title, contents, date_added, category_id, user_id
                FROM
                    post
                WHERE
                    category_id = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = mapRow(resultSet);
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Post mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String contents = resultSet.getString("contents");
        LocalDateTime dateAdded = resultSet.getTimestamp("date_added").toLocalDateTime();
        int categoryId = resultSet.getInt("category_id");
        int userId = resultSet.getInt("user_id");
        return new Post(id, title, contents, dateAdded, categoryId, userId);
    }
}
