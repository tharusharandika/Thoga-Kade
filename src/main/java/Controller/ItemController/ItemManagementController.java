package Controller.ItemController;

import Model.Item;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ItemManagementController implements ItemManagementService{

    @Override
    public void addItem(String itemCode, String description, String packSize, double unitPrice, int quantity){
        String SQL = "INSERT INTO item(ItemCode, Description, PackSize, UnitPrice, QtyOnHand) VALUES(?,?,?,?,?);";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,itemCode);
            preparedStatement.setObject(2,description);
            preparedStatement.setObject(3,packSize);
            preparedStatement.setObject(4,unitPrice);
            preparedStatement.setObject(5,quantity);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItem(String itemCode){
        String SQL = "DELETE FROM item WHERE ItemCode = ?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setObject(1, itemCode);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(String description, String packSize, double unitPrice, int quantity, String itemCode){
        String SQL = "UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?;";

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,description);
            preparedStatement.setObject(2,packSize);
            preparedStatement.setObject(3,unitPrice);
            preparedStatement.setObject(4,quantity);
            preparedStatement.setObject(5,itemCode);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Item> getAllItems(){

        ObservableList<Item> items = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                items.add(new Item(
                                resultSet.getString("ItemCode"),
                                resultSet.getString("Description"),
                                resultSet.getString("PackSize"),
                                resultSet.getDouble("UnitPrice"),
                                resultSet.getInt("QtyOnHand")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
}
