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
            if (userType == UserType.SUPPLER) { %>
    <h2>Welcome <%= user.getName() %>!</h2>
    <div class="dashboard-buttons">

        <!-- Modify the button link to point to addfood.jsp -->
        <a href="addfood.jsp" class="button">Add Food Ingredient</a>

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
