package org.cake_shop.service;


import org.cake_shop.dao.OrderDAO;
import org.cake_shop.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public void placeOrder(OrderDTO orderDTO) {
        try {
            orderDAO.addOrder(orderDTO);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderDTO> getOrderHistoryByConsumerID(int consumerID) {
        try {
            return orderDAO.getOrderHistoryByConsumerID(consumerID);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

