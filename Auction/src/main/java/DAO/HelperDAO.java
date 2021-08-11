package DAO;

import Helper.SQLPK;
import Models.User;
import Models.UserInfo;

import java.sql.Connection;

public class HelperDAO {
    /**
     * Inserts UserInfo object in UserInfo table and corresponding User object in User table
     * @param connection sql connection
     * @param userDAO
     * @param userInfoDAO
     * @param username username
     * @param password password hash
     * @param userInfo UserInfo object (probably with NO_ID)
     * @return true if user was inserted, false otherwise
     */
    public static boolean insertUserWithInfo(Connection connection, UserDAO userDAO, UserInfoDAO userInfoDAO,
                                             String username, String password, UserInfo userInfo) {
        boolean userInfoInserted = userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        if (!userInfoInserted) {
            userInfoDAO.removeUserInfo(lastId);
            return false;
        }

        User newUser = new User(lastId, username, password);
        boolean userInserted = userDAO.insertUser(newUser);

        if (!userInserted) {
            userInfoDAO.removeUserInfo(lastId);
            return false;
        }

        return true;
    }
}
