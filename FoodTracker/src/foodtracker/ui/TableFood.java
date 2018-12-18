package foodtracker.ui;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

public class TableFood {
    private final SimpleStringProperty id;
    private final SimpleStringProperty foodName;
    private final SimpleStringProperty foodType;
    private final SimpleStringProperty quantity;
    private final SimpleStringProperty quantityType;
    private final LocalDate dateAdded;
    private final LocalDate expirationDate;

    public TableFood(int id, String foodName, String foodType, int quantity, String quantityType, LocalDate dateAdded, LocalDate expirationDate) {
        String idString = Integer.toString(id);
        String qString = Integer.toString(quantity);
        this.id = new SimpleStringProperty(idString);
        this.foodName = new SimpleStringProperty(foodName);
        this.foodType = new SimpleStringProperty(foodType);
        this.quantity = new SimpleStringProperty(qString);
        this.quantityType = new SimpleStringProperty(quantityType);
        this.dateAdded = dateAdded;
        this.expirationDate = expirationDate;
        System.out.println("133313313313133");
    }

    public String getId() {
        return id.get();
    }

    public String getFoodName() {
        return foodName.get();
    }

    public String getFoodType() {
        return foodType.get();
    }

    public String getQuantity() {
        return quantity.get();
    }

    public String getQuantityType() {
        return quantityType.get();
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
