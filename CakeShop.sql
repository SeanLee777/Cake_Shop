/*
* Java Final Group 11 Project
* Class: CST8319
* Author: Shanghao Li
* Student ID: 040903008
*/

CREATE DATABASE IF NOT EXISTS cakeshop;
USE cakeshop;

CREATE TABLE Users
(
    userID               INT AUTO_INCREMENT PRIMARY KEY,
    name                 VARCHAR(255)                                            NOT NULL,
    email                VARCHAR(255)                                            NOT NULL UNIQUE,
    password             VARCHAR(255)                                            NOT NULL,
    userType             ENUM ('Retailer', 'Consumers', 'Suppler') NOT NULL,
    city                 VARCHAR(255),
    postalCode           VARCHAR(10),
    phoneNumber          VARCHAR(20)
);

CREATE TABLE Retailers
(
    retailerID   INT PRIMARY KEY,
    storeDetails TEXT,
    FOREIGN KEY (retailerID) REFERENCES Users (userID)
);

CREATE TABLE Consumers
(
    consumerID INT PRIMARY KEY,
    address    TEXT,
    userID  INT,
    FOREIGN KEY (consumerID) REFERENCES Users (userID)
);

CREATE TABLE Suppler
(
    SupplerID      INT PRIMARY KEY,
    SupplerDetails TEXT
);


CREATE TABLE Products
(
    productID      INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    expirationDate DATE,
    quantity       INT          NOT NULL,
    price          DECIMAL(10, 2) NOT NULL,
    retailerID     INT,
    listingType    ENUM ('Donation', 'Sale') ,
    zipcode        VARCHAR(255),
    FOREIGN KEY (retailerID) REFERENCES Retailers (retailerID)
);


CREATE TABLE Orders
(
    orderID       INT AUTO_INCREMENT PRIMARY KEY,
    orderDate     DATETIME DEFAULT CURRENT_TIMESTAMP,
    productID     INT,
    quantity      INT NOT NULL,
    userID INT,
    FOREIGN KEY (userID) REFERENCES users (userID),
    FOREIGN KEY (productID) REFERENCES Products (productID)
);


CREATE TABLE OrderDetails
(
    orderDetailID INT AUTO_INCREMENT PRIMARY KEY,
    orderID       INT,
    productID     INT,
    quantity      INT NOT NULL,
    userID INT,
    FOREIGN KEY (userID) REFERENCES Users (userID),
    FOREIGN KEY (orderID) REFERENCES Orders (orderID),
    FOREIGN KEY (productID) REFERENCES Products (productID)
);

CREATE TABLE Inventory
(
    inventoryID INT AUTO_INCREMENT PRIMARY KEY,
    retailerID  INT,
    FOREIGN KEY (retailerID) REFERENCES Retailers (retailerID)
);

CREATE TABLE InventoryItems
(
    inventoryItemID INT AUTO_INCREMENT PRIMARY KEY,
    inventoryID     INT,
    productID       INT,
    quantity        INT NOT NULL,
    FOREIGN KEY (inventoryID) REFERENCES Inventory (inventoryID),
    FOREIGN KEY (productID) REFERENCES Products (productID)
)
