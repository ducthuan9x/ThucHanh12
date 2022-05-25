package Severce;

import Model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    List<Customer>customerList=new ArrayList<>();
    public CustomerDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void add(Customer customer) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into customer(id,name,age) values (?,?,?)");) {
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setInt(3, customer.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    @Override
    public Customer finById(int id) {
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select id,name,age from customer where id=?");) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int age=rs.getInt("age");
                customer=new Customer(id,name,age);
            }
        } catch (SQLException e) {
        }
        return customer;

    }

    @Override
    public List<Customer> finAll() {
        List<Customer>customerList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select *from customer");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age=rs.getInt("age");
                customerList.add(new Customer(id,name,age));
            }
        } catch (SQLException e) {

        }
        return customerList;
    }


    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement( "delete from customer where id = ?;")){
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean edit(Customer custorme) throws SQLException {
       boolean rowEdit;
        try (PreparedStatement statement= getConnection().prepareStatement("update customer set name=?,  age=? where id=?;")){

        statement.setString(1,custorme.getName());
        statement.setInt(2,custorme.getAge());
        statement.setInt(3,custorme.getId());
        rowEdit=statement.executeUpdate()>0;
        }
        return rowEdit;
    }
}
