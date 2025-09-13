module inventory.inventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens inventory to javafx.fxml;
    opens inventory.UI to javafx.fxml;
    opens inventory.model to javafx.base;
    opens inventory.Service to javafx.fxml;
    opens inventory.DataAccessObject to javafx.fxml;
    opens inventory.Application to javafx.fxml;

    exports inventory.DataAccessObject;
    exports inventory.model;
    exports inventory.Application;
    exports inventory.Service;
    exports inventory.UI;

}