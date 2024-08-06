package org.cake_shop.dto;

public class ConsumerDTO {
    private int consumerID;
    private String address;
    private int userID; // Changed to int for consistency with the rest of the application

    // Constructors
    public ConsumerDTO() {}

    public ConsumerDTO(int consumerID, String address, int userID) {
        this.consumerID = consumerID;
        this.address = address;
        this.userID = userID;
    }

    // Getters and Setters
    public int getConsumerID() {
        return consumerID;
    }

    public void setConsumerID(int consumerID) {
        this.consumerID = consumerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "ConsumerDTO{" +
                "consumerID=" + consumerID +
                ", address='" + address + '\'' +
                ", userID=" + userID +
                '}';
    }
}
