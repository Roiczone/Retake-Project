package inventory.UI;

import inventory.model.Product;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class MainController {

    public static Optional<Product> showDialog(Product existingProduct) {
        Dialog<Product> dialog = new Dialog<>();
        dialog.setTitle(existingProduct == null ? "Add Product" : "Edit Product");

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Form fields
        TextField skuField = new TextField();
        TextField nameField = new TextField();
        TextField descField = new TextField();
        TextField costField = new TextField();
        TextField sellField = new TextField();
        TextField stockField = new TextField();
        TextField thresholdField = new TextField();
        TextField reorderField = new TextField();

        if (existingProduct != null) {
            skuField.setText(existingProduct.getSku());
            nameField.setText(existingProduct.getName());
            descField.setText(existingProduct.getDescription());
            costField.setText(String.valueOf(existingProduct.getCostPrice()));
            sellField.setText(String.valueOf(existingProduct.getSellPrice()));
            stockField.setText(String.valueOf(existingProduct.getStockQuantity()));
            thresholdField.setText(String.valueOf(existingProduct.getReorderThreshold()));
            reorderField.setText(String.valueOf(existingProduct.getReorderAmount()));
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("SKU:"), 0, 0); grid.add(skuField, 1, 0);
        grid.add(new Label("Name:"), 0, 1); grid.add(nameField, 1, 1);
        grid.add(new Label("Description:"), 0, 2); grid.add(descField, 1, 2);
        grid.add(new Label("Cost Price:"), 0, 3); grid.add(costField, 1, 3);
        grid.add(new Label("Sell Price:"), 0, 4); grid.add(sellField, 1, 4);
        grid.add(new Label("Stock Quantity:"), 0, 5); grid.add(stockField, 1, 5);
        grid.add(new Label("Reorder Threshold:"), 0, 6); grid.add(thresholdField, 1, 6);
        grid.add(new Label("Reorder Amount:"), 0, 7); grid.add(reorderField, 1, 7);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Product p = (existingProduct != null) ? existingProduct : new Product();
                p.setSku(skuField.getText());
                p.setName(nameField.getText());
                p.setDescription(descField.getText());
                p.setCostPrice(Double.parseDouble(costField.getText()));
                p.setSellPrice(Double.parseDouble(sellField.getText()));
                p.setStockQuantity(Integer.parseInt(stockField.getText()));
                p.setReorderThreshold(Integer.parseInt(thresholdField.getText()));
                p.setReorderAmount(Integer.parseInt(reorderField.getText()));
                return p;
            }
            return null;
        });

        return dialog.showAndWait();
    }
}
