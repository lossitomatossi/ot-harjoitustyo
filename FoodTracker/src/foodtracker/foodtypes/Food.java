package foodtracker.foodtypes;

import java.time.LocalDate;


public abstract class Food {
    protected int id;
    protected String name;
    protected String foodType;
    protected int quantity;
    protected String quantityType;
    protected LocalDate dateAdded;
//    private LocalDate expirationDate;
//    private Boolean opened;

    
    //if foodType = fresh, no expiration or opened
    //if foodType = prepared, expiration and opened
    //if foodType = ingredient, same as expiration
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
