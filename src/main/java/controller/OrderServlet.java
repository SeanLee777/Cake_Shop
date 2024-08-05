package controller;

import dao.IUserDAO;
import dao.OrderDAO;
import dao.OrderDAOImpl;
import dao.UserDAO;
import dto.OrderDTO;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
/
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(OrderServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        String email = request.getParameter("email");
        IUserDAO userDAO = new UserDAO();
        try {
            Optional<User> optUser = userDAO.findByEmail(email);

            if (optUser.isPresent()) {

                OrderDAO orderDAO = new OrderDAOImpl(); // Again, ensure this is your actual DAO implementation
                List<OrderDTO> orders = orderDAO.getOrderByEmail(user.getEmail());
                logger.info("Order found for : " + user.getName());
                request.setAttribute("orders", orders);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/order.jsp");
                dispatcher.forward(request, response);
            } else {

                logger.warn("No user found with email: " + email);

                request.setAttribute("error", "No user found for the provided email.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            logger.error("SQLException in OrderServlet for email: " + email, e);

            throw new RuntimeException(e);
        }
    }
}
