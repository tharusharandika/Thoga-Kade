package Controller.ItemController;

import Model.Item;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ItemManagementFormController implements Initializable {
//    ItemManagementController itemManagementController = new ItemManagementController();

    ItemManagementService itemManagementService = new ItemManagementController();

    ObservableList<Item> items = FXCollections.observableArrayList();

    Stage dashboard = new Stage();

    @FXML
    private JFXButton btnAddItem;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDeleteItem;

    @FXML
    private JFXButton btnUpdateItem;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colPackSize;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblItemCode;

    @FXML
    private Label lblItemManagementSystem;

    @FXML
    private Label lblPackSize;

    @FXML
    private Label lblQuantity;

    @FXML
    private Label lblUnitPrize;

    @FXML
    private TableView<Item> tblItems;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtPackSize;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtUnitPrize;

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrize.getText());
        String quantity = txtQuantity.getText();

        itemManagementService.addItem(itemCode, description, packSize, unitPrice, Integer.parseInt(quantity));

        loadItemDetails();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtItemCode.setText(null);
        txtDescription.setText(null);
        txtPackSize.setText(null);
        txtUnitPrize.setText(null);
        txtQuantity.setText(null);
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();

        itemManagementService.deleteItem(itemCode);

        loadItemDetails();
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) {
        String itemCode = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize = txtPackSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrize.getText());
        int quantity = Integer.parseInt(txtQuantity.getText());

        itemManagementService.updateItem(description, packSize, unitPrice, quantity, itemCode);

        loadItemDetails();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadItemDetails();

        tblItems.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(newValue.getDescription());
                txtPackSize.setText(newValue.getPackSize());
                txtUnitPrize.setText(String.valueOf(Double.valueOf(newValue.getUnitPrice())));
                txtQuantity.setText(String.valueOf(newValue.getQuantity()));
            }
        });
    }

    private void loadItemDetails(){
        items.clear();

        items = itemManagementService.getAllItems();

        tblItems.setItems(items);
    }

}
