package Controller.OrderController;

import Model.OrderDetails;
import Model.Orders;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderManagementFormController implements Initializable {
//    OrderManagementController orderManagementController = new OrderManagementController();
    OrderManagementService orderManagementService = new OrderManagementController();

    ObservableList<Orders> orders = FXCollections.observableArrayList();

    Stage dashboard = new Stage();

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDeleteOrder;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnUpdateOrder;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private DatePicker dpkOrderDate;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderManagementSystem;

    @FXML
    private TableView<Orders> tblOrder;

    @FXML
    private JFXTextField txtOrderId;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtOrderId.setText(null);
        dpkOrderDate.setValue(null);
        txtCustomerId.setText(null);
    }

    @FXML
    void btnDeleteOrderOnAction(ActionEvent event) {
        orderManagementService.deleteOrder(txtOrderId.getText());

        loadOrders();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        LocalDate orderDate = dpkOrderDate.getValue();
        String customerId = txtCustomerId.getText();

        orderManagementService.placeOrder(orderId, orderDate, customerId);

        loadOrders();
    }

    @FXML
    void btnUpdateOrderOnAction(ActionEvent event) {
        String orderId = txtOrderId.getText();
        LocalDate orderDate = dpkOrderDate.getValue();
        String customerId = txtCustomerId.getText();

        orderManagementService.updateOrder(orderId, orderDate, customerId);

        loadOrders();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        loadOrders();

        tblOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                txtOrderId.setText(newValue.getOrderId());
                dpkOrderDate.setValue(newValue.getOrderDate());
                txtCustomerId.setText(newValue.getCustomerId());
            }
        });
    }

    private void loadOrders(){
        orders.clear();

//        orders = orderManagementController.getAllOrders();

        tblOrder.setItems(orderManagementService.getAllOrders());
    }
}
