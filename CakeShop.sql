/*
* Java Final Group 11 Project
* Class: CST8319
* Author: Shanghao Li, Feng Qi
* Student ID: 040903008,041050147
*/



DROP TABLE IF EXISTS InventoryItems;
DROP TABLE IF EXISTS Inventory;
DROP TABLE IF EXISTS OrderDetails;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Products;
DROP TABLE IF EXISTS Supplier;
DROP TABLE IF EXISTS Consumers;
DROP TABLE IF EXISTS Retailers;
DROP TABLE IF EXISTS Users;

CREATE DATABASE IF NOT EXISTS cakeshop;
USE cakeshop;

CREATE TABLE Users
(
    userID               INT AUTO_INCREMENT PRIMARY KEY,
    name                 VARCHAR(255) NOT NULL,
    email                VARCHAR(255) NOT NULL UNIQUE,
    password             VARCHAR(255) NOT NULL,
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
    FOREIGN KEY (consumerID) REFERENCES Users (userID)
);

CREATE TABLE Supplier
(
    supplierID      INT AUTO_INCREMENT PRIMARY KEY,
    supplierName    VARCHAR(255) NOT NULL,
    contactNumber   VARCHAR(20),
    email           VARCHAR(255),
    address         VARCHAR(500),
    supplierDetails TEXT
);

CREATE TABLE Products
(
    productID      INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    expirationDate DATE,
    quantity       INT NOT NULL,
    price          DECIMAL(10, 2) NOT NULL,
    retailerID     INT,
    listingType    ENUM ('Donation', 'Sale'),
    zipcode        VARCHAR(255),
    FOREIGN KEY (retailerID) REFERENCES Retailers (retailerID)
);

CREATE TABLE Orders
(
    orderID       INT AUTO_INCREMENT PRIMARY KEY,
    orderDate     DATETIME DEFAULT CURRENT_TIMESTAMP,
    productID     INT,
    quantity      INT NOT NULL,
    userID        INT,
    FOREIGN KEY (userID) REFERENCES Users (userID),
    FOREIGN KEY (productID) REFERENCES Products (productID)
);

CREATE TABLE OrderDetails
(
    orderDetailID INT AUTO_INCREMENT PRIMARY KEY,
    orderID       INT,
    productID     INT,
    quantity      INT NOT NULL,
    userID        INT,
    FOREIGN KEY (userID) REFERENCES Users (userID),
    FOREIGN KEY (orderID) REFERENCES Orders (orderID),
    FOREIGN KEY (productID) REFERENCES Products (productID)
);


CREATE TABLE InventoryItems
(
    inventoryItemID INT AUTO_INCREMENT PRIMARY KEY,
    inventoryID     INT NOT NULL,  -- 确保 inventoryID 不允许为空
    productID       INT NOT NULL,  -- 确保 productID 不允许为空
    quantity        INT NOT NULL,  -- 保证 quantity 是必须的
    unit            VARCHAR(50),   -- 添加 unit 列以匹配表单中的 unit 字段
    price           DECIMAL(10, 2) -- 添加 price 列以匹配表单中的 price 字段

);

ALTER TABLE InventoryItems
ADD COLUMN name VARCHAR(255);
