#Cake Shop Management System - Supplier Management

## Overview

This module of the Cake Shop Management System focuses on managing food items required for cake preparation. The code developed by Feng Qi handles the supplier-related functionalities, including the management of food data, interactions with the database, and user interfaces for suppliers.

## Key Components

### 1. FoodDTO.java
**Role:** Serves as a Data Transfer Object (DTO) for food data.
- **Purpose:** To encapsulate food information such as ID, name, quantity, expiration date, and price.
- **Usage:** Organizes and holds food data retrieved from the database before presenting it to users.

### 2. FoodDAO.java
**Role:** Data Access Object (DAO) for food-related database operations.
- **Purpose:** Manages interactions with the database, including reading and writing food data.
- **Usage:** Works with `FoodDTO` to perform CRUD operations on food data.

### 3. ViewSurplusFoodListServlet.java
**Role:** Servlet for displaying available food items.
- **Purpose:** Fetches food data from `FoodDAO` and displays it on a web page.
- **Usage:** Allows users to view surplus food items available in the system.

### 4. Additional JSP Files
- **SupplierIndex.jsp**
    - **Purpose:** Provides a login dashboard for suppliers.
    - **Key Points:** Displays a welcome message, task buttons, and redirects non-supplier users to the login page.

- **EditFoodIngredients.jsp**
    - **Purpose:** Allows suppliers to update details of food ingredients.
    - **Key Points:** Contains a form for updating food ingredient information.

## Setup and Usage

1. **Clone the Repository**
   ```bash
   git clone https://github.com/SeanLee777/Cake_Shop.git
Navigate to the Project Directory

bash
Copy code
cd Cake_Shop
Compile the Code
Make sure you have a Java Development Kit (JDK) installed. Compile the Java files using:

bash
Copy code
javac -d bin src/**/*.java
Deploy the Application
Deploy the compiled files to your Java web server (e.g., Apache Tomcat). Ensure that your server is configured to handle JSP files and servlets.

Access the Supplier Dashboard

Open your web browser and navigate to http://localhost:8080/Cake_Shop/SupplierIndex.jsp to access the supplier dashboard.
Features
Food Management:

Encapsulates food data in FoodDTO.
Manages database operations with FoodDAO.
Displays surplus food items with ViewSurplusFoodListServlet.
User Interfaces:

SupplierIndex.jsp: Dashboard for supplier login.
EditFoodIngredients.jsp: Interface for updating food ingredient details.
Challenges and Solutions
Data Retrieval and Display:

Solution: Improved data retrieval methods in FoodDAO and enhanced display functionality in ViewSurplusFoodListServlet.
User Interface Design:

Solution: Updated JSP files for better usability and responsive design.
Data Validation:

Solution: Added comprehensive validation checks and error handling in FoodDTO and FoodDAO.
Future Enhancements
Advanced Food Management:

Enhanced Data Validation
Improved Supplier Interaction Interfaces
UI/UX Improvements:

Dashboard Enhancements
Refined Update Forms
Integration and Testing:

Comprehensive Testing
Feedback Integration
References
Spring Boot Documentation. (n.d.). Retrieved from Spring Boot Documentation
MySQL Documentation. (n.d.). Retrieved from MySQL Documentation
Java Servlets Documentation. (n.d.). Retrieved from Java Servlets Documentation
Git Documentation. (n.d.). Retrieved from Git Documentation
For further questions or contributions, please contact Feng Qi at artqi201908@gmail.com.

# Group-Project
# CakeShop

CakeShop is a web application designed to manage a cake shop, providing user registration, login, and cake management functionalities. The application is built using Java, JSP, and MySQL.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Folder Structure](#folder-structure)
- [Challenges and Solutions](#challenges-and-solutions)
- [Next Steps](#next-steps)
- [License](#license)

## Features

### User Management
- **Registration:** Users can register with a unique email and password.
- **Login:** Users can log in using their registered email and password.
- **Error Handling:** Provides feedback for duplicate email entries and other common issues.

### Cake Management
- **View Cakes:** Users can view a list of cakes available in the shop.
- **Manage Cakes:** Users can add, update, and delete cake entries.

### Database Integration
- **MySQL Database:** Stores user and cake information.
- **DAO Layer:** Data Access Objects for handling database operations.

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/CakeShop.git
   cd CakeShop