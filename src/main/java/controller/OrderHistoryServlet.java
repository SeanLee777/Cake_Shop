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
import java.util.List;

@WebServlet("/OrderHistoryServlet")
public class OrderHistoryServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(OrderHistoryServlet.class);
    private final OrderDAO orderDAO = new OrderDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userID = currentUser.getUserID();

        try {
            List<Order> orderHistory = orderDAO.getOrderHistoryByUserID(userID);

            if (orderHistory == null || orderHistory.isEmpty()) {
                request.setAttribute("orderHistory", null);
                logger.warn("No orders found for user ID: " + userID);
            } else {
                request.setAttribute("orderHistory", orderHistory);
                logger.info("Order history retrieved for user ID: " + userID);
            }

            request.getRequestDispatcher("OrderHistory.jsp").forward(request, response);
        } catch (SQLException e) {
            logger.error("SQL Exception in OrderHistoryServlet during order history retrieval", e);
            response.sendRedirect("error.jsp");
        }
    }
}
