package Controller.ItemController;

import Model.Item;
import javafx.collections.ObservableList;

public interface ItemManagementService {
    void addItem(String itemCode, String description, String packSize, double unitPrice, int i);
    void deleteItem(String itemCode);
    void updateItem(String description, String packSize, double unitPrice, int quantity, String itemCode);
    ObservableList<Item> getAllItems();
}
