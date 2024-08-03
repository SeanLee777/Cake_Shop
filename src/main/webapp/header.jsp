<!--
* Java Final Group 11 Project
* Class: CST8319
* Author: Shanghao Li
* Student ID: 040903008
-->

<div id="navbar">
    <img src="Images/Logo.png" alt="Cake Shop Logo" style="width: 80px; height: 60px;">

    <div class="nav-items">
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <% 
            if (session != null && session.getAttribute("currentUser") != null) {
                out.println("<li><a href='LogoutServlet'>Logout</a></li>");
            } else {
                out.println("<li><a href='login.jsp'>Login</a></li>");
                out.println("<li><a href='registration.jsp'>Register</a></li>");
            }
            %>
        </ul>
    </div>
</div>
