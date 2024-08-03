/**
 * Java Final Group Project
 * Class: CST8288 - Object Oriented Programming with Design Patterns
 * Section: 031
 * Author: Oliver Kadvany
 * Student ID: 041096826
 */

package controller;

import dao.UserDAO;
import dto.UserDTO;
import model.User;
import model.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.PasswordHashingUtil;
import validation.UserValidator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;

/**
 * Servlet responsible for handling user registration requests.
 * Captures user input, validates it, and processes registration.
 * It interacts with the data layer to store new user information and manages redirection based on the operation outcome.
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    /**
     * Serial version UID for serialization and deserialization.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Logger for logging registration related information and errors.
     */
    private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);

    /**
     * Processes the POST request for user registration.
     * Captures user input, performs validation, and then creates a new user record if validation is successful.
     * Redirects the user to the appropriate page based on the success or failure of the registration process.
     *
     * @param request  HttpServletRequest object that contains the request the client has made of the servlet
     * @param response HttpServletResponse object that contains the response the servlet sends to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Capturing user input from request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        String city = request.getParameter("city");
        String postalCode = request.getParameter("postalCode");
        String phoneNumber = request.getParameter("phone");

        // Creating UserDTO object
        UserDTO userDTO = new UserDTO(name, email, password, userType, city, postalCode, phoneNumber);

        // Validating userDTO data
        if (!UserValidator.isValidUser(userDTO)) {
            // Validation failed, forwarding to registration page with error message
            request.setAttribute("error", "Invalid user data");
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Converting UserDTO to User
        UserType userTypeEnum = UserType.valueOf(userDTO.getUserType().toUpperCase());
        String hashedPassword = PasswordHashingUtil.hashPassword(userDTO.getPassword());
        User newUser = new User(name, email, hashedPassword, userTypeEnum, city, postalCode, phoneNumber);

        // Processing data using UserDAO
        UserDAO userDAO = new UserDAO();
        boolean isUserCreated;
        try {
            isUserCreated = userDAO.createUser(newUser);
        } catch (SQLException e) {
            logger.error("SQL Exception in RegistrationServlet", e);
            response.sendRedirect("registrationFail.jsp");
            return;
        }

        // Responding based on DAO operation result
        if (isUserCreated) {
            // User successfully created, forwarding to success page
            request.setAttribute("status", "Registration successful!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            dispatcher.forward(request, response);
        } else {
            // User creation failed
            response.sendRedirect("registration.jsp?error=Registration failed");
        }
    }
}
