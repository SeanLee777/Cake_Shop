package org.cake_shop.controller;




import org.cake_shop.dto.OrderDTO;
import org.cake_shop.model.Consumer;
import org.cake_shop.service.OrderService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*@WebServlet("/ConsumerHistoryServlet")
public class ConsumerHistoryServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private ConsumerService consumerService = new ConsumerService();*/

    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userID = (int) request.getSession().getAttribute("userID");

        Consumer consumer = consumerService.getConsumerByUserID(userID);
        int consumerID = consumer.getConsumerID();

        List<OrderDTO> orderList = orderService.getOrderHistoryByConsumerID(consumerID);

        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("ConsumerHistory.jsp").forward(request, response);
    }
}*/

