module inventory.inventory {
    requires javafx.controls;
    requires javafx.fxml;


    opens inventory to javafx.fxml;
    exports inventory;
    exports inventory.UI;
    opens inventory.UI to javafx.fxml;
}