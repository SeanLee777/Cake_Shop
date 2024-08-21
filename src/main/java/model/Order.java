package model;

import java.time.LocalDateTime;

/**
 * Model class representing an Order entity in the system.
 */
public class Order {

    private int orderID;
    private int productID;
    private String productName;  // New field for product name
    private int quantity;
    private double price;  // New field for price
    private int userID;
    private LocalDateTime orderDate;

    /**
     * Default constructor.
     */
    public Order() {
    }

    /**
     * Constructor to initialize all fields.
     *
     * @param orderID The ID of the order.
     * @param productID The ID of the product.
     * @param productName The name of the product.
     * @param quantity The quantity of the product ordered.
     * @param price The price of the product.
     * @param userID The userID associated with the order.
     * @param orderDate The date the order was placed.
     */
    public Order(int orderID, int productID, String productName, int quantity, double price, int userID, LocalDateTime orderDate) {
        this.orderID = orderID;
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", productID=" + productID +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", userID=" + userID +
                ", orderDate=" + orderDate +
                '}';
    }
}
