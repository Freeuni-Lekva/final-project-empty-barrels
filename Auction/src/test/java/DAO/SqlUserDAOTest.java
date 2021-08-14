package DAO;

import Helper.SQLPK;
import Models.User;
import Models.UserInfo;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/* TABLES SHOULD BE EMPTY WHEN RUNNING THESE TESTS
*  Or tests will empty them by themselves */
public class SqlUserDAOTest {
    private SqlUserDAO sqlUserDAO;
    private UserInfoDAO userInfoDAO;
    private Connection connection;

    public SqlUserDAOTest() {
        ResourceBundle reader = null;

        try {
            // Reads resources from dbconfig.properties file located
            // under src/main/resources/
            reader = ResourceBundle.getBundle("dbconfig");

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setUrl(reader.getString("db.url"));
            dataSource.setUsername(reader.getString("db.username"));
            dataSource.setPassword(reader.getString("db.password"));

            connection = dataSource.getConnection();

            sqlUserDAO = new SqlUserDAO(connection);
            userInfoDAO = new SqlUserInfoDAO(connection);

            sqlUserDAO.deleteEverything();
            userInfoDAO.deleteEverything();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
        UserInfo userInfo = new UserInfo("levani", "samadashvili", "lsama19@freeuni.edu.ge", "tbilisi", "579204205", "note");
        userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        User user = new User(lastId, "skunsy", "hash", false, true, true, 10, 20, 30);
        boolean inserted = sqlUserDAO.insertUser(user);
        assertTrue(inserted);

        inserted = sqlUserDAO.insertUser(user);
        assertFalse(inserted);

        int expectedId = SQLPK.getLastPrimaryKey(connection);

        User gottenUserById = sqlUserDAO.getUser(expectedId);
        User gottenUserByUsername = sqlUserDAO.getUser("skunsy");

        assertEquals(gottenUserById, gottenUserByUsername);

        assertEquals(expectedId, gottenUserById.getId());
        assertEquals(lastId, gottenUserById.getUserInfoId());
        assertEquals("skunsy", gottenUserById.getUsername());
        assertEquals("hash", gottenUserById.getPassword());
        assertFalse(gottenUserById.getIsDealer());
        assertTrue(gottenUserById.getIsAdmin());
        assertTrue(gottenUserById.getIsBanned());
        assertEquals(10, gottenUserById.getNumAuctionsWon());
        assertEquals(20, gottenUserById.getRating(), 0.0);
        assertEquals(30, gottenUserById.getNumReviews());

        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
    }

    @Test
    public void test2() {
        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
        UserInfo userInfo = new UserInfo("LeVANI", "sAMaDaShvilI", "lsaMA19@fREeuni.edu.ge", "tbilISI", "5a04205", "NOTENOTE");
        userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        User user = new User(lastId, "SKunSY", "haSH235", false, true, true, 10, 20, 30);
        boolean inserted = sqlUserDAO.insertUser(user);
        assertTrue(inserted);

        inserted = sqlUserDAO.insertUser(user);
        assertFalse(inserted);

        int expectedId = SQLPK.getLastPrimaryKey(connection);

        User gottenUserById = sqlUserDAO.getUser(expectedId);
        User gottenUserByUsername = sqlUserDAO.getUser("SKunSY");
        User invalidUser = sqlUserDAO.getUser("skunsy");
        User invalidUser2 = sqlUserDAO.getUser("Skunsy");
        User invalidUser3 = sqlUserDAO.getUser(-1);

        assertNull(invalidUser);
        assertNull(invalidUser2);
        assertNull(invalidUser3);

        assertEquals(gottenUserById, gottenUserByUsername);

        assertEquals(expectedId, gottenUserById.getId());
        assertEquals(lastId, gottenUserById.getUserInfoId());
        assertEquals("SKunSY", gottenUserById.getUsername());
        assertEquals("haSH235", gottenUserById.getPassword());
        assertFalse(gottenUserById.getIsDealer());
        assertTrue(gottenUserById.getIsAdmin());
        assertTrue(gottenUserById.getIsBanned());
        assertEquals(10, gottenUserById.getNumAuctionsWon());
        assertEquals(20, gottenUserById.getRating(), 0.0);
        assertEquals(30, gottenUserById.getNumReviews());

        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
    }

    @Test
    public void test3() {
        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();

        UserInfo userInfo = new UserInfo("levani", "samadashvili", "lsama19@freeuni.edu.ge", "tbilisi", "579204205", "note");
        userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        User user1 = new User(lastId, "skunsy", "hash", false, true, true, 10, 20, 30);
        boolean inserted = sqlUserDAO.insertUser(user1);
        assertTrue(inserted);

        int lastUserId = SQLPK.getLastPrimaryKey(connection);
        user1.setId(lastUserId);

        User user2 = new User(lastId, "NIka", "MErabi");
        inserted = sqlUserDAO.insertUser(user2);
        assertTrue(inserted);

        lastUserId = SQLPK.getLastPrimaryKey(connection);
        user2.setId(lastUserId);

        User user3 = new User(lastId, "StillResisting", "chAnge");
        inserted = sqlUserDAO.insertUser(user3);
        assertTrue(inserted);

        lastUserId = SQLPK.getLastPrimaryKey(connection);
        user3.setId(lastUserId);

        List<User> userList = sqlUserDAO.getAllUsers();
        assertEquals(3, userList.size());
        assertTrue(userList.contains(user1));
        assertTrue(userList.contains(user2));
        assertTrue(userList.contains(user3));

        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
    }

    @Test
    public void test4() {
        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();

        UserInfo userInfo = new UserInfo("LeVANI", "sAMaDaShvilI", "lsaMA19@fREeuni.edu.ge", "tbilISI", "5a04205", "NOTENOTE");
        userInfoDAO.insertUserInfo(userInfo);
        int lastId = SQLPK.getLastPrimaryKey(connection);

        User user = new User(lastId, "SKunSY", "haSH235", false, true, true, 10, 20, 30);
        boolean inserted = sqlUserDAO.insertUser(user);
        assertTrue(inserted);

        int insertedUserId = SQLPK.getLastPrimaryKey(connection);

        boolean removed = sqlUserDAO.removeUser("skunsy");
        assertFalse(removed);

        removed = sqlUserDAO.removeUser(insertedUserId + 1);
        assertFalse(removed);

        removed = sqlUserDAO.removeUser("SKunSY");
        assertTrue(removed);

        inserted = sqlUserDAO.insertUser(user);
        assertTrue(inserted);

        insertedUserId = SQLPK.getLastPrimaryKey(connection);

        removed = sqlUserDAO.removeUser(insertedUserId);
        assertTrue(removed);

        sqlUserDAO.deleteEverything();
        userInfoDAO.deleteEverything();
    }

    @Test
    public void test5() {
        
    }
}
