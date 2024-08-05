package dao;

import dto.ProductDTO;
import java.sql.SQLException;
import java.util.List;


public interface ProductDAO {


    boolean createFood(ProductDTO productDTO) throws SQLException;
    boolean updateFood(ProductDTO productDTO) throws SQLException;

    boolean deleteFood(int productID) throws SQLException;

    ProductDTO getFoodByID(int productID) throws SQLException;
    ProductDTO getSurplusFoodByID(int productID) throws SQLException;

    List<ProductDTO> getFoodList() throws SQLException;
    List<ProductDTO> getSurplusFoodList() throws SQLException;


    boolean updateSurplusFoodList(int productID, int quantity) throws SQLException;
    boolean updateSurplusFood(ProductDTO productDTO) throws SQLException;
}