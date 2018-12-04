package foodtracker.foodtypes;

import java.time.LocalDate;


public class FoodIngredient extends Food {

    public FoodIngredient(int id, String name, String foodType, int quantity, String quantityType, LocalDate dateAdded) {
        super(id, name, foodType, quantity, quantityType, dateAdded);
    }

    @Override
    public String toString() {
        return getFoodType() + " " + getName() + " " + getQuantity() + " " + getQuantityType() + " added: " + getDateAdded();
    }
    
    

}
