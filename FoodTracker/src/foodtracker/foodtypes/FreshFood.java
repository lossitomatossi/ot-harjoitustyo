package foodtracker.foodtypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class FreshFood {
    private int id;
    private String name;
    private String foodType;
    private int quantity;
    private String quantityType;
    private LocalDate dateAdded;

    public FreshFood(int id, String name, String foodType, int quantity, String quantityType, LocalDate dateAdded) {
        this.id = id;
        this.name = name;
        this.foodType = foodType;
        this.quantity = quantity;
        this.quantityType = quantityType;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedString = this.dateAdded.format(formatter);
        return this.name + " " + this.quantity + " " + this.quantityType + " " + formattedString;
    }
    
    
}
