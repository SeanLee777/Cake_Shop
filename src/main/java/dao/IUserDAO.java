/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package dao;

import model.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Interface defining the data access operations for User objects.
 * This interface provides methods for creating, updating, deleting, and querying user data.
 */
public interface IUserDAO {

    /**
     * Creates a new user in the database.
     *
     * @param user The User object to be created.
     * @return true if the user is successfully created, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean createUser(User user) throws SQLException;

    /**
     * Retrieves a user by their email.
     *
     * @param email The email address of the user to be retrieved.
     * @return An Optional containing the User if found, or an empty Optional if no user is found.
     * @throws SQLException if a database access error occurs.
     */
    Optional<User> findByEmail(String email) throws SQLException;

    /**
     * Updates the details of an existing user in the database.
     *
     * @param user The User object containing the updated details.
     * @return true if the update is successful, false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    boolean updateUser(User user) throws SQLException;

    /**
     * Deletes a user from the database.
     *
     * @param userID The ID of the user to be deleted.
     * @throws SQLException if a database access error occurs.
     */
    void deleteUser(int userID) throws SQLException;
}
