package org.cake_shop.model;


public class Consumer {
    private int consumerID;
    private String address;
    private int userID;

    public Consumer() {
    }

    public Consumer(int consumerID, String address, int userID) {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
