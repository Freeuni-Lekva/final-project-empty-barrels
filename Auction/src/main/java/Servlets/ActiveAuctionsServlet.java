package Servlets;

import DAO.SqlAuctionDAO;
import DAO.UserDAO;
import Helper.SessionHelper;
import Models.Auction;
import Models.User;
import Models.UserInfo;
import Services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import static Helper.GeneralConstants.*;
import static Helper.GeneralConstants.NO_ID;

public class ActiveAuctionsServlet extends HttpServlet {


    public ActiveAuctionsServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean userExists = SessionHelper.checkIfUserExists(session);

        if (!userExists) {
            response.sendRedirect("");
            return;
        }

        ServletContext servletContext = getServletContext();
        UserService userService = (UserService)servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();

        List<User> topUsers = userDAO.getAllUsers(); // currently displays top 10 users
        request.setAttribute("users", topUsers);

        SqlAuctionDAO auctionDAO = (SqlAuctionDAO)servletContext.getAttribute(SqlAuctionDAO.AUCTIONDAO_STR);
        List<Auction> auctions = auctionDAO.getAllAuctions(); // currently displays top 10 users
        request.setAttribute("auctions", topUsers);

        request.getRequestDispatcher("Pages/active-auctions.jsp").forward(request, response);
    }

}