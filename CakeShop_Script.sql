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


CREATE TABLE Suppliers
(
    supplierID      INT PRIMARY KEY,
    supplierDetails TEXT,
    FOREIGN KEY (supplierID) REFERENCES Users (userID)
);


INSERT INTO Users (name, email, password, userType) 
VALUES ('Group11', 'group11@example.com', 'Group11', 'Retailer');