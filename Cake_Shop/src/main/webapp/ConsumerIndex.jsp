<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Consumer Dashboard - Cake Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/consumerStyle.css">
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<%@ include file="header.jsp" %>
<body>
<div class="content">
    <h1>Welcome to the Cake Shop Management System!</h1>
    <div class="dashboard-buttons">
        <!-- Button to set order -->
        <a href="ConsumerMain.jsp" class="button">Set Order</a>
        <!-- Button to view order history -->
        <a href="ConsumerHistoryServlet" class="button">View My Order History</a>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
