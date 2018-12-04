package foodtracker.foodtypes;


import foodtracker.utilities.LocalDateConverter;
import java.time.LocalDate;


public class PreparedFood extends Food {
    private LocalDate expirationDate;
    private boolean opened;

    public PreparedFood(int id, String name, String foodType, int quantity, String quantityType, LocalDate expirationDate, LocalDate dateAdded, boolean opened) {
        super(id, name, foodType, quantity, quantityType, dateAdded);
        this.expirationDate = expirationDate;
        this.opened = opened;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean isOpened() {
        return opened;
    }

    public void open() {
        this.opened = true;
    }

    @Override
    public String toString() {
        LocalDateConverter converter = new LocalDateConverter();
        return super.getName() + " " + super.getQuantity() + " " + super.getQuantityType() + " " + converter.dateToString(super.getDateAdded()) + " - "  + converter.dateToString(getExpirationDate());
    }
    
    
}
