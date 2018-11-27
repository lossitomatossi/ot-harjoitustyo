package foodtracker.ui;

import java.sql.SQLException;
import javafx.stage.Stage;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        FoodTracker foodTracker = new FoodTracker();
        Stage primaryStage = new Stage();
        foodTracker.start(primaryStage);
    }
}
