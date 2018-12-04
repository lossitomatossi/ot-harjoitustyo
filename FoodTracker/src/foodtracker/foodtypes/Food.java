package foodtracker.foodtypes;

import java.time.LocalDate;


public class Food {
    private int id;
    private String name;
    private String foodType;
    private int quantity;
    private String quantityType;
    private LocalDate dateAdded;

    public Food(int id, String name, String foodType, int quantity, String quantityType, LocalDate dateAdded) {
        this.id = id;
        this.name = name;
        this.foodType = foodType;
        this.quantity = quantity;
        this.quantityType = quantityType;
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public String getFoodType() {
        return foodType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }
    
    
}
