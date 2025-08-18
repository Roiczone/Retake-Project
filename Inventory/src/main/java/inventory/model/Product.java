package inventory.model;

public class Product {

    private long id;
    private String sku;
    private String name;
    private String description;
    private double costPrice;
    private double sellPrice;
    private int stockQuantity;
    private int reorderThreshold;
    private int reorderAmount;

    public Product() {
        this.id = 0;
        this.sku = "";
        this.name = "";
        this.description = "";
        this.costPrice = 0.0;
        this.sellPrice = 0.0;
        this.stockQuantity = 0;
        this.reorderThreshold = 0;
        this.reorderAmount = 0;
    }

    // Getters & Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getCostPrice() { return costPrice; }
    public void setCostPrice(double costPrice) { this.costPrice = costPrice; }
    public double getSellPrice() { return sellPrice; }
    public void setSellPrice(double sellPrice) { this.sellPrice = sellPrice; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public int getReorderThreshold() { return reorderThreshold; }
    public void setReorderThreshold(int reorderThreshold) { this.reorderThreshold = reorderThreshold; }
    public int getReorderAmount() { return reorderAmount; }
    public void setReorderAmount(int reorderAmount) { this.reorderAmount = reorderAmount; }

    public double getProfitMargin() { return sellPrice - costPrice; }
}

