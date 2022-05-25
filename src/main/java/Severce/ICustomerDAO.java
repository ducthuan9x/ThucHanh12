package Severce;

import Model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    public void add(Customer custorme) throws SQLException;

    public Customer finById(int id);

    public List<Customer> finAll();

    public boolean delete(int id) throws SQLException;

    public boolean edit(Customer custorme) throws SQLException;
}
