package org.cake_shop.controller;


import org.cake_shop.dto.OrderDTO;
import org.cake_shop.model.Consumer;
import javax.servlet.ServletException;
import org.cake_shop.service.ConsumerService;
import org.cake_shop.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import .servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/order")
public class SetOrderServlet extends HttpServlet {
    private OrderService orderService = new OrderService();
    private ConsumerService consumerService = new ConsumerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        int userID = (int) request.getSession().getAttribute("userID");

        Consumer consumer = consumerService.getConsumerByUserID(userID);
        int consumerID = consumer.getConsumerID();

        OrderDTO order = new OrderDTO();
        order.setProductID(productID);
        order.setQuantity(quantity);
        order.setConsumerID(consumerID);

        orderService.placeOrder(order);

        response.sendRedirect("ConsumerIndex.jsp");
    }
}