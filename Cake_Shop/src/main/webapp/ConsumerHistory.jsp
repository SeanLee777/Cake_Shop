<%@ page import="dto.OrderDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOC TYPE html>
<html>
<head>
    <title>Order History - Cake Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/consumerStyle.css">
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<%@ include file="header.jsp" %>
<body>
<div class="content">
    <h1>Your Order History</h1>
    <table>
        <thead>
        <tr>
            <th>Order ID</th>
            <th>Product ID</th>
            <th>Quantity</th>
            <th>Order Date</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<OrderDTO> orderList = (List<OrderDTO>) request.getAttribute("orderList");
            if (orderList != null) {
                for (OrderDTO order : orderList) {
        %>
        <tr>
            <td><%= order.getOrderID() %></td>
            <td><%= order.getProductID() %></td>
            <td><%= order.getQuantity() %></td>
            <td><%= order.getOrderDate() %></td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
