<%--
  Created by IntelliJ IDEA.
  User: artqi
  Date: 8/19/2024
  Time: 5:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="dao.FoodDAO" %>
<%@ page import="dto.FoodDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory List</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .content {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 900px;
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        .button {
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<div class="content">
    <div class="container">
        <h1>Inventory List</h1>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Unit</th>
                <th>Price per Unit</th>
            </tr>
            </thead>
            <tbody>
            <%
                FoodDAO foodDAO = new FoodDAO();
                List<FoodDTO> foodList = foodDAO.getSurplusFoodList(); // Call DAO method to get all items
                for (FoodDTO food : foodList) {
            %>
            <tr>
                <td><%= food.getId() %></td>
                <td><%= food.getName() %></td>
                <td><%= food.getQuantity() %></td>
                <td><%= food.getUnit() %></td>
                <td><%= food.getPrice() %></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <a href="addfood.jsp" class="button">Add Another Item</a>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
