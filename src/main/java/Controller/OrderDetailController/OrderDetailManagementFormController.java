package Controller.OrderDetailController;

import Model.OrderDetails;
import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class OrderDetailManagementFormController implements Initializable {

    ObservableList<OrderDetails> orderDetails = FXCollections.observableArrayList();

    Stage dashboard = new Stage();

    @FXML
    private JFXButton btnBackToDashboard;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblOrderManagementSystem;

    @FXML
    private TableView<OrderDetails> tblOrderDetails;

    @FXML
    void btnBackToDashboardOnAction(ActionEvent event) {
        try {
            dashboard.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Dashboard.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dashboard.setResizable(false);
        dashboard.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        loadOrderDetails();
    }

    private void loadOrderDetails(){
        orderDetails.clear();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orderdetail;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                orderDetails.add(new OrderDetails(
                                resultSet.getString("OrderID"),
                                resultSet.getString("ItemCode"),
                                resultSet.getInt("OrderQTY"),
                                resultSet.getInt("Discount")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tblOrderDetails.setItems(orderDetails);
    }
}
