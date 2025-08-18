package inventory.model;

import java.time.LocalDateTime;

public class Sale {
    private long id;
    private Product product;
    private int quantity;
    private double salePrice;
    private double total;
    private LocalDateTime saleDate;

    public Sale() {
        this.id = 0;
        this.product = new Product();
        this.quantity = 0;
        this.salePrice = 0.0;
        this.total = 0.0;
        this.saleDate = LocalDateTime.now();
    }

    // Getters & Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getSalePrice() { return salePrice; }
    public void setSalePrice(double salePrice) { this.salePrice = salePrice; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public LocalDateTime getSaleDate() { return saleDate; }
    public void setSaleDate(LocalDateTime saleDate) { this.saleDate = saleDate; }
}


