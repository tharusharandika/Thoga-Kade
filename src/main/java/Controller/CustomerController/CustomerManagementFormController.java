package Controller.CustomerController;

import Model.Customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class CustomerManagementFormController implements Initializable {

//    CustomerManagementController customerManagementController = new CustomerManagementController();

    CustomerManagementService customerManagementService = new CustomerManagementController();

    ObservableList<Customer> customers = FXCollections.observableArrayList();

    @FXML
    private JFXButton btnAddCustomer;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDeleteCustomer;

    @FXML
    private JFXButton btnUpdateCustomer;

    @FXML
    private JFXComboBox<String> cmbProvince;

    @FXML
    private JFXComboBox<String> cmbTitle;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colPostalCode;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker dpkDOB;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblCustomerManagementSystem;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDOB;

    @FXML
    private Label lblPostalCode;

    @FXML
    private Label lblProvince;

    @FXML
    private Label lblSalary;

    @FXML
    private Label lblTitle;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtPostalCode;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
//        String customerId = txtCustomerId.getText();
//        String title = cmbTitle.getValue();
//        String customerName = txtCustomerName.getText();
//        LocalDate DOB = dpkDOB.getValue();
//        double salary = Double.parseDouble(txtSalary.getText());
//        String address = txtAddress.getText();
//        String city = txtCity.getText();
//        String province = cmbProvince.getValue();
//        String postalCode = txtPostalCode.getText();

        Customer customer = new Customer(
                txtCustomerId.getText(),
                cmbTitle.getValue(),
                txtCustomerName.getText(),
                dpkDOB.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                cmbProvince.getValue(),
                txtPostalCode.getText()
        );

        customerManagementService.addCustomer(customer);

        loadCustomerDetails();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtCustomerId.setText(null);
        cmbTitle.setValue(null);
        txtCustomerName.setText(null);
        dpkDOB.setValue(null);
        txtSalary.setText(null);
        txtAddress.setText(null);
        txtCity.setText(null);
        cmbProvince.setValue(null);
        txtPostalCode.setText(null);
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) {
//        String customerId = txtCustomerId.getText();

        customerManagementService.deleteCustomer(txtCustomerId.getText());

        loadCustomerDetails();
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) {
//        String customerId = txtCustomerId.getText();
//        String title = cmbTitle.getValue();
//        String customerName = txtCustomerName.getText();
//        LocalDate DOB = dpkDOB.getValue();
//        double salary = Double.parseDouble(txtSalary.getText());
//        String address = txtAddress.getText();
//        String  city = txtCity.getText();
//        String province = cmbProvince.getValue();
//        String postalCode = txtPostalCode.getText();

        Customer customer = new Customer(
                txtCustomerId.getText(),
                cmbTitle.getValue(),
                txtCustomerName.getText(),
                dpkDOB.getValue(),
                Double.parseDouble(txtSalary.getText()),
                txtAddress.getText(),
                txtCity.getText(),
                cmbProvince.getValue(),
                txtPostalCode.getText()
        );

        customerManagementService.updateCustomer(customer);

        loadCustomerDetails();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> customerTitles = FXCollections.observableArrayList(
                "Mr",
                "Ms",
                "Miss"

        );
        cmbTitle.setItems(customerTitles);

        ObservableList<String> province = FXCollections.observableArrayList(
                "Central",
                "Eastern",
                "North-Central",
                "North-Western",
                "Northern",
                "Sabaragamuwa",
                "Southern",
                "Uva",
                "Western"
        );
        cmbProvince.setItems(province);

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        loadCustomerDetails();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                txtCustomerId.setText(newValue.getCustomerId());
                cmbTitle.setValue(newValue.getTitle());
                txtCustomerName.setText(newValue.getCustomerName());
                dpkDOB.setValue(newValue.getDOB());
                txtSalary.setText(String.valueOf(Double.valueOf(newValue.getSalary())));
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                cmbProvince.setValue(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
            }
        });
    }

    private void loadCustomerDetails(){

        customers.clear();

        customers = customerManagementService.getAllCustomers();

        tblCustomer.setItems(customers);
    }
}
