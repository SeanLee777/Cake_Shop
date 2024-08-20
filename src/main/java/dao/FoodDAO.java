/*package dao;

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
}*/
/*package dao;
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
        String query = "SELECT * FROM FoodIngredients WHERE quantity > 0";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FoodDTO food = new FoodDTO();
                food.setId(resultSet.getInt("id"));
                food.setName(resultSet.getString("name"));
                food.setQuantity(resultSet.getInt("quantity"));
                food.setExpirationDate(resultSet.getDate("expiration_date").toString());
                food.setPrice(resultSet.getDouble("price"));
                foodList.add(food);
            }
        }
        return foodList;
    }
}
*/

package dao;

import dto.FoodDTO;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FoodDAO {
    private static final Logger LOGGER = Logger.getLogger(FoodDAO.class.getName());

    // Get database connection
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cakeshop";
        String user = "root";
        String password = "2023ck@*";
        return DriverManager.getConnection(url, user, password);
    }

    // Get list of surplus food items
    public List<FoodDTO> getSurplusFoodList() {
        List<FoodDTO> foodList = new ArrayList<>();
        String query = "SELECT * FROM InventoryItems WHERE quantity > 0";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                FoodDTO food = new FoodDTO();
                food.setId(resultSet.getInt("inventoryItemID"));
                food.setInventoryId(resultSet.getInt("inventoryID"));
                food.setProductId(resultSet.getInt("productID"));
                food.setName(resultSet.getString("name"));
                food.setQuantity(resultSet.getInt("quantity"));
                food.setUnit(resultSet.getString("unit"));
                food.setPrice(resultSet.getDouble("price"));
                foodList.add(food);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching surplus food list", e);
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, "Null pointer exception", e);
        }

        return foodList;
    }

    // Get food ingredient by ID
    public FoodDTO getFoodIngredientById(int ingredientId) {
        FoodDTO foodIngredient = null;
        String query = "SELECT * FROM InventoryItems WHERE inventoryItemID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ingredientId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    foodIngredient = new FoodDTO();
                    foodIngredient.setId(resultSet.getInt("inventoryItemID"));
                    foodIngredient.setInventoryId(resultSet.getInt("inventoryID"));
                    foodIngredient.setProductId(resultSet.getInt("productID"));
                    foodIngredient.setName(resultSet.getString("name"));
                    foodIngredient.setQuantity(resultSet.getInt("quantity"));
                    foodIngredient.setUnit(resultSet.getString("unit"));
                    foodIngredient.setPrice(resultSet.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching food ingredient by ID", e);
        }

        return foodIngredient;
    }

    // Update food ingredient information
    public void updateFoodIngredient(FoodDTO foodIngredient) {
        String query = "UPDATE InventoryItems SET name = ?, quantity = ?, unit = ?, price = ? WHERE inventoryItemID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, foodIngredient.getName());
            statement.setInt(2, foodIngredient.getQuantity());
            statement.setString(3, foodIngredient.getUnit());
            statement.setDouble(4, foodIngredient.getPrice());
            statement.setInt(5, foodIngredient.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating food ingredient information", e);
        }
    }

    // Add new food ingredient
    public void addFood(FoodDTO foodIngredient) {
        String query = "INSERT INTO InventoryItems (inventoryID, productID, name, quantity, unit, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, foodIngredient.getInventoryId());
            statement.setInt(2, foodIngredient.getProductId());
            statement.setString(3, foodIngredient.getName());
            statement.setInt(4, foodIngredient.getQuantity());
            statement.setString(5, foodIngredient.getUnit());
            statement.setDouble(6, foodIngredient.getPrice());

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding food ingredient", e);
        }
    }
}
