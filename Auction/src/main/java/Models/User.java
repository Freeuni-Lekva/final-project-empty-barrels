package Models;

import Helper.GeneralConstants;

import java.util.Objects;

public class User implements GeneralConstants {
    /* Constants for User status */
    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;
    public static final int STATUS_UNDEFINED = -1;

    /* Constants to determine user's status */
    private static final int AUCTIONS_NEEDED_FOR_SILVER = 0;
    private static final int AUCTIONS_NEEDED_FOR_GOLD = 10;
    private static final int AUCTIONS_NEEDED_FOR_PLATINUM = 50;

    private int id;
    private int userInfoId;
    private String username;
    private String password; // SHA-256 Hash string of user's raw password
    private boolean isDealer;
    private boolean isAdmin;
    private int numAuctionsWon;
    private int rating;
    private int numReviews;
    private int status; // SILVER, GOLD, PLATINUM

    public User(int id, int userInfoId, String username, String password,
                boolean isDealer, boolean isAdmin, int numAuctionsWon,
                int rating, int numReviews) {
        this.id = id;
        this.userInfoId = userInfoId;
        this.username = username;
        this.password = password;
        this.isDealer = isDealer;
        this.isAdmin = isAdmin;
        this.numAuctionsWon = numAuctionsWon;
        this.rating = rating;
        this.numReviews = numReviews;
        this.status = calculateStatus(numAuctionsWon);
    }

    public User(int userInfoId, String username, String password,
                boolean isDealer, boolean isAdmin, int numAuctionsWon,
                int rating, int numReviews) {
        this(NO_ID, userInfoId, username, password, isDealer, isAdmin, numAuctionsWon, rating, numReviews);
    }

    public User(int userInfoId, String username, String password) {
        this(NO_ID, userInfoId, username, password, false, false, 0, 0, 0);
    }

    public int getId() {
        return id;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsDealer() {
        return isDealer;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public int getNumAuctionsWon() {
        return numAuctionsWon;
    }

    public int getRating() {
        return rating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public int getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param password Hash value of users raw password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setNumAuctionsWon(int numAuctionsWon) {
        this.numAuctionsWon = numAuctionsWon;
        this.status = calculateStatus(numAuctionsWon);
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    /**
     * Increments numAuctionsWon by given value "increment"
     */
    public void incrementNumAuctionsWon(int increment) {
        this.numAuctionsWon += increment;
        this.status = calculateStatus(this.numAuctionsWon);
    }

    /**
     * Increments numAuctionsWon by 1
     */
    public void incrementNumAuctionsWon() {
        incrementNumAuctionsWon(1);
    }

    /**
     * Calculates user's status using number of auctions won by user
     */
    private int calculateStatus(int numAuctionsWon) {
        if (numAuctionsWon >= User.AUCTIONS_NEEDED_FOR_PLATINUM) {
            return User.PLATINUM;
        } else if (numAuctionsWon >= User.AUCTIONS_NEEDED_FOR_GOLD) {
            return User.GOLD;
        } else if (numAuctionsWon >= User.AUCTIONS_NEEDED_FOR_SILVER) {
            return User.SILVER;
        }

        return User.STATUS_UNDEFINED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && userInfoId == user.userInfoId && isDealer == user.isDealer && isAdmin == user.isAdmin && numAuctionsWon == user.numAuctionsWon && rating == user.rating && numReviews == user.numReviews && status == user.status && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }
}
