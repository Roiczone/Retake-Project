module inventory.inventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens inventory to javafx.fxml;
    exports inventory.UI;
    opens inventory.UI to javafx.fxml;
}