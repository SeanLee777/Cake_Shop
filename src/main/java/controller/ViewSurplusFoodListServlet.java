package controller;

import model.User;
import model.UserType;
import service.FoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ViewSurplusFoodListServlet")
public class ViewSurplusFoodListServlet extends HttpServlet {
    private FoodService foodService = new FoodService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");

        if (user != null && user.getUserType() == UserType.Suppler) {
            request.setAttribute("surplusFoodList", foodService.getSurplusFoodList());
            request.getRequestDispatcher("supplerIndex.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}