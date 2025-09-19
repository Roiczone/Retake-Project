package inventory.Application;

import inventory.Service.ProductService;
import inventory.Service.SaleService;
import inventory.UI.MainController;
import inventory.model.Product;
import inventory.model.Sale;
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
    private TableView<Product> productTable = new TableView<>();

    private ObservableList<Sale> sales;
    private TableView<Sale> salesTable = new TableView<>();

    private Label revenueLabel;
    private Label profitLabel;

    @Override
    public void start(Stage primaryStage) {

        products = FXCollections.observableArrayList(productService.getAllProducts());
        productTable.setItems(products);

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

        productTable.getColumns().addAll(nameCol, stockCol, priceCol);

        // 🔹 Highlight low-stock rows in red
        productTable.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                if (product == null || empty) {
                    setStyle("");
                } else if (product.getStockQuantity() <= product.getReorderThreshold()) {
                    setStyle("-fx-background-color: #ffcccc;"); // light red
                } else {
                    setStyle("");
                }
            }
        });

        // 🔹 Show popup alert if low-stock products exist at startup
        var lowStockProducts = productService.getLowStockProducts();
        if (!lowStockProducts.isEmpty()) {
            StringBuilder msg = new StringBuilder("Low stock detected:\n");
            lowStockProducts.forEach(p ->
                    msg.append(p.getName()).append(" (").append(p.getStockQuantity()).append(" left)\n")
            );
            new Alert(Alert.AlertType.WARNING, msg.toString()).show();
        }

        ComboBox<String> sortFilterBox = new ComboBox<>();
        sortFilterBox.getItems().addAll(
                "Default",
                "Stock (Low → High)",
                "Stock (High → Low)",
                "Profit Margin (High → Low)",
                "Low Stock Only"
        );
        sortFilterBox.setValue("Default");

        sortFilterBox.setOnAction(e -> {
            String choice = sortFilterBox.getValue();
            switch (choice) {
                case "Stock (Low → High)" -> products.setAll(
                        productService.getAllProducts().stream()
                                .sorted((a, b) -> Integer.compare(a.getStockQuantity(), b.getStockQuantity()))
                                .toList()
                );
                case "Stock (High → Low)" -> products.setAll(
                        productService.getAllProducts().stream()
                                .sorted((a, b) -> Integer.compare(b.getStockQuantity(), a.getStockQuantity()))
                                .toList()
                );
                case "Profit Margin (High → Low)" -> products.setAll(
                        productService.getAllProducts().stream()
                                .sorted((a, b) -> Double.compare(b.getProfitMargin(), a.getProfitMargin()))
                                .toList()
                );
                case "Low Stock Only" -> products.setAll(
                        productService.getLowStockProducts()
                );
                default -> products.setAll(productService.getAllProducts());
            }
        });

        Button addBtn = new Button("Add Product");
        addBtn.setOnAction(e -> {
            MainController.showDialog(null).ifPresent(product -> {
                productService.addProduct(product);
                products.setAll(productService.getAllProducts());
            });
        });

        Button editBtn = new Button("Edit Product");
        editBtn.setOnAction(e -> {
            Product selected = productTable.getSelectionModel().getSelectedItem();
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
            Product selected = productTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                productService.deleteProduct(selected.getId());
                products.setAll(productService.getAllProducts());
            } else {
                new Alert(Alert.AlertType.WARNING, "Select a product to delete!").show();
            }
        });

        Button saleBtn = new Button("Sell Product");
        saleBtn.setOnAction(e -> {
            Product selected = productTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                TextInputDialog dialog = new TextInputDialog("1");
                dialog.setTitle("Sell Product");
                dialog.setHeaderText("Sell " + selected.getName());
                dialog.setContentText("Enter quantity to sell:");

                dialog.showAndWait().ifPresent(input -> {
                    try {
                        int quantity = Integer.parseInt(input);
                        if (quantity > 0) {
                            saleService.recordSale(selected, quantity);
                            products.setAll(productService.getAllProducts());
                            sales.setAll(saleService.getAllSales());

                            updateAnalytics();

                            if (selected.getStockQuantity() <= selected.getReorderThreshold()) {
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "Low stock for: " + selected.getName());
                                alert.show();
                            }
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Quantity must be positive!").show();
                        }
                    } catch (NumberFormatException ex) {
                        new Alert(Alert.AlertType.ERROR, "Invalid number!").show();
                    } catch (RuntimeException ex) {
                        new Alert(Alert.AlertType.ERROR, ex.getMessage()).show();
                    }
                });
            } else {
                new Alert(Alert.AlertType.WARNING, "Select a product to sell!").show();
            }
        });


        Button restockBtn = new Button("Restock Product");
        restockBtn.setOnAction(e -> {
            Product selected = productTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                TextInputDialog dialog = new TextInputDialog("10");
                dialog.setTitle("Restock Product");
                dialog.setHeaderText("Restock " + selected.getName());
                dialog.setContentText("Enter quantity to add:");

                dialog.showAndWait().ifPresent(input -> {
                    try {
                        int quantity = Integer.parseInt(input);
                        if (quantity > 0) {
                            selected.setStockQuantity(selected.getStockQuantity() + quantity);
                            productService.updateProduct(selected);
                            products.setAll(productService.getAllProducts());
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Quantity must be positive!").show();
                        }
                    } catch (NumberFormatException ex) {
                        new Alert(Alert.AlertType.ERROR, "Invalid number!").show();
                    }
                });
            } else {
                new Alert(Alert.AlertType.WARNING, "Select a product to restock!").show();
            }
        });

        HBox productButtons = new HBox(10, addBtn, editBtn, deleteBtn, saleBtn, restockBtn);
        productButtons.setPadding(new Insets(10));

        VBox productLayout = new VBox(10, sortFilterBox, productTable, productButtons);
        productLayout.setPadding(new Insets(10));

        Tab productsTab = new Tab("Products", productLayout);
        productsTab.setClosable(false);

        sales = FXCollections.observableArrayList(saleService.getAllSales());
        salesTable.setItems(sales);

        TableColumn<Sale, String> saleProductCol = new TableColumn<>("Product");
        saleProductCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getProduct().getName())
        );

        TableColumn<Sale, Integer> saleQtyCol = new TableColumn<>("Quantity");
        saleQtyCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getQuantity()).asObject()
        );

        TableColumn<Sale, Double> salePriceCol = new TableColumn<>("Price");
        salePriceCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(data.getValue().getSalePrice()).asObject()
        );

        TableColumn<Sale, Double> saleTotalCol = new TableColumn<>("Total");
        saleTotalCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(data.getValue().getTotal()).asObject()
        );

        TableColumn<Sale, String> saleDateCol = new TableColumn<>("Date");
        saleDateCol.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(data.getValue().getSaleDate().toString())
        );

        salesTable.getColumns().addAll(saleProductCol, saleQtyCol, salePriceCol, saleTotalCol, saleDateCol);

        revenueLabel = new Label("Total Revenue: $" + saleService.getTotalRevenue());
        profitLabel = new Label("Total Profit: $" + saleService.getTotalProfit());

        VBox salesLayout = new VBox(10, salesTable, revenueLabel, profitLabel);
        salesLayout.setPadding(new Insets(10));

        Tab salesTab = new Tab("Sales", salesLayout);
        salesTab.setClosable(false);

        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(productsTab, salesTab);

        primaryStage.setScene(new Scene(tabPane, 800, 500));
        primaryStage.setTitle("Inventory Management");
        primaryStage.show();
    }

    private void updateAnalytics() {
        revenueLabel.setText("Total Revenue: $" + saleService.getTotalRevenue());
        profitLabel.setText("Total Profit: $" + saleService.getTotalProfit());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
