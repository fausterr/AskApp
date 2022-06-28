package dao;

import config.DataProvider;
import model.Category;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private final DataSource dataSource;

    public CategoryDao() {
        try {
            this.dataSource = DataProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Category> findAll() {
        final String query = "SELECT id, name FROM category";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Category mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Category(id, name);
    }
}
