package Models;

import java.util.Objects;

public class User {
    private int id;
    private int userInfoId;
    private String username;
    private String password;
    private boolean isDealer;
    private boolean isAdmin;
    private int numAuctionsWon;
    private int rating;
    private int numReviews;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && userInfoId == user.userInfoId && isDealer == user.isDealer && isAdmin == user.isAdmin && numAuctionsWon == user.numAuctionsWon && rating == user.rating && numReviews == user.numReviews && username.equals(user.username) && password.equals(user.password);
    }

}
