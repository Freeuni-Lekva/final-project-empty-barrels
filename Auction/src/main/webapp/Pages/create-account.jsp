<%--
  Created by IntelliJ IDEA.
  User: samad
  Date: 10.08.2021
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Account</title>
</head>
<body>
    <h1>Create new account</h1>
    <form action="create-account" method="post">
        Account Details
        <br>
        <label for="username-input">Username: </label>
        <input type="text" id="username-input" name="username">
        <br>
        <label for="password-input">Password: </label>
        <input type="password" id="password-input" name="password">
        <br>
        <label for="password-repeat-input">Repeat password: </label>
        <input type="password" id="password-repeat-input" name="password-repeat">
        <br>
        <label for="firstname-input">First Name: </label>
        <input type="text" id="firstname-input" name="firstname">
        <br>
        <label for="lastname-input">Last Name: </label>
        <input type="text" id="lastname-input" name="lastname">
        <br>
        <label for="email-input">Email: </label>
        <input type="text" id="email-input" name="email">
        <br>
        <label for="address-input">Address: </label>
        <input type="text" id="address-input" name="address">
        <br>
        <label for="phonenumber-input">Phone Number: </label>
        <input type="text" id="phonenumber-input" name="phonenumber">
        <br>
        <label for="note-input">Note: </label>
        <input type="text" id="note-input" name="note">
        <br>
        <br>
        <button>Create Account</button>
    </form>
</body>
</html>
