/**
 **Java Final Group Project
 **Class:CST8288-Object Oriented Programming with Design Patterns
 **Section:031
 **Author:Jialin Wang
 **Student ID:041041336
 */
package controller;

import dao.OrderDAOImpl;
import dao.ProductDAOImpl;
import dto.OrderDTO;
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
import java.util.Enumeration;

@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(PurchaseServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAOImpl productDAO = new ProductDAOImpl();
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        String userEmail = request.getParameter("userEmail");
        int userID = orderDAO.getUserIDbyEmail(userEmail);
        if (userID == -1) {
            logger.error("User email not found or database error: " + userEmail);
            return;
        }
        logger.info("Processing purchase for userEmail: {}", userEmail);

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("quantity")) {
                int productId = Integer.parseInt(paramName.replaceAll("\\D+", ""));
                int quantityToPurchase = Integer.parseInt(request.getParameter(paramName));

                if (quantityToPurchase > 0) {
                    try {
                        ProductDTO product = productDAO.getFoodByID(productId);
                        if (product != null && product.getQuantity() >= quantityToPurchase) {
                            boolean updateSuccess = productDAO.updateSurplusFoodList(productId, product.getQuantity() - quantityToPurchase);
                            if (updateSuccess) {
                                OrderDTO order = new OrderDTO(userID, productId, quantityToPurchase);
                                boolean orderCreationSuccess = orderDAO.createOrder(order);
                                if (!orderCreationSuccess) {
                                    logger.error("Failed to create order for user: " + userEmail + " and productID: " + productId);
                                }
                            } else {
                                logger.error("Failed to update food quantity for productID: " + productId);
                            }
                        } else {
                            logger.error("Requested quantity exceeds available stock for productID: " + productId);
                        }
                    } catch (SQLException e) {
                        logger.error("SQLException while processing purchase for productID: " + productId, e);
                    }
                }
            }
        }
        request.getSession().setAttribute("message", "Purchase successful.");
        response.sendRedirect("ConsumerIndex.jsp");
    }

    public void setProductDAO(ProductDAOImpl productDAO) {
    }

    public void setOrderDAO(OrderDAOImpl orderDAO) {
    }
}
