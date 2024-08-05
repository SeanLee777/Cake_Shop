<%@ page import="model.User" %>
<%@ page import="model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Supplier Dashboard - Cake Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/supplierStyle.css">
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<%@ include file="header.jsp" %>
<body>
<div class="content">
    <h1>Welcome to the Cake Shop Supplier Dashboard!</h1>
    <%
        if (session != null && session.getAttribute("currentUser") != null) {
            // User is logged in
            User user = (User) session.getAttribute("currentUser");
            UserType userType = user.getUserType();
            if (userType == UserType.Suppler) { %>
    <h2>Welcome <%= user.getName() %>!</h2>
    <div class="dashboard-buttons">
        <!-- Button to manage orders -->
        <a href="ManageOrdersServlet" class="button">Manage Orders</a>
        <!-- Button to update inventory -->
        <a href="UpdateInventoryServlet" class="button">Update Inventory</a>
        <!-- Button to view supplier profile -->
        <a href="ViewProfileServlet" class="button">View Profile</a>
    </div>
    <% } else { %>
    <p>You do not have access to this page.</p>
    <% }
    } else {
        // User is not logged in
        response.sendRedirect("login.jsp");
    }
    %>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
