/**
 * Java Final Group Project
 * Class: CST8288 - Object Oriented Programming with Design Patterns
 * Section: 031
 * Author: Jialin Wang
 * Student ID: 041041336
 */
package controller;

import dao.OrderDAOImpl;
import dto.OrderDTO;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/PurchaseHistoryServlet")
public class PurchaseHistoryServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(PurchaseHistoryServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("currentUser") == null) {
            logger.warn("User is not logged in. Redirecting to login page.");
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("currentUser");
        String userEmail = user.getEmail();
        logger.info("Retrieving purchase history for user with email: {}", userEmail);

        OrderDAOImpl orderDAO = new OrderDAOImpl();
        List<OrderDTO> orderList = null;

        try {
            orderList = orderDAO.getOrderByEmail(userEmail);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving purchase history: ", e);
        }


        if (orderList == null || orderList.isEmpty()) {
            logger.info("No purchase history found for user with email: {}", userEmail);
        } else {
            logger.info("Retrieved {} purchase history entries for user with email: {}", orderList.size(), userEmail);
        }


        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/PurchaseHistory.jsp").forward(request, response);
    }

    public void setOrderDAO(OrderDAOImpl orderDAO) {
    }
}
