package controller;

import dao.ConsumerDAO;
import model.Consumer;
import model.User;
import model.UserType;
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

@WebServlet("/ConsumerServlet")
public class ConsumerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ConsumerServlet.class);
    private final ConsumerDAO consumerDAO = new ConsumerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if (user != null && user.getUserType() == UserType.CONSUMERS) {
            logger.info("User found in session: " + user.getName());
            try {
                Consumer consumer = consumerDAO.getConsumerByUserID(user.getUserID());
                if (consumer != null) {
                    session.setAttribute("consumer", consumer);
                    request.getRequestDispatcher("ConsumerIndex.jsp").forward(request, response);
                } else {
                    logger.warn("Consumer not found for user ID: " + user.getUserID());
                    response.sendRedirect("errorPage.jsp"); // Redirect to an error page
                }
            } catch (SQLException e) {
                logger.error("SQL Exception in ConsumerServlet during GET", e);
                response.sendRedirect("errorPage.jsp"); // Redirect to an error page
            }
        } else {
            logger.warn("User not found in session or not a consumer, redirecting to login.");
            response.sendRedirect("login.jsp");
        }
    }
}
