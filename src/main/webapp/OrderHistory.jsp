<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
    <link rel="stylesheet" href="css/consumerStyle.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image/x-icon" href="Images/Logo.png">
</head>
<body>
<%@ include file="consHeader.jsp" %>

<div class="consumer-container">
    <h1>Your Order History</h1>
    <%
        User user = (User) session.getAttribute("currentUser");
        List<Order> orderHistory = (List<Order>) request.getAttribute("orderHistory");

        if (user != null) {
    %>
    <table>
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Order Date</th>
            </tr>
        </thead>
        <tbody>
        <%
            if (orderHistory != null && !orderHistory.isEmpty()) {
                for (Order order : orderHistory) {
        %>
            <tr>
                <td><%= order.getOrderID() %></td>
                <td><%= order.getProductID() %></td>
                <td><%= order.getQuantity() %></td>
                <td><%= order.getOrderDate() %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="4">No orders found.</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
        } else {
    %>
    <p>Error: User not logged in or session expired.</p>
    <p><a href="login.jsp">Click here to log in</a></p>
    <%
        }
    %>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
