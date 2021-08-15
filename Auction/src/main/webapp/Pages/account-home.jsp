<%@ page import="Models.User" %>
<%@ page import="Models.UserInfo" %>
<%@ page import="static Helper.GeneralConstants.*" %><%--
  Created by IntelliJ IDEA.
  User: samad
  Date: 12.08.2021
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
//    UserInfo currentUserInfo = (UserInfo)session.getAttribute(CURRENT_USER_INFO_STRING);
%>
<html>
<head>
    <title>Account Home</title>
</head>
<body>
    <h1>User: <%=currentUser.getUsername()%></h1>

    <a href="profile">My Profile</a>
    <br>
    <a href="logout">Logout</a>
    <br>
    <a href="auctions">Auctions</a>
</body>
</html>
