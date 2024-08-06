package org.cake_shop.service;

import org.cake_shop.dao.ConsumerDAO;
import org.cake_shop.dto.ConsumerDTO;
import org.cake_shop.model.Consumer;

import java.sql.SQLException;
import java.util.List;

public class ConsumerService {
    private ConsumerDAO consumerDAO = new ConsumerDAO();

    public void addConsumer(ConsumerDTO consumerDTO) {
        try {
            consumerDAO.addConsumer(consumerDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Consumer getConsumerByID(int consumerID) {
        try {
            return consumerDAO.getConsumerByConsumerID(consumerID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Consumer> getAllConsumers() {
        try {
            return consumerDAO.getAllConsumers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateConsumer(ConsumerDTO consumerDTO) {
        try {
            consumerDAO.updateConsumer(consumerDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteConsumer(int consumerID) {
        try {
            consumerDAO.deleteConsumer(consumerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
