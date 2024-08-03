package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for PasswordHashingUtil.
 * Ensures that password hashing functionality works as expected.
 */
class PasswordHashingUtilTest {

    /**
     * Tests if hashing a standard password produces a non-null and non-empty result.
     */
    @Test
    void whenHashingPassword_thenNonNullNonEmptyResult() {
        String password = "password123";
        String hashed = PasswordHashingUtil.hashPassword(password);

        assertNotNull(hashed, "Hashed password should not be null");
        assertFalse(hashed.isEmpty(), "Hashed password should not be empty");
    }

    /**
     * Tests if hashing the same password multiple times produces consistent results.
     */
    @Test
    void whenHashingPasswordConsistently_thenSameResult() {
        String password = "consistentPassword";
        String firstHash = PasswordHashingUtil.hashPassword(password);
        String secondHash = PasswordHashingUtil.hashPassword(password);

        assertEquals(firstHash, secondHash, "Hashing the same password should produce the same result");
    }

    /**
     * Tests if hashing an empty string password produces a non-null result.
     */
    @Test
    void whenHashingEmptyPassword_thenNonNullResult() {
        String password = "";
        String hashed = PasswordHashingUtil.hashPassword(password);

        assertNotNull(hashed, "Hashing an empty password should not return null");
    }

    /**
     * Tests if hashing a password containing special characters produces a non-null result.
     */
    @Test
    void whenHashingPasswordWithSpecialCharacters_thenNonNullResult() {
        String password = "p@$$w0rd!#";
        String hashed = PasswordHashingUtil.hashPassword(password);

        assertNotNull(hashed, "Hashing a password with special characters should not return null");
    }

}
