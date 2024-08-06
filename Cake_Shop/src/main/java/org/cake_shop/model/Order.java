package org.cake_shop.model;



import java.time.LocalDateTime;

public class Order {
    private int orderID;
    private int productID;
    private int quantity;
    private int consumerID;
    private String productName;
    private LocalDateTime orderDate;

    // Constructor
    public Order() {}

    public Order(int orderID, int productID, int quantity, int consumerID, String productName, LocalDateTime orderDate) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.consumerID = consumerID;
        this.productName = productName;
        this.orderDate = orderDate;
    }

    // Getters and setters
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

    public int getConsumerID() {
        return consumerID;
    }

    public void setConsumerID(int consumerID) {
        this.consumerID = consumerID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
                ", quantity=" + quantity +
                ", consumerID=" + consumerID +
                ", productName='" + productName + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
