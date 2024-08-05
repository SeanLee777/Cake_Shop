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

@WebServlet("/FoodEditServlet")
public class FoodEditServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(FoodEditServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        ProductDAOImpl foodDAO = new ProductDAOImpl();

        try {
            ProductDTO food = foodDAO.getFoodByID(productID);
            logger.info("Food item found: " + food);

            if (food != null) {
                request.setAttribute("food", food);
                request.setAttribute("productID", food.getProductID());
                request.getRequestDispatcher("/edit_food_details.jsp").forward(request, response);

            } else {
                response.sendRedirect("update_food.jsp?error=Food item not found.");
            }

        } catch (Exception e) {
            logger.error("Error in FoodEditServlet", e);
            response.sendRedirect("update_food.jsp");
        }
    }
}
