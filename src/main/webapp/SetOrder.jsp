<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Set Order</title>
    <link rel="stylesheet" href="css/consumerStyle.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image/x-icon" href="Images/Logo.png">
</head>
<body>
<%@ include file="consHeader.jsp" %>

<div class="consumer-container">
    <h1>Set Your Order</h1>
    <%
        User user = (User) session.getAttribute("currentUser");

        if (user != null) {
    %>
    <form action="OrderServlet" method="post">
        <div>
            <label for="productID">Product ID:</label>
            <input type="number" name="productID" id="productID" required>
        </div>

        <div>
            <label for="quantity">Quantity:</label>
            <input type="number" name="quantity" id="quantity" required>
        </div>

        <input type="hidden" name="action" value="setOrder">
        <input type="submit" value="Place Order">
    </form>
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
