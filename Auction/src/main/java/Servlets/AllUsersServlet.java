package Servlets;


import Helper.SessionHelper;
import Models.User;
import Models.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static Helper.GeneralConstants.CURRENT_USER_INFO_STRING;
import static Helper.GeneralConstants.CURRENT_USER_STRING;

@WebServlet(name = "AllUsersServlet", urlPatterns = {"/allusers"})
public class AllUsersServlet extends HttpServlet {
    public AllUsersServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        boolean userExists = SessionHelper.checkIfUserExists(session);
        User currentUser = (User)session.getAttribute(CURRENT_USER_STRING);
        UserInfo currentUserInfo = (UserInfo)session.getAttribute(CURRENT_USER_INFO_STRING);

        if (!userExists || !currentUser.getIsAdmin()) {
            response.sendRedirect("");
            return;
        }
        request.getRequestDispatcher("Pages/all-users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
//        super.doPost(httpServletRequest, httpServletResponse);
    }
}
