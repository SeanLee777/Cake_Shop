/**
 * Java Final Group 11 Project
 * Class: CST8319
 * Author: DAIN CIBY THEKKEL
 * Student ID: 040947640
 */

package dto;

import java.util.Date;

public class ProductDTO {


    private int productID;


    private String name;


    private int quantity;


    private Date expirationDate;

    private double price;


    private String listingType;


    private String zipcode;


    public ProductDTO(int productID, String name, int quantity, Date expirationDate, double price) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
    }


    public ProductDTO(String name, int quantity, Date expirationDate, double price) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
    }


    public ProductDTO(int productID, String name, int quantity, Date expirationDate, double price, String listingType, String zipcode) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.listingType = listingType;
        this.zipcode = zipcode;
    }


    public ProductDTO(String name, int quantity, Date expirationDate, double price, String listingType, String zipcode) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.listingType = listingType;
        this.zipcode = zipcode;
    }


    public ProductDTO() { }

    public int getProductID() {return productID;}

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getListingType() { return listingType;}

    public void setListingType(String listingType) { this.listingType = listingType;}

    public String getZipcode() { return zipcode;}

    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                ", price=" + price +
                '}';
    }
}