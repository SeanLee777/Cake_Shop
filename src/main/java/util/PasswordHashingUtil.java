/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Base64;

/**
 * Utility class providing password hashing functionality.
 */
public class PasswordHashingUtil {

    /**
     * Logger for logging errors related to password hashing.
     */
	 private static final Logger logger = LogManager.getLogger(PasswordHashingUtil.class);

    /**
     * Hashes a password using the SHA-256 algorithm.
     *
     * @param password The password to be hashed.
     * @return The hashed password as a Base64 encoded string.
     * @throws RuntimeException If the SHA-256 hashing algorithm is not available.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
        	logger.error("Failed to hash password due to missing algorithm: SHA-256", e);
            throw new RuntimeException("Failed to hash password", e);
        }
    }
}
