package dao;

import config.DataProvider;
import model.FavouritePost;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavouritePostDao {

    private final DataSource dataSource;

    public FavouritePostDao() {
        try {
            this.dataSource = DataProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(FavouritePost favouritePost) {
        String query = "INSERT INTO favourite_post (user_id, post_id) VALUES(?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, favouritePost.getUserId());
            statement.setInt(2, favouritePost.getPostId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()) {
                favouritePost.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FavouritePost> getFavouritePostByUserId(int id) {
        String query = """
                SELECT
                    user_id, post_id
                FROM
                    favourite_post
                WHERE
                    user_id = ?
                """;
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<FavouritePost> posts = new ArrayList<>();
            while (resultSet.next()) {
                FavouritePost favouritePost = new FavouritePost(
                        resultSet.getInt("user_id"),
                        resultSet.getInt("post_id")
                        );
                posts.add(favouritePost);
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}