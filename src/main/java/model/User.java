/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: Shanghao Li
 * Student ID: 040903008
 */

package model;

/**
 * Represents a user in the system with various attributes like name, email, etc.
 */
public class User {
    /**
     * Unique identifier for the user.
     */
    private int userID;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * The type of the user (e.g., retailer, consumer, charitable organization).
     */
    private UserType userType;

    /**
     * The city where the user resides.
     */
    private String city;

    /**
     * The postal code of the user's location.
     */
    private String postalCode;

    /**
     * The phone number of the user.
     */
    private String phoneNumber;



    /**
     * Constructor to create a new User.
     *
     * @param name The name of the user.
     * @param email The email address of the user.
     * @param password The password of the user.
     * @param userType The type of the user (e.g., retailer, consumer, charitable organization).
     * @param city The city where the user resides.
     * @param postalCode The postal code of the user's location.
     * @param phoneNumber The phone number of the user.
     */
    public User(String name, String email, String password, UserType userType, String city, String postalCode, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
    }

    public User() {

    }

    // Getters and setters
    // Getters and Setters

    /**
     * Gets the city of the user.
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the user.
     * @param city The city to set.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the postal code of the user.
     * @return The postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the postal code of the user.
     * @param postalCode The postal code to set.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets the phone number of the user.
     * @return The phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     * @param phoneNumber The phone number to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the list of food preferences of the user.
     * @return The list of food preferences.
     */

    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user ID.
     * @param userID The user ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the name of the user.
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the user.
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the type of the user.
     * @return The user type.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the type of the user.
     * @param userType The user type to set.
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
