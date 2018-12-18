package foodtracker.foodtypes;

import java.time.LocalDate;

public class TableFood {
    private final String foodName;
    private final String foodType;
    private final int quantity;
    private final String quantityType;
    private final LocalDate dateAdded;
    private final LocalDate expirationDate;

    public TableFood(String foodName, String foodType, int quantity, String quantityType, LocalDate dateAdded, LocalDate expirationDate) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.quantity = quantity;
        this.quantityType = quantityType;
        this.dateAdded = dateAdded;
        this.expirationDate = expirationDate;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
