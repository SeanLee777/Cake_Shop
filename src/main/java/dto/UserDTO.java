/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package dto;

public class UserDTO {
    /**
     * Represents the name of the user.
     */
    private String name;

    /**
     * Represents the email of the user.
     */
    private String email;

    /**
     * Represents the password of the user.
     */
    private String password;

    /**
     * Represents the type of the user (e.g., retailer, consumer).
     */
    private String userType;

    /**
     * Represents the city of the user.
     */
    private final String city;

    /**
     * Represents the postal code of the user's location.
     */
    private final String postalCode;

    /**
     * Represents the phone number of the user.
     */
    private final String phoneNumber;

    /**
     * Represents a boolean indicating if the user is subscribed to alerts.
     */

    /**
     * Constructor to create a UserDTO object.
     *
     * @param name                 The name of the user.
     * @param email                The email address of the user.
     * @param password             The password of the user.
     * @param userType             The type of the user (e.g., retailer, consumer).
     * @param city                 The city of the user.
     * @param postalCode           The postal code of the user's location.
     * @param phoneNumber          The phone number of the user.
     */
    public UserDTO(String name, String email, String password, String userType, String city, String postalCode, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the name of the user.
     *
     * @return A string representing the user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name A string containing the user's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the user.
     *
     * @return A string representing the user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email A string containing the user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return A string representing the user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password A string containing the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the type of the user.
     *
     * @return A string representing the user's type.
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the type of the user.
     *
     * @param userType A string containing the user's type.
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
