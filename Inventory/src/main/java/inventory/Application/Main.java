package inventory.Application;

import inventory.model.Product;
import inventory.Service.ProductService;
import inventory.Service.SaleService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    private ProductService productService = new ProductService();
    private SaleService saleService = new SaleService();
    private ObservableList<Product> products;

    private TableView<Product> table = new TableView<>();

    @Override
    public void start(Stage primaryStage) {

        // Load products
        products = FXCollections.observableArrayList(productService.getAllProducts());
        table.setItems(products);

        // Columns
        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> javafx.beans.property.SimpleStringProperty.stringExpression(data.getValue().getName()));

        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(data -> javafx.beans.property.SimpleIntegerProperty.integerExpression(data.getValue().getStockQuantity()));

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(data -> javafx.beans.property.SimpleDoubleProperty.doubleExpression(data.getValue().getSellPrice()));

        table.getColumns().addAll(nameCol, stockCol, priceCol);

        // Buttons
        Button addBtn = new Button("Add Product");
        addBtn.setOnAction(e -> {
            Product p = new Product();
            p.setName("New Product");
            p.setSku("SKU123");
            p.setCostPrice(5.0);
            p.setSellPrice(10.0);
            p.setStockQuantity(20);
            p.setReorderThreshold(5);
            p.setReorderAmount(10);

            productService.addProduct(p);
            products.setAll(productService.getAllProducts());
        });

        Button saleBtn = new Button("Sell Product");
        saleBtn.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    saleService.recordSale(selected, 1);
                    products.setAll(productService.getAllProducts());

                    if (selected.getStockQuantity() <= selected.getReorderThreshold()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Low stock for: " + selected.getName());
                        alert.show();
                    }
                } catch (RuntimeException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    alert.show();
                }
            }
        });

        HBox buttons = new HBox(10, addBtn, saleBtn);
        buttons.setPadding(new Insets(10));

        VBox root = new VBox(10, table, buttons);
        root.setPadding(new Insets(10));

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Inventory Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

