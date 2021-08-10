package DAO;

import Models.User;

import java.util.List;

public interface UserDAO {
    /**
     * Gets User object by its' id
     * @param id
     * @return user with the id if it exists, null otherwise
     */
    public User getUser(int id);

    /**
     * Gets User object by its' username (every username is unique)
     * @param username
     * @return user with the username if it exists, null otherwise
     */
    public User getUser(String username);

    /**
     * Inserts given user object in the store
     * @param user
     */
    public void insertUser(User user);

    /**
     * Gets every User from store
     * @return list of users, empty list if there are no users
     */
    public List<User> getAllUsers();
}
