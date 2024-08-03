<!--
* Java Final Group 11 Project
* Class: CST8319
* Author: Shanghao Li
* Student ID: 040903008
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
   <head>
      <title>Registration Successful</title>
      <link rel="stylesheet" href="css/style.css">
      <link rel="stylesheet" href="css/style_registerSuccess.css">
   </head>
   <body>
      <%@ include file="header.jsp" %>
      <div class="content">
         <div class="success-container">
            <img src="Images/success_checkmark.png" alt="Success" style="width: 100px; height: 100px;">
            <h2>Registration Successful!</h2>
            <p>Your account has been successfully created.</p>
            <a href="login.jsp">Login</a>
         </div>
      </div>
      <%@ include file="footer.jsp" %>
   </body>
</html>