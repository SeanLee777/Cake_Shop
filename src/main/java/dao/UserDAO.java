/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package dao;

import model.User;
import model.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DatabaseUtil;

import java.sql.*;
import java.util.Optional;


/**
 * Implementation of the IUserDAO interface for accessing and manipulating user data in the database.
 * Provides concrete methods for creating, (updating, deleting), and finding users.
 */
public class UserDAO implements IUserDAO {

    /**
     * Logger for logging information, warnings, and errors.
     */
    private static final Logger logger = LogManager.getLogger(UserDAO.class);

    /**
     * Creates a new user in the database.
     *
     * @param user The User object to be saved.
     * @return boolean indicating if the user was successfully created.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public boolean createUser(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;

        String insertUserSQL = "INSERT INTO Users (name, email, password, userType, city, postalCode, phoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertPreferenceSQL = "INSERT INTO UserFoodPreferences (userID, preferenceID) VALUES (?, ?)";

        try {
            conn = DatabaseUtil.getInstance().getConnection();
            conn.setAutoCommit(false); // Starting transaction

            // Inserting user
            stmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getUserType().toString());
            stmt.setString(5, user.getCity());
            stmt.setString(6, user.getPostalCode());
            stmt.setString(7, user.getPhoneNumber());

            int userRowCount = stmt.executeUpdate();

            if (userRowCount > 0) {
                logger.info("User created successfully: {}", user.getEmail());
                // Get generated user ID
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    user.setUserID(userId); // Setting user ID in the User object


                }
                conn.commit(); // Commiting transaction
                return true;
            } else {
                logger.warn("Failed to create user: {}", user.getEmail());
                conn.rollback(); // Rolling back transaction
                return false;
            }
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rolling back transaction on error
            }
            logger.error("SQLException in createUser for email: {}", user.getEmail(), e);
            throw e;
        } finally {
            if (generatedKeys != null) generatedKeys.close();
            if (stmt != null) stmt.close();
            if (conn != null) {
                conn.setAutoCommit(true); // Reseting auto-commit
                conn.close();
            }
        }
    }


    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user to find.
     * @return An Optional containing the User if found.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String userEmail = rs.getString("email");
                    String password = rs.getString("password");
                    UserType userType = UserType.getByString(rs.getString("userType"));
                    String city = rs.getString("city");
                    String postalCode = rs.getString("postalCode");
                    String phoneNumber = rs.getString("phoneNumber");

                    User user = new User(name, userEmail, password, userType, city, postalCode, phoneNumber);
                    user.setUserID(rs.getInt("userID"));

                    logger.info("User found by email: {}", email);
                    return Optional.of(user);
                } else {
                    logger.info("No user found by email: {}", email);
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException in findByEmail for email: {}", email, e);
            throw e;
        }
    }


    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET name = ?, email = ?, password = ?, userType = ? WHERE userID = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getUserType().toString());
            stmt.setInt(5, user.getUserID());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("User updated successfully: {}", user.getEmail());
                return true;
            } else {
                logger.warn("Failed to update user: {}", user.getEmail());
                return false;
            }
        } catch (SQLException e) {
            logger.error("SQLException in createUser for email: {}", user.getEmail(), e);
            throw e;
        }
    }

    /**
     * Deletes a user from the database.
     *
     * @param userID The ID of the user to be deleted.
     * @throws SQLException if a database access error occurs.
     */
    @Override
    public void deleteUser(int userID) throws SQLException {
        String sql = "DELETE FROM Users WHERE userID = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userID);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                logger.info("Successfully deleted user with userID: {}", userID);
            } else {
                logger.warn("No user found with userID: {}, nothing to delete.", userID);
            }
        } catch (SQLException e) {
            logger.error("SQLException in deleteUser for userID: {}", userID, e);
            throw e; // Re throwing
        }
    }

}
