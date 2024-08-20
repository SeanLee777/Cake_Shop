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
    <meta charset="UTF-8">
    <title>Add Food Success</title>
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
            text-align: center;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
        }
        h1 {
            color: #4CAF50;
        }
        .message {
            margin-top: 20px;
            font-size: 18px;
            color: #333;
        }
        .button {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #4CAF50;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #45a049;
        }
        a {
            text-decoration: none;
            color: white;
        }
    </style>
</head>
<body>

<div class="content">
    <div class="container">
        <h1>Success!</h1>
        <p class="message">The food item has been successfully added to the inventory.</p>
        <button class="button">
            <a href="addfood.jsp">Add Another Item</a>
        </button>
        <br/><br/>
        <button class="button">
            <a href="inventoryList.jsp">View Inventory</a>
        </button>
    </div>
</div>

<%@ include file="footer.jsp" %>

</body>
</html>
