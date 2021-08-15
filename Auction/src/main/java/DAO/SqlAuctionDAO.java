package DAO;

import Models.Auction;
import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlAuctionDAO implements AuctionDAOInterface {
    public final static String AUCTIONDAO_STR = "AUCTION_DAO_STR_UNIQUE";
    private Connection connection;

    public SqlAuctionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Auction getAuction(int id) {
        Auction auction = null;

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Auctions" +
                    " WHERE ID=?;");

            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                auction = convertToAuction(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return auction;
    }

    @Override
    public boolean insertAuction(Auction auction) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Auctions" +
                    "(ID, seller_ID, item_ID, current_bidder_ID, starting_price, min_increment, end_time, current_price)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, auction.getId());
            stmt.setInt(2, auction.getSeller_id());
            stmt.setInt(3, auction.getItem_id());
            stmt.setInt(4, auction.getCurrent_bidder_id());
            stmt.setInt(5, auction.getStarting_price());
            stmt.setInt(6, auction.getMin_increment());
            stmt.setDate(7, (Date) auction.getEnd_date());
            stmt.setInt(8, auction.getCurrent_price());
            int numRowsAffected = stmt.executeUpdate();

            return numRowsAffected == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeAuction(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM Auctions" +
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
    public List<Auction> getAllAuctions() {
        List<Auction> auctionsList = new ArrayList<>();

        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Auctions;");
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                auctionsList.add(convertToAuction(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return auctionsList;
    }

    /**
     * Converts resultSet into Auction object
     * @param resultSet a single row from the User table
     * @return User object corresponding to the given row
     */
    private Auction convertToAuction(ResultSet resultSet) {
        Auction result = null;

        try {
            result = new Auction(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getInt(4),
                    resultSet.getInt(5), resultSet.getInt(6),
                    resultSet.getInt(8), resultSet.getDate(7));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
