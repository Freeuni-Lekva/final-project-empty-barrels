package Servlets;

import DAO.UserDAO;
import DAO.UserInfoDAO;
import Helper.SessionHelper;
import Models.UserInfo;
import Services.UserService;
import Helper.GeneralConstants;
import Helper.Hasher;
import Models.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet implements GeneralConstants {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean userExists = SessionHelper.checkIfUserExists(session);

        if (!userExists) {
            response.sendRedirect("");
            return;
        }

        request.getRequestDispatcher("Pages/account-home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String passwordHash = Hasher.hash(password);

        ServletContext servletContext = getServletContext();
        UserService userService = (UserService)servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();
        UserInfoDAO userInfoDAO = userService.getUserInfoDAO();
        User foundUser = userDAO.getUser(username);

        if (foundUser == null) {
            // TODO: Notify user on the webpage
            System.out.println("User doesn't exist");
            response.sendRedirect("");
        } else if (!passwordHash.equals(foundUser.getPassword())) {
            // TODO: Notify user on the webpage
            System.out.println("Invalid password");
            response.sendRedirect("");
        } else {
            // Successful login
            int userInfoId = foundUser.getUserInfoId();
            UserInfo foundUserInfo = userInfoDAO.getUserInfo(userInfoId);

            HttpSession session = request.getSession();
            session.setAttribute(CURRENT_USER_STRING, foundUser);
            session.setAttribute(CURRENT_USER_INFO_STRING, foundUserInfo);

            response.sendRedirect("/account-home");
        }
    }
}
