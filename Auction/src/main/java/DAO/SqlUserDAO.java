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
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users" +
                                                                 " WHERE ID=?;");

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                user = convertToUser(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public User getUser(String username) {
        User user = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users" +
                                                                 " WHERE user_name=?;");

            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                user = convertToUser(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users;");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                userList.add(convertToUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;
    }

    @Override
    public boolean insertUser(User user) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users" +
                    "(user_info_ID, user_name, password, is_dealer, is_admin, auctions_won," +
                    "rating, num_reviews)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, user.getUserInfoId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setBoolean(4, user.getIsDealer());
            stmt.setBoolean(5, user.getIsAdmin());
            stmt.setInt(6, user.getNumAuctionsWon());
            stmt.setInt(7, user.getRating());
            stmt.setInt(8, user.getNumReviews());
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeUser(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Users" +
                                                                 " WHERE ID=?;");
            stmt.setInt(1, id);
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean removeUser(String username) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Users" +
                    " WHERE user_name=?;");
            stmt.setString(1, username);
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
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
