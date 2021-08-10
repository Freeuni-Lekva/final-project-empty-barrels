package DAO;

import Models.User;

import java.util.List;

public interface UserDAO {
    /**
     * Gets User object by its' id
     * @param id
     * @return
     */
    public User getUser(int id);

    /**
     * Gets User object by its' username (every username is unique)
     * @param username
     * @return
     */
    public User getUser(String username);

    /**
     * Inserts given user object in the store
     * @param user
     */
    public void insertUser(User user);

    /**
     * Gets every User from store
     * @return
     */
    public List<User> getAllUsers();
}
