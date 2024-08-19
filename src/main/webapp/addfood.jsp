<%--
  Created by IntelliJ IDEA.
  User: artqi
  Date: 8/19/2024
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Food Ingredient</title>
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
        }

        .form-container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input[type="text"],
        input[type="number"],
        button {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        button {
            background-color: #28a745;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="content">
    <div class="form-container">
        <h2>Add Food Ingredient</h2>
        <form action="AddFoodServlet" method="post">
            <label for="inventoryID">Inventory ID:</label>
            <input type="number" id="inventoryID" name="inventoryID" placeholder="Enter Inventory ID" required>

            <label for="productID">Product ID:</label>
            <input type="number" id="productID" name="productID" placeholder="Enter Product ID" required>

            <label for="name">Ingredient Name:</label>
            <input type="text" id="name" name="name" placeholder="Enter Ingredient Name" required>

            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" placeholder="Enter Quantity" required>

            <label for="unit">Unit:</label>
            <input type="text" id="unit" name="unit" placeholder="Enter Unit (e.g., kg, lbs)" required>

            <label for="price">Price per Unit:</label>
            <input type="number" id="price" name="price" step="0.01" placeholder="Enter Price per Unit" required>

            <button type="submit">Add Ingredient</button>
        </form>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
