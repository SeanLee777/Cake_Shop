package dto;

import java.util.Date;


public class OrderDTO {
    private int orderID;
    private int productID;
    private int quantity;
    private Date orderDate;
    private int userID;


    public OrderDTO( int userID,int productID, int quantity) {
        this.userID=userID;
        this.productID = productID;
        this.quantity = quantity;
    }


    public OrderDTO(int orderID, int productID, int quantity, Date orderDate) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }



    public OrderDTO() {

    }


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

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String toString() {
        return ", OrderID: " + orderID + ", ProductID: " + productID + ", Quantity: " + quantity;
    }
}
