<!--
* Java Final Group 11 Project
* Class: CST8319
* Author: Shanghao Li
* Student ID: 040903008
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/registrationStyle.css">
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="content">
    <div class="registration-container">
        <h2>User Registration</h2>
        <%-- Error message handling --%>
        <% String errorMessage = (String) request.getAttribute("error");
            if (errorMessage != null && !errorMessage.isEmpty()) {%>
        <div style="color: red;"><%= errorMessage %>
        </div>
        <%}%>
        <form action="register" method="post">
            <div>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="flex-item">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="flex-item">
                <label for="phone">Phone Number:</label>
                <input type="text" id="phone" name="phone">
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div>
                <label for="userType">User Type:</label>
                <select id="userType" name="userType">
                    <option value="RETAILER">Retailer</option>
                    <option value="CONSUMERS">Consumers</option>
                    <option value="SUPPLER">Suppler</option>
                </select>
            </div>
            <div class="flex-container">
                <div class="flex-item">
                    <label for="city">City:</label>
                    <input type="text" id="city" name="city">
                </div>
                <div class="flex-item">
                    <label for="postalCode">Postal Code:</label>
                    <input type="text" id="postalCode" name="postalCode">
                </div>
            </div>

            <div>
                <button type="submit">Register</button>
            </div>
        </form>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script src="js/checkboxDropdown.js"></script>
</body>
</html>