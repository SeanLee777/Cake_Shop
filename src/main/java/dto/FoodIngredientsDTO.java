package dto;

public class FoodIngredientsDTO {
    private int id;
    private String name;
    private int quantity;
    private String unit;
    private double price;
    private String expirationDate; // 添加 expirationDate 字段

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpirationDate() { // 添加 getter 方法
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) { // 添加 setter 方法
        this.expirationDate = expirationDate;
    }
}
