/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Enumeration of user types in the system.
 * Represents different roles a user can have, such as Retailer, Consumer, or Charitable Organization.
 */
public enum UserType {
    RETAILER("Retailer"),
    CONSUMERS("Consumers"),
    SUPPLER("Suppler");

    /**
     * Logger for logging related information and errors.
     */
    private static final Logger logger = LogManager.getLogger(UserType.class);

    /**
     * The string representation of the user type.
     */
    private final String userTypeString;

    /**
     * Constructor to assign a string representation to the enum constant.
     *
     * @param userTypeString The string representation of the user type.
     */
    UserType(String userTypeString) {
        this.userTypeString = userTypeString;
    }

    /**
     * Retrieves the enum constant corresponding to the provided string representation.
     *
     * @param userTypeString The string representation of the user type.
     * @return The corresponding UserType enum constant.
     * @throws IllegalArgumentException if no matching enum constant is found.
     */
    public static UserType getByString(String userTypeString) {
        for (UserType userType : UserType.values()) {
            if (userType.userTypeString.equalsIgnoreCase(userTypeString)) {
                return userType;
            }
        }
        logger.error("No enum constant found for userTypeString: {}", userTypeString);
        throw new IllegalArgumentException("No enum constant found for " + userTypeString);
    }

    /**
     * Returns the string representation of the user type.
     *
     * @return The string representation of the user type.
     */
    @Override
    public String toString() {
        return this.userTypeString;
    }
}
