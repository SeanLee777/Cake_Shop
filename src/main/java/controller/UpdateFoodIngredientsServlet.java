package controller;

import dto.FoodIngredientsDTO;
import service.FoodIngredientsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateFoodIngredientsServlet")
public class UpdateFoodIngredientsServlet extends HttpServlet {
    private FoodIngredientsService foodIngredientsService = new FoodIngredientsService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int ingredientID = Integer.parseInt(request.getParameter("ingredientID"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String unit = request.getParameter("unit");
            double price = Double.parseDouble(request.getParameter("price"));

            FoodIngredientsDTO foodIngredientsDTO = new FoodIngredientsDTO();
            foodIngredientsDTO.setId(ingredientID);
            foodIngredientsDTO.setName(name);
            foodIngredientsDTO.setQuantity(quantity);
            foodIngredientsDTO.setUnit(unit);
            foodIngredientsDTO.setPrice(price);

            foodIngredientsService.updateFoodIngredients(foodIngredientsDTO);

            response.sendRedirect("edit_FoodIngredients_details.jsp?message=Ingredient updated successfully");
        } catch (Exception e) {
            response.sendRedirect("edit_FoodIngredients_details.jsp?error=Failed to update ingredient");
        }
    }
}