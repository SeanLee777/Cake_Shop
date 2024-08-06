package org.cake_shop.dto;



public class ConsumerDTO {
    private int consumerID;
    private String address;
    private String userID;

    // Constructor
    public ConsumerDTO() {}

    public ConsumerDTO(int consumerID, String address, String userID) {
        this.consumerID = consumerID;
        this.address = address;
        this.userID = userID;
    }

    // Getters and setters
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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "ConsumerDTO{" +
                "consumerID=" + consumerID +
                ", address='" + address + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}

