<%@ page import="Models.User" %>
<%@ page import="Models.UserInfo" %>
<%@ page import="static Helper.GeneralConstants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
    UserInfo currentUserInfo = (UserInfo)session.getAttribute(CURRENT_USER_INFO_STRING);
%>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <ul>
        <li>Username: <%=currentUser.getUsername()%></li>
        <li>First Name: <%=currentUserInfo.getFirstName()%></li>
        <li>Last Name: <%=currentUserInfo.getLastName()%></li>
        <li>Email: <%=currentUserInfo.getEmail()%></li>
        <li>Address: <%=currentUserInfo.getAddress()%></li>
        <li>Phone Number: <%=currentUserInfo.getPhoneNumber()%></li>
        <li>Note: <%=currentUserInfo.getNote()%></li>
    </ul>

</body>
</html>
