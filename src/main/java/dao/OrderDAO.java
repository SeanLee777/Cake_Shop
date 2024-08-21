package dao;

import model.Order;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderDAO {

    private static final Logger logger = LogManager.getLogger(OrderDAO.class);

    // Method to create an order
    public int createOrder(int userID, int productID, int quantity, LocalDateTime orderDate) throws SQLException {
        String query = "INSERT INTO Orders (userID, productID, quantity, orderDate) VALUES (?, ?, ?, ?)";
        int generatedOrderID = -1;

        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userID);
            statement.setInt(2, productID);
            statement.setInt(3, quantity);
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(orderDate));
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedOrderID = generatedKeys.getInt(1);
                        logger.info("Order created successfully with Order ID: {}", generatedOrderID);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error creating order: ", e);
            throw e;
        }
        return generatedOrderID;
    }

    // Method to calculate the total amount of an order
    public double calculateTotalAmount(int orderID) throws SQLException {
        String query = "SELECT SUM(p.price * o.quantity) AS totalAmount " +
                "FROM Orders o " +
                "JOIN Products p ON o.productID = p.productID " +
                "WHERE o.orderID = ?";
        double totalAmount = 0.0;

        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totalAmount = resultSet.getDouble("totalAmount");
                    logger.info("Total amount calculated for Order ID {}: ${}", orderID, totalAmount);
                }
            }
        } catch (SQLException e) {
            logger.error("Error calculating total amount: ", e);
            throw e;
        }
        return totalAmount;
    }

    public List<Order> getOrderHistoryByUserID(int userID) throws SQLException {
        String query = "SELECT * FROM Orders WHERE userID = ?";
        List<Order> orderHistory = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderID(resultSet.getInt("orderID"));
                    order.setOrderDate(resultSet.getTimestamp("orderDate").toLocalDateTime());
                    order.setProductID(resultSet.getInt("productID"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    order.setUserID(resultSet.getInt("userID"));
                    orderHistory.add(order);
                }
                logger.info("Retrieved {} orders for User ID: {}", orderHistory.size(), userID);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving order history: ", e);
            throw e;
        }
        return orderHistory;
    }
}
