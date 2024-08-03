package validation;

import dto.UserDTO;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for UserValidator.
 * This class tests various validation methods used for user input validation.
 */
class UserValidatorTest {

    /**
     * Tests if a valid name is accepted by the validator.
     */
    @Test
    void whenNameIsValid_thenTrue() {
        assertTrue(UserValidator.isValidName("John Doe"));
    }

    /**
     * Tests if a null name is correctly identified as invalid.
     */
    @Test
    void whenNameIsNull_thenFalse() {
        assertFalse(UserValidator.isValidName(null));
    }

    /**
     * Tests if an empty name is correctly identified as invalid.
     */
    @Test
    void whenNameIsEmpty_thenFalse() {
        assertFalse(UserValidator.isValidName(""));
    }

    /**
     * Tests if a single character name is correctly identified as valid.
     */
    @Test
    void whenNameIsOneChar_thenTrue() {
        assertTrue(UserValidator.isValidName("A"));
    }

    /**
     * Tests if a very long name is correctly identified as valid.
     */
    @Test
    void whenNameIsVeryLong_thenTrue() {
        String longName = "A".repeat(100);
        assertTrue(UserValidator.isValidName(longName));
    }

    /**
     * Tests if a name with special characters is correctly identified as valid.
     */
    @Test
    void whenNameContainsSpecialCharacters_thenTrue() {
        assertTrue(UserValidator.isValidName("Anne-Marie O'Neill"));
    }

    /**
     * Tests if a valid email address is accepted by the validator.
     */
    @Test
    void whenEmailIsValid_thenTrue() {
        assertTrue(UserValidator.isValidEmail("name@example.com"));
    }

    /**
     * Tests if an invalid email address is correctly identified as invalid.
     */
    @Test
    void whenEmailIsInvalid_thenFalse() {
        assertFalse(UserValidator.isValidEmail("name@example"));
    }

    /**
     * Tests if a long email address is correctly identified as valid.
     */
    @Test
    void whenEmailIsLongButValid_thenTrue() {
        String localPart = "a".repeat(64);
        String longEmail = localPart + "@example.com";
        assertTrue(UserValidator.isValidEmail(longEmail));
    }

    /**
     * Tests if an email address with a subdomain is correctly identified as valid.
     */
    @Test
    void whenEmailHasSubdomain_thenTrue() {
        assertTrue(UserValidator.isValidEmail("name@sub.example.com"));
    }

    /**
     * Tests if a valid password is accepted by the validator.
     */
    @Test
    void whenPasswordIsValid_thenTrue() {
        assertTrue(UserValidator.isValidPassword("P@ssw0rd"));
    }

    /**
     * Tests if a password that is too short is correctly identified as invalid.
     */
    @Test
    void whenPasswordIsTooShort_thenFalse() {
        assertFalse(UserValidator.isValidPassword("P@ss1"));
    }

    /**
     * Tests if a password lacking a digit is correctly identified as invalid.
     */
    @Test
    void whenPasswordLacksDigit_thenFalse() {
        assertFalse(UserValidator.isValidPassword("Password!"));
    }

    /**
     * Tests if a password lacking an uppercase letter is correctly identified as invalid.
     */
    @Test
    void whenPasswordLacksUppercase_thenFalse() {
        assertFalse(UserValidator.isValidPassword("p@ssw0rd"));
    }

    /**
     * Tests if a password lacking a lowercase letter is correctly identified as invalid.
     */
    @Test
    void whenPasswordLacksLowercase_thenFalse() {
        assertFalse(UserValidator.isValidPassword("P@SSW0RD"));
    }

    /**
     * Tests if a password that meets the minimum length requirement is identified as valid.
     */
    @Test
    void whenPasswordIsJustLongEnough_thenTrue() {
        assertTrue(UserValidator.isValidPassword("Pass1wrd"));
    }

    /**
     * Tests if a very long password is correctly identified as valid.
     */
    @Test
    void whenPasswordIsVeryLong_thenTrue() {
        String longPassword = "P@ss" + "w0rd".repeat(10);
        assertTrue(UserValidator.isValidPassword(longPassword));
    }

    /**
     * Tests if a valid user type string is correctly identified as valid.
     */
    @Test
    void whenUserTypeIsValid_thenTrue() {
        assertTrue(UserValidator.isValidUserType("CONSUMER"));
    }

    /**
     * Tests if an invalid user type string is correctly identified as invalid.
     */
    @Test
    void whenUserTypeIsInvalid_thenFalse() {
        assertFalse(UserValidator.isValidUserType("UNKNOWN"));
    }

    /**
     * Tests if a null user type string is correctly identified as invalid.
     */
    @Test
    void whenUserTypeIsNull_thenFalse() {
        assertFalse(UserValidator.isValidUserType(null));
    }

    /**
     * Tests if a user type string with different case sensitivity is correctly identified as valid.
     */
    @Test
    void whenUserTypeHasDifferentCase_thenTrue() {
        assertTrue(UserValidator.isValidUserType("consumer"));
    }

    /**
     * Tests if a valid phone number is accepted by the validator.
     */
    @Test
    void whenPhoneNumberIsValid_thenTrue() {
        assertTrue(UserValidator.isValidPhoneNumber("1234567890"));
    }

    /**
     * Tests if a phone number containing letters is correctly identified as invalid.
     */
    @Test
    void whenPhoneNumberHasLetters_thenFalse() {
        assertFalse(UserValidator.isValidPhoneNumber("1234abc890"));
    }

    /**
     * Tests if a null phone number is correctly identified as invalid.
     */
    @Test
    void whenPhoneNumberIsNull_thenFalse() {
        assertFalse(UserValidator.isValidPhoneNumber(null));
    }

    /**
     * Tests if a fully valid UserDTO object passes all validation checks.
     */
    @Test
    void whenUserDTOIsValid_thenTrue() {
        List<String> foodPreferences = Arrays.asList("Vegetarian", "Gluten-Free");
        UserDTO userDTO = new UserDTO(
                "John Doe",             // Name
                "john@example.com",     // Email
                "P@ssw0rd",             // Password
                "CONSUMER",             // UserType
                "SomeCity",             // City
                "12345",                // PostalCode
                "1234567890"           // PhoneNumber
        );
        assertTrue(UserValidator.isValidUser(userDTO));
    }
}
