/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package validation;

import dto.UserDTO;
import model.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides methods to validate the data of a UserDTO object.
 * Checks the validity of various user attributes such as name, email, password, user type, and phone number.
 */
public class UserValidator {
    private static final Logger logger = LogManager.getLogger(UserValidator.class);

    /**
     * Validates the entire UserDTO object.
     *
     * @param userDTO The UserDTO object to be validated.
     * @return true if all user data is valid, false if any validation fails.
     */
    public static boolean isValidUser(UserDTO userDTO) {
        boolean isValid = isValidName(userDTO.getName()) &&
                isValidEmail(userDTO.getEmail()) &&
                isValidPassword(userDTO.getPassword()) &&
                isValidUserType(userDTO.getUserType()) &&
                isValidPhoneNumber(userDTO.getPhoneNumber());

        if (!isValid) {
            logger.info("User validation failed for email: {}", userDTO.getEmail());
        }
        return isValid;
    }

    /**
     * Validates the user's name.
     *
     * @param name The name to be validated.
     * @return true if the name is not null and not empty, false otherwise.
     */
    static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Validates the user's email address.
     *
     * @param email The email to validate.
     * @return true if the email is valid, false otherwise.
     */
    static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    /**
     * Validates the user's password.
     * The password must contain at least one digit, one lowercase letter, one uppercase letter, and be at least 8 characters long.
     *
     * @param password The password to be validated.
     * @return true if the password matches the specified password regex pattern, false otherwise.
     */
    static boolean isValidPassword(String password) {
        // Password must contain at least one digit, one lowercase, one uppercase, and 8 or more characters
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return password != null && password.matches(passwordRegex);
    }

    /**
     * Validates the user type string.
     * The method checks if the provided user type string corresponds to any enum constant in the UserType enum.
     *
     * @param userTypeString The user type string to be validated.
     * @return true if the user type is valid, false otherwise.
     */
    static boolean isValidUserType(String userTypeString) {
        if (userTypeString == null) {
            logger.warn("User type validation failed: userTypeString is null");
            return false;
        }
        try {
            UserType.valueOf(userTypeString.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            logger.warn("User type validation failed: No enum constant for userTypeString - {}", userTypeString);
            return false;
        }
    }

    /**
     * Validates the user's phone number.
     * The phone number must contain only digits.
     *
     * @param phoneNumber The phone number to be validated.
     * @return true if the phone number contains only digits, false otherwise.
     */
    static boolean isValidPhoneNumber(String phoneNumber) {
        // Checking if the phone number contains only digits
        String phoneRegex = "\\d+";
        if (phoneNumber == null) {
            logger.warn("Phone number validation failed: phone number is null");
            return false;
        }
        if (!phoneNumber.matches(phoneRegex)) {
            logger.warn("Phone number validation failed for '{}': must contain only digits", phoneNumber);
            return false;
        }
        return true;
    }
}