package Controller.OrderController;

import Model.Orders;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class OrderManagementController implements OrderManagementService{

    @Override
    public void placeOrder(String orderId, LocalDate orderDate, String customerId){
        String SQL = "INSERT INTO orders(OrderID, OrderDate, CustID) VALUES(?,?,?);";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,orderId);
            preparedStatement.setObject(2,orderDate);
            preparedStatement.setObject(3,customerId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrder(String orderId, LocalDate orderDate, String customerId){
        String SQL = "UPDATE orders SET OrderDate = ?, CustID = ? WHERE OrderID = ?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,orderDate);
            preparedStatement.setObject(2,customerId);
            preparedStatement.setObject(3,orderId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(String orderId){
        String SQL = "DELETE FROM orders WHERE OrderID = ?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, orderId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Orders> getAllOrders(){

        ObservableList<Orders> orders = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                orders.add(new Orders(
                                resultSet.getString("OrderID"),
                                resultSet.getDate("OrderDate").toLocalDate(),
                                resultSet.getString("CustID")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }
}
