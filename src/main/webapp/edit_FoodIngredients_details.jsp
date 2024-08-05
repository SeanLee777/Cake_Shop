<%@ page import="model.UserType" %>
<%@ page import="model.User" %>
<%@ page import="dto.FoodIngredientsDTO" %>
<%--
  User: fengqi
  Date: 2024-08-04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Food Ingredients Details</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/retailerStyle.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="content">
    <h1>Edit Food Ingredients Details</h1>
    <p>
        <%
            if (session != null && session.getAttribute("currentUser") != null) {
                // User is logged in
                User user = (User) session.getAttribute("currentUser");
                UserType userType = user.getUserType();
                // Check for valid user type
                if (userType == UserType.RETAILER || userType == UserType.Suppler) {
        %>
    </p>
    <form action="UpdateFoodIngredientsServlet" method="post">
        <input type="hidden" id="ingredientID" name="ingredientID" value="<%= request.getAttribute("ingredientID") %>"/>

        <label for="name">Ingredient Name:</label>
        <input type="text" id="name" name="name" value="${foodIngredient.name}" required><br>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" value="${foodIngredient.quantity}" required><br>

        <label for="unit">Unit:</label>
        <input type="text" id="unit" name="unit" value="${foodIngredient.unit}" required><br>

        <label for="price">Price per Unit:</label>
        <input type="text" id="price" name="price" value="${foodIngredient.price}" required><br>

        <button class="button" type="submit">Update Ingredient</button>
    </form>
    <%
            } else {
                // User does not have the appropriate user type
                response.sendRedirect("login.jsp");
            }
        } else {
            // User is not logged in
            response.sendRedirect("login.jsp");
        }
    %>
    </p>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
