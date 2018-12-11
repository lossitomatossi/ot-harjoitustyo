package foodtracker.foodtypes;

import java.time.LocalDate;

/**
 * The base for all different types of foods,
 * contains all their shared attributes
 * @author terala
 */
public abstract class Food {

    /**
     * Id number as according to the database, a unique identifier for all foods
     */
    protected int id;

    /**
     * The name of a food item
     */
    protected String name;

    /**
     * Defines in which of the different FoodType classes the food is added to.
     * The three types are: fresh, prepared and ingredient.
     */
    protected String foodType;

    /**
     * The amount of food listed in the database
     */
    protected int quantity;

    /**
     * Tells the user whether the food's amount is marked in pieces, liters or
     * grams.
     */
    protected String quantityType;

    /**
     * Tells the user when the item was added to the database
     */
    protected LocalDate dateAdded;
//    private LocalDate expirationDate;
//    private Boolean opened;

    
    //if foodType = fresh, no expiration or opened
    //if foodType = prepared, expiration and opened
    //if foodType = ingredient, same as expiration

    /**
     *
     * @param id id in the database
     * @param name name in the database
     * @param foodType foodType in the database
     * @param quantity quantity in the database
     * @param quantityType quantityType in the database
     * @param dateAdded dateAdded in the database
     */
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
