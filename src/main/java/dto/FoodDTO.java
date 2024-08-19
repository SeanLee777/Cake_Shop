package dto;

public class FoodDTO {
    private int id; // inventoryItemID
    private int inventoryId;
    private int productId;
    private String name;
    private int quantity;
    private String unit;
    private double price;

    // Default constructor
    public FoodDTO() {}

    // Parameterized constructor
    public FoodDTO(int id, int inventoryId, int productId, String name, int quantity, String unit, double price) {
        this.id = id;
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
    }

    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for inventoryId
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    // Getter and Setter for productId
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter and Setter for unit
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    // Getter and Setter for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
