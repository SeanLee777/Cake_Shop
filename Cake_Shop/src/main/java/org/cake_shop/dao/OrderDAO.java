package org.cake_shop.dao;


import org.cake_shop.dto.OrderDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cakeshop";
        String user = "root";
        String password = "sam123";
        return DriverManager.getConnection(url, user, password);
    }

    public void addOrder(OrderDTO orderDTO) throws SQLException {
        String query = "INSERT INTO Orders (productID, quantity, consumerID, orderDate) VALUES (?, ?, ?, NOW())";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, orderDTO.getProductID());
            statement.setInt(2, orderDTO.getQuantity());
            statement.setInt(3, orderDTO.getConsumerID());
            statement.executeUpdate();
        }
    }

    public List<OrderDTO> getOrderHistoryByConsumerID(int consumerID) throws SQLException {
        List<OrderDTO> orderList = new ArrayList<>();
        String query = "SELECT o.orderID, p.name AS productName, o.quantity, o.orderDate FROM Orders o JOIN Products p ON o.productID = p.productID WHERE o.consumerID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, consumerID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OrderDTO order = new OrderDTO();
                    order.setOrderID(resultSet.getInt("orderID"));
                    order.setProductName(resultSet.getString("productName"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    order.setOrderDate(resultSet.getTimestamp("orderDate").toString());
                    orderList.add(order);
                }
            }
        }
        return orderList;
    }
}
