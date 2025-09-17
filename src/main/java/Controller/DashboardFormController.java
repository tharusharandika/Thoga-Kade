package Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {

    Stage customerManagement = new Stage();
    Stage itemManagement = new Stage();
    Stage orderManagement = new Stage();
    Stage orderDetailManagement = new Stage();

    @FXML
    private JFXButton btnCustomerManagementSystem;

    @FXML
    private JFXButton btnItemManagementSystem;

    @FXML
    private JFXButton btnOrderDetailManagementSystem;

    @FXML
    private JFXButton btnOrderManagementSystem;

    @FXML
    private Label lblDashboard;

    @FXML
    void btnCustomerManagementOnAction(ActionEvent event) {
        try {
            customerManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/CustomerManagementForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        customerManagement.setResizable(false);
        customerManagement.show();
    }

    @FXML
    void btnItemManagementOnAction(ActionEvent event) {
        try {
            itemManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/ItemManagementForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        itemManagement.setResizable(false);
        itemManagement.show();
    }

    @FXML
    void btnOrderDetailManagementSystemOnAction(ActionEvent event) {
        try {
            orderDetailManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/OrderDetailManagementForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        orderDetailManagement.setResizable(false);
        orderDetailManagement.show();
    }

    @FXML
    void btnOrderManagementSystemOnAction(ActionEvent event) {
        try {
            orderManagement.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/OrderManagementForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        orderManagement.setResizable(false);
        orderManagement.show();
    }

}
