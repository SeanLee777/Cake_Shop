package controller;

import dao.ProductDAOImpl;
import dto.ProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/UpdateFoodServlet")
public class UpdateFoodServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(UpdateFoodServlet.class);


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productID;
        String name;
        int quantity;
        double price;
        java.sql.Date expirationDate;

        try {
            productID = Integer.parseInt(request.getParameter("productID"));
            name = request.getParameter("name");
            quantity = Integer.parseInt(request.getParameter("quantity"));
            price = Double.parseDouble(request.getParameter("price"));
            expirationDate = java.sql.Date.valueOf(request.getParameter("expirationDate")); // Converts String to java.sql.Date.

            ProductDTO productDTO = new ProductDTO(productID, name, quantity, expirationDate, price);
            ProductDAOImpl foodDAOImpl = new ProductDAOImpl();

            boolean updated = foodDAOImpl.updateFood(productDTO);
            if (updated) {
                logger.info("Food updated successfully: " + name);
                response.sendRedirect("update_food.jsp?message=Food item updated successfully.");

            } else {
                logger.warn("Failed to update food: " + name);
                response.sendRedirect("edit_food_details.jsp?productID=" + productID + "&error=Failed to update food item.");
            }

        } catch (IllegalArgumentException e) {
            logger.error("Invalid input format", e);
            response.sendRedirect("edit_food_details.jsp?productID=" + request.getParameter("productID") + "&error=Invalid input format.");

        } catch (SQLException e) {
            logger.error("Database error while updating food", e);
            response.sendRedirect("edit_food_details.jsp?productID=" + request.getParameter("productID") + "&error=Database error during food update.");
        }
    }
}