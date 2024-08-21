<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Order Success</title>
    <link rel="stylesheet" href="css/consumerStyle.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="icon" type="image/x-icon" href="Images/Logo.png">
</head>
<body>
<%@ include file="consHeader.jsp" %>

<div class="consumer-container">
    <!-- Success Checkmark Image -->
    <div style="text-align: center; margin-bottom: 20px;">
        <img src="Images/success_checkmark.png" alt="Success" style="width: 100px; height: 100px;">
    </div>

    <h1>Order Successful</h1>
    <p>Thank you for your order! Your order has been successfully placed.</p>
    <p>You will receive a confirmation email with the details of your order shortly.</p>

    <div class="order-details">
        <h2>Order Details</h2>
        <table>
            <tr>
                <th>Order ID</th>
                <td><%= request.getAttribute("orderID") %></td>
            </tr>
            <tr>
                <th>Total Amount</th>
                <td>$<%= request.getAttribute("totalAmount") %></td>
            </tr>
            <tr>
                <th>Order Date</th>
                <td><%= request.getAttribute("orderDate") %></td>
            </tr>
        </table>
    </div>

    <div class="actions">
        <a class="btn" href="ConsumerIndex.jsp">Back to Home</a>
        <a class="btn" href="OrderHistory.jsp">View Order History</a>
    </div>
</div>

<%@ include file="footer.jsp" %>
</body>
</html>
