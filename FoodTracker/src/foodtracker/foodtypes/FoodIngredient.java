package foodtracker.foodtypes;

import java.time.LocalDate;


public class FoodIngredient extends Food {
    private LocalDate expirationDate;

    public FoodIngredient(int id, String name, String foodType, int quantity, String quantityType, LocalDate dateAdded, LocalDate expirationDate) {
        super(id, name, foodType, quantity, quantityType, dateAdded);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public String toString() {
        return "Ingredient: " + getName() + " " + getQuantity() + " " + getQuantityType() + " added: " + getExpirationDate();
    }
    
    

}
