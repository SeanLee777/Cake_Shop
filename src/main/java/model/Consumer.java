package model;

/**
 * Model class representing a Consumer entity in the system.
 */
public class Consumer {

    private int consumerID;
    private String address;
    private int userID;

    /**
     * Default constructor.
     */
    public Consumer() {
    }

    /**
     * Constructor to initialize all fields.
     *
     * @param consumerID The ID of the consumer.
     * @param address The address of the consumer.
     * @param userID The userID associated with the consumer.
     */
    public Consumer(int consumerID, String address, int userID) {
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
        return "Consumer{" +
                "consumerID=" + consumerID +
                ", address='" + address + '\'' +
                ", userID=" + userID +
                '}';
    }
}
