package Servlets;

import DAO.UserDAO;
import Helper.SessionHelper;
import Models.User;
import Models.UserInfo;
import Services.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static Helper.GeneralConstants.*;

@WebServlet(name = "BanUserServlet", urlPatterns = {"/BanUserServlet"})
public class BanUserServlet extends HttpServlet {

    public BanUserServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean userExists = SessionHelper.checkIfUserExists(session);
        User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);

        if (!userExists || !currentUser.getIsAdmin()) {
            response.sendRedirect("");
            return;
        }
        ServletContext servletContext = getServletContext();
        UserService userService = (UserService)servletContext.getAttribute(USER_SERVICE);
        UserDAO userDAO = userService.getUserDAO();

        userDAO.banUser(request.getParameter("userToBan"));

        response.sendRedirect(request.getContextPath() + "/allusers");
    }
}
