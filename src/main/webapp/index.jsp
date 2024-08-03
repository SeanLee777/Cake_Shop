<!--
* Java Final Group 11 Project
* Class: CST8319
* Author: Shanghao Li
* Student ID: 040903008
-->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UserType" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Cake Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
    <link rel="stylesheet" href="css/loginStyle.css">

</head>
<body>
<%@ include file="header.jsp" %>
<div class="content">
    <h1>Welcome to Our Cake Shop!</h1>
    <p><%
        if (session != null && session.getAttribute("currentUser") != null) {
            // User is logged in
            User user = (User) session.getAttribute("currentUser");
            UserType userType = user.getUserType();
    %></p>
    <h1>Welcome <%= user.getName() %>!</h1>
    <h2> Your are: <%= userType.toString() %>
    </h2>

    <table>
        <tr>
            <% if (userType == UserType.RETAILER) { %>
            <td><a class="button" href="create_cake.jsp">Create Cake</a></td>
            <td><a class="button" href="view_all_orders" >View all Customers' Order</a></td>
            <% } else if (userType == UserType.Suppler) {%>
            <td><a class="button" href="CharityIndex.jsp">Supplier Main Page</a></td>
            <% } else if (userType == UserType.CONSUMER) {%>
            <td><a class="button" href="ConsumerIndex.jsp">Consumers Main Page</a></td>            <% } %>
        </tr>
    </table>
    <br>
    <a href='LogoutServlet'>Logout</a>
    <%
    } else {
        // User is not logged in
        response.sendRedirect("login.jsp");%>
    <a href='login.jsp'>Login</a> | <a href='registration.jsp'>Register</a>
    <%
        }
    %>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>