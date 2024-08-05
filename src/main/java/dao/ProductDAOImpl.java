package dao;

import dto.ProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductDAOImpl implements ProductDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);

    @Override
    public boolean createFood(ProductDTO productDTO) throws SQLException {
        String sql = "INSERT INTO Products (name, quantity, expirationDate, price) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DatabaseUtil.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, productDTO.getName());
            stmt.setInt(2, productDTO.getQuantity());
            stmt.setDate(3, new java.sql.Date(productDTO.getExpirationDate().getTime()));
            stmt.setDouble(4, productDTO.getPrice());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Food created successfully: " + productDTO.getName());
            } else {
                logger.warn("Failed to create food: " + productDTO.getName());
            }
        } catch (SQLException e) {
            logger.error("SQLException in createFood for name: " + productDTO.getName(), e);
            throw e; // Re throwing
        }
        return true;
    }


    @Override
    public boolean updateFood(ProductDTO productDTO) throws SQLException {
        String sql = "UPDATE Products SET name = ?, quantity = ?, expirationDate = ?, price = ?  WHERE productID = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productDTO.getName());
            stmt.setInt(2, productDTO.getQuantity());
            stmt.setDate(3, new java.sql.Date(productDTO.getExpirationDate().getTime()));
            stmt.setDouble(4, productDTO.getPrice());
            stmt.setInt(5, productDTO.getProductID());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Food updated successfully: " + productDTO.getName());
                return true;
            } else {
                logger.warn("Failed to update food: " + productDTO.getName());
                return false;
            }
        } catch (SQLException e) {
            logger.error("SQLException in updateFood for name: " + productDTO.getName(), e);
            throw e; // Re throwing
        }
    }


    public boolean updateSurplusFood(ProductDTO productDTO) throws SQLException {
        String sql = "UPDATE Products SET name = ?, quantity = ?, expirationDate = ?, price = ?, listingType = ?, zipcode = ? WHERE productID = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productDTO.getName());
            stmt.setInt(2, productDTO.getQuantity());
            stmt.setDate(3, new java.sql.Date(productDTO.getExpirationDate().getTime()));
            stmt.setDouble(4, productDTO.getPrice());
            stmt.setString(5, productDTO.getListingType());
            stmt.setString(6, productDTO.getZipcode());
            stmt.setInt(7, productDTO.getProductID());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Surplus Food updated successfully: " + productDTO.getName());
                return true;
            } else {
                logger.warn("Failed to update surplus food: " + productDTO.getName());
                return false;
            }
        } catch (SQLException e) {
            logger.error("SQLException in updateSurplusFood for name: " + productDTO.getName(), e);
            throw e; // Re throwing
        }
    }


    @Override
    public boolean deleteFood(int productID) throws SQLException {
        try {
            Connection conn = DatabaseUtil.getInstance().getConnection();
            String sql = "DELETE FROM Products WHERE productID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productID);
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Food deleted successfully: " + productID);
                return true;
            } else {
                logger.warn("Failed to delete food: " + productID);
                return false;
            }
        } catch (SQLException e) {
            logger.error("SQLException in deleteFood for productID: " + productID, e);
            throw e; // Re throwing
        }
    }


    @Override
    public ProductDTO getFoodByID(int productID) throws SQLException {
        try { // Try-with-resources
            Connection conn = DatabaseUtil.getInstance().getConnection();
            String sql = "SELECT name, quantity, expirationDate, price " +
                    "FROM Products WHERE productID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ProductDTO food = new ProductDTO(
                        productID,
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDate("expirationDate"),
                        rs.getDouble("price"));
                logger.info("Food found by ID: " + productID);
                return food;
            } else {
                logger.info("No food found by ID: " + productID);
                return null;
            }
        } catch (SQLException e) {
            logger.error("SQLException in getFoodByID for product ID: " + productID, e);
            throw e; // Rethrow the exception to handle it in the calling method.
        }
    }


    @Override
    public ProductDTO getSurplusFoodByID(int productID) throws SQLException {
        try { // Try-with-resources
            Connection conn = DatabaseUtil.getInstance().getConnection();
            String sql = "SELECT name, quantity, expirationDate, price, listingType, zipcode " +
                    "FROM Products WHERE productID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ProductDTO food = new ProductDTO(
                        productID,
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDate("expirationDate"),
                        rs.getDouble("price"),
                        rs.getString("listingType"),
                        rs.getString("zipcode"));
                logger.info("Surplus food found by ID: " + productID);
                return food;
            } else {
                logger.info("No surplus food found by ID: " + productID);
                return null;
            }
        } catch (SQLException e) {
            logger.error("SQLException in getSurplusFoodByID for product ID: " + productID, e);
            throw e; // Rethrow the exception to handle it in the calling method.
        }
    }

    @Override
    public List<ProductDTO> getFoodList() throws SQLException {
        List<ProductDTO> foodList = new ArrayList<>();
        try {
            Connection conn = DatabaseUtil.getInstance().getConnection();
            String sql = "SELECT productID, name, expirationDate, quantity, price FROM Products";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductDTO food = new ProductDTO(
                        rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDate("expirationDate"),
                        rs.getDouble("price"));
                foodList.add(food);
            }
            logger.info("Food list retrieved successfully with " + foodList.size() + " items.");
        } catch (SQLException e) {
            logger.error("SQLException in getFoodList", e);
            throw e; // Rethrow the exception to handle it in the calling method.
        }
        return foodList;
    }


    @Override
    public List<ProductDTO> getSurplusFoodList() throws SQLException {
        try {
            Connection conn = DatabaseUtil.getInstance().getConnection();
            String sql = "SELECT productID, name, expirationDate, quantity, price, listingType, zipcode " +
                    "FROM Products WHERE quantity > 0 " +
                    "AND expirationDate <= DATE_ADD(CURDATE(), INTERVAL 7 DAY)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<ProductDTO> surplusFoodList = new ArrayList<>();
            while (rs.next()) {
                ProductDTO food = new ProductDTO(
                        rs.getInt("productID"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDate("expirationDate"),
                        rs.getDouble("price"),
                        rs.getString("listingType"),
                        rs.getString("zipcode"));
                surplusFoodList.add(food);
            }
            logger.info("Surplus food list retrieved successfully");
            return surplusFoodList;
        } catch (SQLException e) {
            logger.error("SQLException in getSurplusFoodList", e);
            throw e;
        }
    }


    @Override
    public boolean updateSurplusFoodList(int productID, int quantity) throws SQLException {
        try {
            Connection conn = DatabaseUtil.getInstance().getConnection();
            String sql = "UPDATE Products SET quantity = ? WHERE productID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantity);
            stmt.setInt(2, productID);
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Surplus food list updated successfully");
                return true;
            } else {
                logger.warn("Failed to update surplus food list");
                return false;
            }
        } catch (SQLException e) {
            logger.error("SQLException in updateSurplusFoodList", e);
            throw e;
        }
    }
}