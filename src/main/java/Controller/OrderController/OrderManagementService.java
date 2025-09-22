package Controller.OrderController;

import Model.Orders;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public interface OrderManagementService {
    void deleteOrder(String text);
    void placeOrder(String orderId, LocalDate orderDate, String customerId);
    void updateOrder(String orderId, LocalDate orderDate, String customerId);
    ObservableList<Orders> getAllOrders();
}
