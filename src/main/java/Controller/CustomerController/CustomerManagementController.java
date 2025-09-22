package Controller.CustomerController;

import Model.Customer;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerManagementController implements CustomerManagementService{

    @Override
    public void addCustomer(Customer customer){
        String SQL = "INSERT INTO customer(CustID, CustTitle, CustName, DOB, salary, CustAddress, City, Province, PostalCode) VALUES(?,?,?,?,?,?,?,?,?);";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,customer.getCustomerId());
            preparedStatement.setObject(2,customer.getTitle());
            preparedStatement.setObject(3,customer.getCustomerName());
            preparedStatement.setObject(4,customer.getDOB());
            preparedStatement.setObject(5,customer.getSalary());
            preparedStatement.setObject(6,customer.getAddress());
            preparedStatement.setObject(7,customer.getCity());
            preparedStatement.setObject(8,customer.getProvince());
            preparedStatement.setObject(9,customer.getPostalCode());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(String customerId){
        String SQL = "DELETE FROM customer WHERE CustID = ?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1, customerId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(Customer customer){
        String SQL = "UPDATE customer SET CustTitle = ?, CustName = ?, DOB = ?, salary = ?, CustAddress = ?, City = ?, Province = ?, PostalCode = ? WHERE CustID = ?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,customer.getTitle());
            preparedStatement.setObject(2,customer.getCustomerName());
            preparedStatement.setObject(3,customer.getDOB());
            preparedStatement.setObject(4,customer.getSalary());
            preparedStatement.setObject(5,customer.getAddress());
            preparedStatement.setObject(6,customer.getCity());
            preparedStatement.setObject(7,customer.getProvince());
            preparedStatement.setObject(8,customer.getPostalCode());
            preparedStatement.setObject(9,customer.getCustomerId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer;");

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                customers.add(new Customer(
                                resultSet.getString("CustID"),
                                resultSet.getString("CustTitle"),
                                resultSet.getString("CustName"),
                                resultSet.getDate("DOB").toLocalDate(),
                                resultSet.getDouble("salary"),
                                resultSet.getString("CustAddress"),
                                resultSet.getString("City"),
                                resultSet.getString("Province"),
                                resultSet.getString("PostalCode")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }
}
