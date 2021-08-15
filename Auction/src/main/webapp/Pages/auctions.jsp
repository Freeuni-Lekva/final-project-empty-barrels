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
        <h1>Auctions</h1>
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

    <br>
    <a href="account-home">Back</a>
</body>
</html>
