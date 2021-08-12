package Models;

import java.util.Objects;

public class User {
    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;
    public static final int STATUS_UNDEFINED = -1;

    private static final int AUCTIONS_NEEDED_FOR_SILVER = 0;
    private static final int AUCTIONS_NEEDED_FOR_GOLD = 10;
    private static final int AUCTIONS_NEEDED_FOR_PLATINUM = 50;

    private int id;
    private int userInfoId;
    private String username;
    private Password password;
    private boolean isDealer;
    private boolean isAdmin;
    private int numAuctionsWon;
    private double rating;
    private int numReviews;
    private int status; // SILVER, GOLD, PLATINUM
    private int sumReviewScores;

    public User(int id, int userInfoId, String username, Password password,
                boolean isDealer, boolean isAdmin, int numAuctionsWon,
                double rating, int numReviews) {
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
        this.sumReviewScores = 0; // might change later
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

    public Password getPassword() {
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

    public double getRating() {
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

    public void setPassword(Password password) {
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

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    // Increments numAuctionsWon by given value "increment"
    public void incrementNumAuctionsWon(int increment) {
        this.numAuctionsWon += increment;
        this.status = calculateStatus(this.numAuctionsWon);
    }

    // Increments numAuctionsWon by 1
    public void incrementNumAuctionsWon() {
        incrementNumAuctionsWon(1);
    }

    /**
     * Gets a single review score and updates rating accordingly
     * @param reviewScore score of the new review
     */
    public void addRating(int reviewScore) {
        numReviews++;
        sumReviewScores += reviewScore;

        setRating((double)sumReviewScores / numReviews);
    }

    // Calculates user's status using number of auctions won by user
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

        return id == user.id && userInfoId == user.userInfoId && isDealer == user.isDealer && isAdmin == user.isAdmin
                && numAuctionsWon == user.numAuctionsWon && rating == user.rating && numReviews == user.numReviews
                && username.equals(user.getUsername()) && password.equals(user.getPassword());
    }
}
