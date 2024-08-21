package dto;

/**
 * Data Transfer Object for Order entities.
 * Used to transfer order data between different layers of the application.
 */
public class OrderDTO {

    private int orderID;
    private int productID;
    private int quantity;
    private int userID;
    private String orderDate;

    /**
     * Default constructor.
     */
    public OrderDTO() {
    }

    /**
     * Constructor to initialize all fields.
     *
     * @param orderID The ID of the order.
     * @param productID The ID of the product.
     * @param quantity The quantity of the product ordered.
     * @param userID The userID associated with the order.
     * @param orderDate The date the order was placed.
     */
    public OrderDTO(int orderID, int productID, int quantity, int userID, String orderDate) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.userID = userID;
        this.orderDate = orderDate;
    }

    // Getters and Setters

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderID=" + orderID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                ", userID=" + userID +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
