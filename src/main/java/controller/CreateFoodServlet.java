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



@WebServlet("/CreateFoodServlet")
public class CreateFoodServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(CreateFoodServlet.class);

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("foodName");
        String quantity = request.getParameter("quantity");
        String expirationDate = request.getParameter("expiry_date");
        String price = request.getParameter("price");

        ProductDAOImpl foodDAOImpl = new ProductDAOImpl();
        try {
            ProductDTO productDTO = new ProductDTO(name,
                    Integer.parseInt(quantity),
                    java.sql.Date.valueOf(expirationDate),
                    Double.parseDouble(price));

            boolean created = foodDAOImpl.createFood(productDTO);
            if (created) {
                response.sendRedirect("create_food.jsp?message=Food created successfully");
                logger.info("Food created successfully: " + name);

            } else {
                response.sendRedirect("create_food.jsp?error=Failed to create food");
                logger.warn("Failed to create food: " + name);
            }

        } catch (SQLException e) {
            logger.error("Database error in CreateFoodServlet", e);
            response.sendRedirect("create_food.jsp?error=Database error during food creation");

        } catch (NumberFormatException e) {
            logger.error("Invalid input format in CreateFoodServlet", e);
            response.sendRedirect("create_food.jsp?error=Invalid input format");
        }
    }
}