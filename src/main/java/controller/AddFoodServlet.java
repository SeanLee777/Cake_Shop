package controller;

import dao.FoodDAO;
import dto.FoodDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddFoodServlet")
public class AddFoodServlet extends HttpServlet {
    private FoodDAO foodDAO;

    @Override
    public void init() {
        foodDAO = new FoodDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inventoryIdStr = request.getParameter("inventoryID");
        String productIdStr = request.getParameter("productID");
        String name = request.getParameter("name");
        String quantityStr = request.getParameter("quantity");
        String unit = request.getParameter("unit");
        String priceStr = request.getParameter("price");

        if (inventoryIdStr == null || productIdStr == null || name == null || quantityStr == null || unit == null || priceStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
            return;
        }

        try {
            int inventoryId = Integer.parseInt(inventoryIdStr);
            int productId = Integer.parseInt(productIdStr);
            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);

            FoodDTO foodIngredient = new FoodDTO();
            foodIngredient.setInventoryId(inventoryId);
            foodIngredient.setProductId(productId);
            foodIngredient.setName(name);
            foodIngredient.setQuantity(quantity);
            foodIngredient.setUnit(unit);
            foodIngredient.setPrice(price);

            foodDAO.addFood(foodIngredient);

            response.sendRedirect("AddFoodSuccess.jsp"); // Success Page
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        }
    }
}
