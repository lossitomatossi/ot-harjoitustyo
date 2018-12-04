package foodtracker.foodtypes;

import foodtracker.utilities.LocalDateConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class FreshFood extends Food {
    private int id;
    private String name;
    private String foodType;
    private int quantity;
    private String quantityType;
    private LocalDate dateAdded;

    public FreshFood(int id, String name, String foodType, int quantity, String quantityType, LocalDate dateAdded) {
        super(id, name, foodType, quantity, quantityType, dateAdded);
    }

    @Override
    public String toString() {
        LocalDateConverter converter = new LocalDateConverter();
        String formattedString = converter.dateToString(super.getDateAdded());
            return super.getName() + " " + super.getQuantity() + " " + super.getQuantityType() + " " + formattedString;
    }
    
    
}
