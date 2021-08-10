package DAO;

import Models.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlUserInfoDAO implements UserInfoDAO {
    private Connection connection;

    public SqlUserInfoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserInfo getUserInfo(int id) {
        UserInfo userInfo = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserInfo" +
                                                                 " WHERE ID=?;");
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            userInfo = convertToUserInfo(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userInfo;
    }

    @Override
    public List<UserInfo> getAllUserInfos() {
        List<UserInfo> userInfoList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM UserInfo;");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                userInfoList.add(convertToUserInfo(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userInfoList;
    }

    @Override
    public void insertUserInfo(UserInfo userInfo) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO UserInfo " +
                    "(first_name, last_name, email, address, phone_number, note)" +
                    "VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, userInfo.getFirstName());
            stmt.setString(2, userInfo.getLastName());
            stmt.setString(3, userInfo.getEmail());
            stmt.setString(4, userInfo.getAddress());
            stmt.setString(5, userInfo.getPhoneNumber());
            stmt.setString(6, userInfo.getNote());
            int numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected != 1) {
                throw new SQLException();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Converts resultSet into UserInfo object
     * @param resultSet single row of UserInfo table
     * @return corresponding UserInfo object
     */
    private UserInfo convertToUserInfo(ResultSet resultSet) {
        UserInfo userInfo = null;

        try {
            userInfo = new UserInfo(resultSet.getInt(1), resultSet.getString(2),
                                    resultSet.getString(3), resultSet.getString(4),
                                    resultSet.getString(5), resultSet.getString(6),
                                    resultSet.getString(7));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userInfo;
    }
}
