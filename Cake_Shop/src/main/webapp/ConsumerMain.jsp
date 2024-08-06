<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOC TYPE html>
<html>
<head>
    <title>Set Order - Cake Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/consumerStyle.css">
    <link rel="icon" type="image/x-icon" href="Images/favicon.ico">
</head>
<%@ include file="header.jsp" %>
<body>
<div class="content">
    <h1>Set Your Order</h1>
    <form action="SetOrderServlet" method="post">
        <label for="productID">Product ID:</label>
        <input type="text" id="productID" name="productID" required><br>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" required><br>

        <button class="button" type="submit">Submit Order</button>
    </form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
