package foodtracker.utilities;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

public class TableFood {
    private final SimpleStringProperty id;
    private final SimpleStringProperty foodName;
    private final SimpleStringProperty foodType;
    private final SimpleStringProperty quantity;
    private final SimpleStringProperty quantityType;
    private final SimpleStringProperty dateAdded;
    private final SimpleStringProperty expirationDate;
    private LocalDateConverter converter;

    public TableFood(int id, String foodName, String foodType, int quantity, String quantityType, LocalDate dateAdded, LocalDate expirationDate) {
        this.id = new SimpleStringProperty(Integer.toString(id));
        this.foodName = new SimpleStringProperty(foodName);
        this.foodType = new SimpleStringProperty(foodType);
        this.quantity = new SimpleStringProperty(Integer.toString(quantity));
        this.quantityType = new SimpleStringProperty(quantityType);
        this.converter = new LocalDateConverter();
        this.dateAdded = new SimpleStringProperty(converter.dateToString(dateAdded));
        if (foodType.equals("fresh")) {
            this.expirationDate = new SimpleStringProperty("");
        } else {
            this.expirationDate = new SimpleStringProperty(converter.dateToString(expirationDate));
        }
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

    public String getDateAdded() {
        return dateAdded.get();
    }

    public String getExpirationDate() {
        return expirationDate.get();
    }
}
