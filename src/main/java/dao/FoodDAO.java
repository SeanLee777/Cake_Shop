package dao;

import dto.FoodDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cakeshop";
        String user = "root";
        String password = "2023ck@*";
        return DriverManager.getConnection(url, user, password);
    }

    public List<FoodDTO> getSurplusFoodList() throws SQLException {
        List<FoodDTO> foodList = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE quantity > 0";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FoodDTO food = new FoodDTO();
                food.setId(resultSet.getInt("productID"));  // 修改字段名
                food.setName(resultSet.getString("name"));
                food.setQuantity(resultSet.getInt("quantity"));
                food.setExpirationDate(resultSet.getDate("expirationDate").toString());  // 修改字段名
                food.setPrice(resultSet.getDouble("price"));
                foodList.add(food);
            }
        }
        return foodList;
    }
}


