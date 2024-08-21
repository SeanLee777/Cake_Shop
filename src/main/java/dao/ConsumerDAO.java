package dao;

import model.Consumer;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerDAO {

    public Consumer getConsumerByUserID(int userID) throws SQLException {
        String query = "SELECT * FROM Consumers WHERE userID = ?";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Consumer consumer = new Consumer();
                    consumer.setConsumerID(resultSet.getInt("consumerID"));
                    consumer.setUserID(resultSet.getInt("userID"));
                    consumer.setAddress(resultSet.getString("address"));
                    return consumer;
                }
            }
        }
        return null;
    }

    public boolean updateConsumerAddress(int consumerID, String newAddress) throws SQLException {
        String query = "UPDATE Consumers SET address = ? WHERE consumerID = ?";
        try (Connection connection = DatabaseUtil.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newAddress);
            statement.setInt(2, consumerID);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }
}
