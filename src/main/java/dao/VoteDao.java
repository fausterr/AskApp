package dao;

import config.DataProvider;
import model.Vote;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteDao {
    private final DataSource dataSource;

    public VoteDao() {
        try {
            this.dataSource = DataProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Vote vote) {
        final String query = """
                INSERT INTO
                    vote (user_id, post_id, type, date_added)
                VALUES
                    (?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    type = ?
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vote.getUserId());
            statement.setInt(2, vote.getPostId());
            statement.setString(3, vote.getType().toString());
            statement.setObject(4, vote.getDateAdded());
            statement.setString(5, vote.getType().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countByPostId(int postId) {
        final String query = """
                SELECT
                    (SELECT COUNT(post_id) FROM vote WHERE post_id = ? AND type = 'UP')
                    -
                    (SELECT COUNT(post_id) FROM vote WHERE post_id = ? AND type = 'DOWN')
                    AS
                    vote_count;
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, postId);
            statement.setInt(2, postId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}