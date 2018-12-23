package foodtracker.ui;

import java.sql.SQLException;
import javafx.stage.Stage;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        Stage stage = new Stage();
//        stage.setTitle("FoodTracker");
//        
//        stage.setScene(value);
        FoodTracker foodTracker = new FoodTracker();
        Stage primaryStage = new Stage();
        foodTracker.start(primaryStage);
    }
}
