package Models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserInfoTest {

    @Test
    public void testBasic1() {
        UserInfo userInfo = new UserInfo(1, "levan", "samadashvili", "@", "qutaisi", "555", "note");

        assertEquals(1, userInfo.getId());
        assertEquals("levan", userInfo.getFirstName());
        assertEquals("samadashvili", userInfo.getLastName());
        assertEquals("@", userInfo.getEmail());
        assertEquals("qutaisi", userInfo.getAddress());
        assertEquals("555", userInfo.getPhoneNumber());
        assertEquals("note", userInfo.getNote());
    }
}
