package foodtracker.foodtypes;


import foodtracker.utilities.LocalDateConverter;
import java.time.LocalDate;


public class PreparedFood extends Food implements Comparable<PreparedFood> {
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
    @Override
    public int compareTo(PreparedFood o) {
        int cmp = (expirationDate.getYear() - o.getExpirationDate().getYear());
        if (cmp == 0) {
            cmp = (expirationDate.getMonthValue() - o.getExpirationDate().getMonthValue());
            if (cmp == 0) {
                cmp = (expirationDate.getDayOfMonth() - o.getExpirationDate().getDayOfMonth());
            }
        }
        return cmp;
    }
}
