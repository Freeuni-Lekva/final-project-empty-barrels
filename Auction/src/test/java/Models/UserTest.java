package Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testBasic1() {
        User user = new User(1, 2, "america", "hash", false, true, false, 15, 10, 20);

        assertEquals(1, user.getId());
        assertEquals(2, user.getUserInfoId());
        assertEquals("america", user.getUsername());
        assertEquals("hash", user.getPassword());
        assertFalse(user.getIsDealer());
        assertTrue(user.getIsAdmin());
        assertFalse(user.getIsBanned());
        assertEquals(15, user.getNumAuctionsWon());
        assertEquals(10, user.getRating());
        assertEquals(20, user.getNumReviews());
    }

    @Test
    public void testStatus1() {
        User user = new User(1, "america", "amer");
        assertEquals(User.SILVER, user.getStatus());

        user.incrementNumAuctionsWon();
        user.incrementNumAuctionsWon(5);

        assertEquals(6, user.getNumAuctionsWon());
        assertEquals(User.SILVER, user.getStatus());

        user.setNumAuctionsWon(15);
        assertEquals(User.GOLD, user.getStatus());

        user.setNumAuctionsWon(58);
        assertEquals(User.PLATINUM, user.getStatus());
    }
}
