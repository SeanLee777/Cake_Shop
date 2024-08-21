package dto;

/**
 * Data Transfer Object for Consumer entities.
 * Used to transfer consumer data between different layers of the application.
 */
public class ConsumerDTO {

    private int consumerID;
    private String address;
    private int userID;

    /**
     * Default constructor.
     */
    public ConsumerDTO() {
    }

    /**
     * Constructor to initialize all fields.
     *
     * @param consumerID The ID of the consumer.
     * @param address The address of the consumer.
     * @param userID The userID associated with the consumer.
     */
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
