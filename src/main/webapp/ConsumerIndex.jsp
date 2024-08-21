<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Consumer" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Consumer Profile</title>
    <link rel="stylesheet" href="css/consumerStyle.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image/x-icon" href="Images/Logo.png">
</head>
<body>
<%@ include file="consHeader.jsp" %>

<div class="consumer-container">
    <h1>Consumer Profile</h1>
    <%
        User user = (User) session.getAttribute("currentUser");
        Consumer consumer = (Consumer) session.getAttribute("consumer");

        if (user != null && consumer != null) {
    %>
    <table>
        <tr>
            <td><strong>Name:</strong></td>
            <td><%= user.getName() %></td>
        </tr>
        <tr>
            <td><strong>Email:</strong></td>
            <td><%= user.getEmail() %></td>
        </tr>
        <tr>
            <td><strong>City:</strong></td>
            <td><%= user.getCity() %></td>
        </tr>
        <tr>
            <td><strong>Postal Code:</strong></td>
            <td><%= user.getPostalCode() %></td>
        </tr>
        <tr>
            <td><strong>Phone Number:</strong></td>
            <td><%= user.getPhoneNumber() %></td>
        </tr>
        <tr>
            <td><strong>Address:</strong></td>
            <td><%= consumer.getAddress() %></td>
        </tr>
    </table>
    <br>
    <a href="OrderHistory.jsp">Order History</a>
    <br>
    <a href="SetOrder.jsp">Set Order</a>
    <br>
    <a href='LogoutServlet'>Logout</a>
    <%
        } else {
    %>
    <p>Error: User not logged in or session expired.</p>
    <p><a href="login.jsp">Click here to login</a></p>
    <%
        }
    %>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
