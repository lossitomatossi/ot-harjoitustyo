package FoodTypes;

import java.sql.Date;


public class FreshFood {
    private int id;
    private String name;
    private String foodType;
    private int quantity;
    private String quantityType;
    private Date dateAdded;

    public FreshFood(int id, String name, String foodType, int quantity, String quantityType, Date dateAdded) {
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

    public Date getDateAdded() {
        return dateAdded;
    }

    @Override
    public String toString() {
        return this.name + " " + this.quantity + " " + this.quantityType + " " + this.dateAdded;
    }
    
    
}
