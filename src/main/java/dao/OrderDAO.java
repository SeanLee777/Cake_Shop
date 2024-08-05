package dao;

import dto.OrderDTO;

import java.util.List;


public interface OrderDAO {


    List<OrderDTO> getOrderByEmail(String email);


    List<OrderDTO> getOrderList();


    boolean createOrder(OrderDTO orderDTO);


    int getUserIDbyEmail(String email);
}
