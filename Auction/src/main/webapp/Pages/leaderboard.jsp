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
    <meta charset="utf-8">
    <title>Leaderboard</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a8ba60cbab.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="../Styles/reset.css">
    <link rel="stylesheet" type="text/css" href="../Styles/style.css">
</head>
<body>
    <div class="main-div">
        <h1 class="h1">Top <%=topUsers.size()%> Users:</h1>
        <br>

        <ol>
            <% for (User user : topUsers) { %>
                <li class="label-2"><%=user.getUsername()%>: <%=user.getNumAuctionsWon()%></li>
                <br>
            <% } %>
        </ol>

        <br>
        <a class="h4-link" href="account-home">Back</a>
    </div>
</body>
</html>
