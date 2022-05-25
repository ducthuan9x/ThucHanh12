package Severce;

import Model.Customer;
import Model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO {
    public void add(Order order) throws SQLException;

    public Order finById(int id);

    public List<Order> finAll();

    public boolean delete(int id) throws SQLException;

    public boolean edit(Order order) throws SQLException;
}
