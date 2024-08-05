package dao;

import dto.FoodIngredientsDTO;
import java.sql.*;

public class FoodIngredientsDAO {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cakeshop";
        String user = "root";
        String password = "2023ck@*";
        return DriverManager.getConnection(url, user, password);
    }

    public void updateFoodIngredients(FoodIngredientsDTO foodIngredientsDTO) throws SQLException {
        String query = "UPDATE Products SET name = ?, quantity = ?, price = ?, expirationDate = ? WHERE productID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, foodIngredientsDTO.getName());
            statement.setInt(2, foodIngredientsDTO.getQuantity());
            statement.setDouble(3, foodIngredientsDTO.getPrice());
            statement.setDate(4, Date.valueOf(foodIngredientsDTO.getExpirationDate()));  // 修改字段名
            statement.setInt(5, foodIngredientsDTO.getId());

            statement.executeUpdate();
        }
    }
}

