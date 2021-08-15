<%@ page import="Models.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: samad
  Date: 14.08.2021
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<User> topUsers = (List<User>)request.getAttribute("topUsers");
%>
<html>
<head>
    <title>Leaderboard</title>
</head>
<body>
    <h1>Top <%=topUsers.size()%> Users:</h1>
    <ol>
        <% for (User user : topUsers) { %>
        <li><%=user.getUsername()%>: <%=user.getNumAuctionsWon()%></li>
        <% } %>
    </ol>

    <br>
    <a href="account-home">Back</a>
</body>
</html>
