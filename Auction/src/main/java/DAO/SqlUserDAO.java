package DAO;

import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDAO implements UserDAO {
    private Connection connection;

    public SqlUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUser(int id) {
        User user = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User" +
                                                                 " WHERE ID=?;");

            stmt.setString(1, String.valueOf(id));
            ResultSet resultSet = stmt.executeQuery();
            user = convertToUser(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUser(String username) {
        User user = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User" +
                                                                 " WHERE user_name=?;");

            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            user = convertToUser(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User;");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                userList.add(convertToUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;
    }

    /**
     * Converts resultSet into User object
     * @param resultSet a single row from the User table
     * @return User object corresponding to the given row
     */
    private User convertToUser(ResultSet resultSet) {
        User result = null;

        try {
            result = new User(resultSet.getInt(1), resultSet.getInt(2),
                              resultSet.getString(3), resultSet.getString(4),
                              resultSet.getBoolean(5), resultSet.getBoolean(6),
                              resultSet.getInt(7), resultSet.getInt(8),
                              resultSet.getInt(9));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
