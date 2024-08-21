package controller;

import dao.OrderDAO;
import model.Order;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(OrderServlet.class);
    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("setOrder".equals(action)) {
            handleSetOrder(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("orderHistory".equals(action)) {
            handleOrderHistory(request, response);
        }
    }

    private void handleSetOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            logger.warn("Attempted to set order without a logged-in user. Redirecting to login.");
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            int userID = currentUser.getUserID();
            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            LocalDateTime orderDate = LocalDateTime.now();

            int generatedOrderID = orderDAO.createOrder(userID, productID, quantity, orderDate);
            double totalAmount = orderDAO.calculateTotalAmount(generatedOrderID);

            request.setAttribute("orderID", generatedOrderID);
            request.setAttribute("totalAmount", totalAmount);
            request.setAttribute("orderDate", orderDate);

            logger.info("Order successfully placed with ID: {}, Total Amount: ${}, Date: {}", generatedOrderID, totalAmount, orderDate);

            request.getRequestDispatcher("OrderSuccess.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.error("SQL Exception during order creation", e);
            response.sendRedirect("error.jsp");
        }
    }

    private void handleOrderHistory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            logger.warn("No user found in session. Redirecting to login.");
            response.sendRedirect("login.jsp");
            return;
        }

        int userID = currentUser.getUserID();
        logger.info("Attempting to retrieve order history for user ID: {}", userID);

        try {
            List<Order> orderHistory = orderDAO.getOrderHistoryByUserID(userID);
            logger.info("Retrieved {} orders for User ID: {}", orderHistory.size(), userID);

            request.setAttribute("orderHistory", orderHistory);
            request.getRequestDispatcher("OrderHistory.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.error("SQL Exception during order history retrieval", e);
            response.sendRedirect("error.jsp");
        }
    }
}
