package Servlets;

import DAO.ReviewsDAO;
import DAO.SqlReviewsDao;
import DAO.UserDAO;
import Helper.Hasher;
import Models.Review;
import Models.User;
import Models.UserInfo;
import Services.UserService;
import org.apache.commons.dbcp.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

import static Helper.GeneralConstants.*;

public class WriteReviewServlet extends HttpServlet {

    public WriteReviewServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        UserService userService = (UserService)servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();
        SqlReviewsDao reviewsDao = (SqlReviewsDao) servletContext.getAttribute(SqlReviewsDao.ATTRIBUTE_NAME);

        HttpSession session = request.getSession();
        User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
        UserInfo currentUserInfo = (UserInfo)session.getAttribute(CURRENT_USER_INFO_STRING);

        String username = request.getParameter("username");
        String parse = request.getParameter("point");
        if (parse == ""){

        }
        int score = 0;
        if (parse != ""){
            score = Integer.parseInt(parse);
        }
        if (score > 5){
            score = 5;
        }else if (score < 0){
            score = 0;
        }

        String reviewComment = request.getParameter("comment");
        int reviewerId = -1;

        if (currentUser != null){
            reviewerId = currentUser.getId();
        }

        User foundUser = userDAO.getUser(username);
        if (foundUser != null){
            Review review = new Review(reviewerId, foundUser.getId(), score, reviewComment);
            reviewsDao.insertReview(review);
            response.sendRedirect(request.getContextPath() + "/account-home");
        }else{
            request.setAttribute(MESSAGE_STRING, "User with given username does not exist.");
            request.getRequestDispatcher("Pages/write-review.jsp").forward(request, response);
        }
    }
}
