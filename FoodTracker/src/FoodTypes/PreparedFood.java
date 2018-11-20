package FoodTypes;

import java.util.Date;


public class PreparedFood {
    private int id;
    private String name;
    private String foodType;
    private int quantity;
    private String quantityType;
    private Date expirationDate;
    private Date dateAdded;
    private boolean opened;

    public PreparedFood(int id, String name, String foodType, int quantity, String quantityType, Date expirationDate, Date dateAdded, boolean opened) {
        this.id = id;
        this.name = name;
        this.foodType = foodType;
        this.quantity = quantity;
        this.quantityType = quantityType;
        this.expirationDate = expirationDate;
        this.dateAdded = dateAdded;
        this.opened = opened;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public String getFoodType() {
        return foodType;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public boolean isOpened() {
        return opened;
    }
}
