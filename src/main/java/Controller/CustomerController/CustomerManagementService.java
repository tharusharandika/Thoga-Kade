package Controller.CustomerController;

import Model.Customer;
import javafx.collections.ObservableList;

public interface CustomerManagementService {

    void addCustomer(Customer customer);
    void deleteCustomer(String customer);
    void updateCustomer(Customer customer);
    ObservableList<Customer> getAllCustomers();

}
