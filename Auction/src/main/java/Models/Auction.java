package Models;

import java.util.Date;

public class Auction {
    private int id;
    private int seller_id;
    private int item_id;
    private int current_bidder_id;
    private int starting_price;
    private int min_increment;
    private int current_price;
    private Date end_date;

    public Auction(int id, int seller_id, int item_id, int current_bidder_id, int starting_price, int min_increment, int current_price, Date end_date) {
        this.id = id;
        this.seller_id = seller_id;
        this.item_id = item_id;
        this.current_bidder_id = current_bidder_id;
        this.starting_price = starting_price;
        this.min_increment = min_increment;
        this.current_price = current_price;
        this.end_date = end_date;
    }

    public int getId() {
        return id;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public int getCurrent_bidder_id() {
        return current_bidder_id;
    }

    public int getStarting_price() {
        return starting_price;
    }

    public int getMin_increment() {
        return min_increment;
    }

    public int getCurrent_price() {
        return current_price;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setCurrent_bidder_id(int current_bidder_id) {
        this.current_bidder_id = current_bidder_id;
    }

    public void setStarting_price(int starting_price) {
        this.starting_price = starting_price;
    }

    public void setMin_increment(int min_increment) {
        this.min_increment = min_increment;
    }

    public void setCurrent_price(int current_price) {
        this.current_price = current_price;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;

        return id == auction.getId();
    }
}
