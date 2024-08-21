/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package controller;

import java.io.IOException;
import java.io.Serial;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet responsible for handling user logout requests.
 * It manages the invalidation of the user's session and redirects the user to the login page.
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    /**
     * Serial version UID for serialization and deserialization purposes.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Logger for logging logout related information.
     */
    private static final Logger logger = LogManager.getLogger(LogoutServlet.class);

    /**
     * Processes the GET request for user logout.
     * Invalidates the current user's session if it exists and redirects to the login page.
     *
     * @param request  HttpServletRequest object that contains the request the client has made of the servlet
     * @param response HttpServletResponse object that contains the response the servlet sends to the client
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false); // false = don't create a new session
        if (session != null) {
            logger.info("Invalidating session for user: {}", session.getAttribute("currentUser"));
            session.invalidate(); // Invalidating session
        } else {
            logger.info("Logout requested but no session found.");
        }
        response.sendRedirect("login.jsp");
        logger.info("Redirected to login page after logout.");
    }

}
