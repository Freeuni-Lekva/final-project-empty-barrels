package DAO;

import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqlUserDAO implements UserDAO {
    private Connection connection;

    public SqlUserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getUser(int id) {
        User result = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User" +
                                                                 " WHERE ID=?;");

            stmt.setString(1, String.valueOf(id));
            ResultSet resultSet = stmt.executeQuery();
            result =

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    /**
     * Converts resultSet into User object
     * @param resultSet a single row from the User table
     * @return User object corresponding to the given row
     */
    private User convertToUser(ResultSet resultSet) {
        
        try {
            User result = new User(resultSet.getString(1), )
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
