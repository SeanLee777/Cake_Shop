package dao;

import dto.OrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger logger = LogManager.getLogger(OrderDAOImpl.class);


    @Override
    public List<OrderDTO> getOrderByEmail(String email) {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT o.orderID, o.productID, o.quantity, o.orderDate, o.userID " +
                "FROM orders o " +
                "JOIN users u ON o.userID = u.userID " +
                "WHERE u.email = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderDTO order = new OrderDTO();
                    order.setOrderID(rs.getInt("orderID"));
                    order.setProductID(rs.getInt("productID"));
                    order.setQuantity(rs.getInt("quantity"));
                    order.setOrderDate(rs.getDate("orderDate"));
                    order.setUserID(rs.getInt("userID"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException in getOrderByEmail: ", e);
        }
        return orders;
    }


    @Override
    public List<OrderDTO> getOrderList() {
        List<OrderDTO> orders = new ArrayList<>();
        String sql = "SELECT orderID, productID, quantity, orderDate, userID FROM Orders";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setOrderID(rs.getInt("orderID"));
                order.setProductID(rs.getInt("productID"));
                order.setQuantity(rs.getInt("quantity"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setUserID(rs.getInt("userID"));
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("SQLException in getOrderList: ", e);
        }
        return orders;
    }


    @Override
    public boolean createOrder(OrderDTO orderDTO) {
        String sql = "INSERT INTO Orders (userID, productID, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderDTO.getUserID());
            stmt.setInt(2, orderDTO.getProductID());
            stmt.setInt(3, orderDTO.getQuantity());
            int rowCount = stmt.executeUpdate();
            if (rowCount > 0) {
                logger.info("Order created successfully.");
                return true;
            } else {
                logger.warn("Failed to create order.");
                return false;
            }
        } catch (SQLException e) {
            logger.error("SQLException in createOrder: ", e);
            return false;
        }
    }

    @Override
    public int getUserIDbyEmail(String email) {
        String sql = "SELECT userID FROM users WHERE email = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("userID");
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException in getUserIdByEmail: ", e);
        }
        return -1;
    }
}
