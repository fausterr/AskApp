package dao;

import config.DataProvider;
import model.Comment;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

    private final DataSource dataSource;

    public CommentDao() {
        try {
            this.dataSource = DataProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findAll() {
        final String query = "SELECT id, user_id, post_id, comment_text, date_added FROM comment c";

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Comment> comments = new ArrayList<>();
            while (resultSet.next()) {
                Integer userId = resultSet.getInt("user_id");
                Integer postId = resultSet.getInt("post_id");
                String text = resultSet.getString("comment_text");
                LocalDateTime dateAdded = resultSet.getTimestamp("date_added").toLocalDateTime();
                comments.add(new Comment(userId, postId, text, dateAdded));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Comment comment) {
        final String query = """
                INSERT INTO
                    comment (user_id, post_id, comment_text, date_added)
                VALUES
                    (?, ?, ?, ?)
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, comment.getUserId());
            statement.setInt(2, comment.getPostId());
            statement.setString(3, comment.getCommentText());
            statement.setObject(4, comment.getDateAdded());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                comment.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}