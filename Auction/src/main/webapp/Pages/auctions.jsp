<%@ page import="Models.User" %>
<%@ page import="Models.UserInfo" %>
<%@ page import="DAO.SqlAuctionDAO" %>
<%@ page import="Models.Auction" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static Helper.GeneralConstants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Auction auctions = (Auction)application.getAttribute("AUCTIONS");
    User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
    UserInfo currentUserInfo = (UserInfo)session.getAttribute(CURRENT_USER_INFO_STRING);
%>
<html>
<head>
    <meta charset="utf-8">
            <title>Auctions</title>
            <link rel="preconnect" href="https://fonts.gstatic.com">
            <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;700&display=swap" rel="stylesheet">
            <link rel="preconnect" href="https://fonts.gstatic.com">
            <link href="https://fonts.googleapis.com/css2?family=Poppins&display=swap" rel="stylesheet">
            <script src="https://kit.fontawesome.com/a8ba60cbab.js" crossorigin="anonymous"></script>
            <link rel="stylesheet" type="text/css" href="../Styles/reset.css">
            <link rel="stylesheet" type="text/css" href="../Styles/style.css">
            <title>Auctions</title>
                <style>
                      table,
                      th,
                      td {
                        padding: 10px;
                        border: 1px solid black;
                        border-collapse: collapse;
                      }
                </style>
</head>
<body>
    <div class="main-div">
        <h2 class="profile-name">Auctions</h2>

        <div>
            <table border="1">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>seller ID</th>
                  <th>item ID</th>
                  <th>starting price</th>
                  <th>min increment</th>
                  <th>end time</th>
                  <th>current price</th>
                </tr>
               </thead>
               <tbody>

                <%
                        SqlAuctionDAO auctionDao = (SqlAuctionDAO)application.getAttribute("AUCTION_DAO_STR_UNIQUE");
                        for (Auction auc : auctionDao.getAllAuctions()){
                            out.println("<tr><td>"+auc.getId()+"</td><td>"+auc.getSeller_id()+"</td><td>"+auc.getItem_id()+"</td><td>"+auc.getStarting_price()+"</td><td>"+auc.getMin_increment()+"</td><td>"+auc.getEnd_date()+"</td><td>"+auc.getCurrent_price()+"</td></tr>");
                        }
                %>
              </tbody>
            </table>
        </div>

        <br>
        <a class="h4-link-2" href="account-home">Back</a>
    </div>
</body>
</html>
