package Servlets;

import DAO.HelperDAO;
import DAO.UserDAO;
import DAO.UserInfoDAO;
import Helper.GeneralConstants;
import Helper.Hasher;
import Models.User;
import Models.UserInfo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class CreateAccountServlet extends HttpServlet implements GeneralConstants {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Pages/create-account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection)servletContext.getAttribute(CONNECTION);
        UserDAO userDAO = (UserDAO)servletContext.getAttribute(USER_DAO);
        UserInfoDAO userInfoDAO = (UserInfoDAO)servletContext.getAttribute(USER_INFO_DAO);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("password-repeat");
        String firstName = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phonenumber");
        String note = request.getParameter("note");

        User foundUser = userDAO.getUser(username);

        if (foundUser != null) {
            // TODO: notify user on the webpage instead of STDOUT
            System.out.println("Account with the given username already exists");
            request.getRequestDispatcher("Pages/create-account.jsp").forward(request, response);
        } else if (!password.equals(repeatedPassword)) {
            // TODO: notify user on the webpage instead of STDOUT
            System.out.println("Repeated password is invalid");
            request.getRequestDispatcher("Pages/create-account.jsp").forward(request, response);
        } else {
            UserInfo userInfo = new UserInfo(firstName, lastname, email, address, phoneNumber, note);

            String passwordHash = Hasher.hash(password);
            boolean inserted = HelperDAO.insertUserWithInfo(connection, userDAO, userInfoDAO, username, passwordHash, userInfo);

            if (inserted) {
                request.getRequestDispatcher("Pages/successful-account-creation.jsp").forward(request, response);
            } else {
                // TODO: notify user on the webpage instead of STDOUT
                System.out.println("Couldn't register");
                request.getRequestDispatcher("Pages/create-account.jsp").forward(request, response);
            }
        }
    }
}
