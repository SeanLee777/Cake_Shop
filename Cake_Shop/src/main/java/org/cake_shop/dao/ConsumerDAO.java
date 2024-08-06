package org.cake_shop.dao;

import org.cake_shop.dto.ConsumerDTO;
import org.cake_shop.model.Consumer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumerDAO {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cakeshop";
        String user = "root";
        String password = "sam123"; // Consider using environment variables or a secure vault for sensitive data.
        return DriverManager.getConnection(url, user, password);
    }

    public Consumer getConsumerByUserID(int userID) throws SQLException {
        String query = "SELECT * FROM Consumers WHERE userID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToConsumer(resultSet);
                }
            }
        }
        return null;
    }

    public Consumer getConsumerByConsumerID(int consumerID) throws SQLException {
        String query = "SELECT * FROM Consumers WHERE consumerID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consumerID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToConsumer(resultSet);
                }
            }
        }
        return null;
    }

    public List<Consumer> getAllConsumers() throws SQLException {
        List<Consumer> consumers = new ArrayList<>();
        String query = "SELECT * FROM Consumers";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                consumers.add(mapResultSetToConsumer(resultSet));
            }
        }
        return consumers;
    }

    public void addConsumer(ConsumerDTO consumer) throws SQLException {
        String query = "INSERT INTO Consumers (consumerID, address, userID) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consumer.getConsumerID());
            statement.setString(2, consumer.getAddress());
            statement.setInt(3, consumer.getUserID());
            statement.executeUpdate();
        }
    }

    public void updateConsumer(ConsumerDTO consumer) throws SQLException {
        String query = "UPDATE Consumers SET address = ? WHERE consumerID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, consumer.getAddress());
            statement.setInt(2, consumer.getConsumerID());
            statement.executeUpdate();
        }
    }

    public void deleteConsumer(int consumerID) throws SQLException {
        String query = "DELETE FROM Consumers WHERE consumerID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, consumerID);
            statement.executeUpdate();
        }
    }

    private Consumer mapResultSetToConsumer(ResultSet resultSet) throws SQLException {
        Consumer consumer = new Consumer();
        consumer.setConsumerID(resultSet.getInt("consumerID"));
        consumer.setAddress(resultSet.getString("address"));
        consumer.setUserID(resultSet.getInt("userID"));
        return consumer;
    }
}
