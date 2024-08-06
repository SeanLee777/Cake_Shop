package org.cake_shop.service;

import org.cake_shop.dao.ConsumerDAO;
import org.cake_shop.dto.ConsumerDTO;
import org.cake_shop.model.Consumer;

import java.sql.SQLException;
import java.util.List;

public class ConsumerService {
    private final ConsumerDAO consumerDAO;

    // Constructor for dependency injection
    public ConsumerService(ConsumerDAO consumerDAO) {
        this.consumerDAO = consumerDAO;
    }

    public void addConsumer(ConsumerDTO consumerDTO) throws ServiceException {
        try {
            consumerDAO.addConsumer(consumerDTO);
        } catch (SQLException e) {
            throw new ServiceException("Error adding consumer", e);
        }
    }

    public Consumer getConsumerByID(int consumerID) throws ServiceException {
        try {
            return consumerDAO.getConsumerByConsumerID(consumerID);
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving consumer by ID", e);
        }
    }

    public List<Consumer> getAllConsumers() throws ServiceException {
        try {
            return consumerDAO.getAllConsumers();
        } catch (SQLException e) {
            throw new ServiceException("Error retrieving all consumers", e);
        }
    }

    public void updateConsumer(ConsumerDTO consumerDTO) throws ServiceException {
        try {
            consumerDAO.updateConsumer(consumerDTO);
        } catch (SQLException e) {
            throw new ServiceException("Error updating consumer", e);
        }
    }

    public void deleteConsumer(int consumerID) throws ServiceException {
        try {
            consumerDAO.deleteConsumer(consumerID);
        } catch (SQLException e) {
            throw new ServiceException("Error deleting consumer", e);
        }
    }
}
