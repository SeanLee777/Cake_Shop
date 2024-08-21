/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package controller;

import dao.UserDAO;
import model.User;
import util.PasswordHashingUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Servlet responsible for handling user login requests.
 * It authenticates users based on email and password, manages session creation on successful login,
 * and redirects to the appropriate page based on the outcome of the authentication process.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * Serial version UID for serialization and deserialization.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Logger for logging login related information and errors.
     */
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    /**
     * Processes the POST request for user login.
     * Validates the user credentials and manages session creation and redirection based on login success or failure.
     *
     * @param request  HttpServletRequest object that contains the request the client has made of the servlet
     * @param response HttpServletResponse object that contains the response the servlet sends to the client
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        try {
            Optional<User> optUser = userDAO.findByEmail(email);

            if (optUser.isPresent() && PasswordHashingUtil.hashPassword(password).equals(optUser.get().getPassword())) {
                // Correct password
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", optUser.get());
                response.sendRedirect("index.jsp");

                logger.info("User logged in successfully: {}", email); // Log successful login
            } else {
                // Invalid login credentials
                response.sendRedirect("LoginError.jsp");

                logger.warn("Failed login attempt for email: {}", email); // Log failed login attempt
            }
        } catch (SQLException e) {
            logger.error("Database error in LoginServlet" + e);
            response.sendRedirect("login.jsp?error=Database error during login");
        }
    }
}