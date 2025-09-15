package inventory.Application;

import inventory.Service.ProductService;
import inventory.Service.SaleService;
import inventory.UI.MainController;
import inventory.model.Product;
import inventory.UI.MainController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {

    private ProductService productService = new ProductService();
    private SaleService saleService = new SaleService();
    private ObservableList<Product> products;

    private TableView<Product> table = new TableView<>();

    @Override
    public void start(Stage primaryStage) {


        products = FXCollections.observableArrayList(productService.getAllProducts());
        table.setItems(products);


        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getName())
        );

        TableColumn<Product, Integer> stockCol = new TableColumn<>("Stock");
        stockCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getStockQuantity()).asObject()
        );

        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(data.getValue().getSellPrice()).asObject()
        );

        table.getColumns().addAll(nameCol, stockCol, priceCol);


        Button addBtn = new Button("Add Product");
        addBtn.setOnAction(e -> {
            MainController.showDialog(null).ifPresent(product -> {
                productService.addProduct(product);
                products.setAll(productService.getAllProducts());
            });
        });

        Button editBtn = new Button("Edit Product");
        editBtn.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                MainController.showDialog(selected).ifPresent(updated -> {
                    productService.updateProduct(updated);
                    products.setAll(productService.getAllProducts());
                });
            } else {
                new Alert(Alert.AlertType.WARNING, "Select a product to edit!").show();
            }
        });

        Button deleteBtn = new Button("Delete Product");
        deleteBtn.setOnAction(e -> {
            Product selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                productService.deleteProduct(selected.getId());
                products.setAll(productService.getAllProducts());
            } else {
                new Alert(Alert.AlertType.WARNING, "Select a product to delete!").show();
            }
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

        HBox buttons = new HBox(10, addBtn, editBtn, deleteBtn, saleBtn);

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

