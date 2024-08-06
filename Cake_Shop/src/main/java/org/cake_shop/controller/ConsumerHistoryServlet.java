package org.cake_shop.controller;

import org.cake_shop.dto.OrderDTO;
import org.cake_shop.model.Consumer;
import org.cake_shop.service.ConsumerService;
import org.cake_shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class ConsumerHistoryServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private ConsumerService consumerService = new ConsumerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = (int) request.getSession().getAttribute("userID");

        Consumer consumer = consumerService.getConsumerByUserID(userID);
        int consumerID = consumer.getConsumerID();

        List<OrderDTO> orderList = orderService.getOrderHistoryByConsumerID(consumerID);

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("ConsumerHistory.jsp").forward(request, response);
    }
}