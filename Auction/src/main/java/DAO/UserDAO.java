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
     * Gets every User from store
     * @return
     */
    public List<User> getAllUsers();
}
