package foodtracker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.ButtonGroup;

public class FoodTracker extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        //Top text for the application
        Text sceneTitle = new Text("Welcome to FoodTracker!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);
        
        //food item data gathering
        Label foodName = new Label("Food item:");
        grid.add(foodName, 0, 1);
        TextField foodTF = new TextField();
        grid.add(foodTF, 1, 1);
        
        
        //food quantity data gathering
        Label quantity = new Label("Quantity of food item:");
        grid.add(quantity, 0, 2);
        TextField quantityTF = new TextField();
        grid.add(quantityTF, 1, 2);
        
        
        //Finding out the type of quantity
        RadioButton grams = new RadioButton("grams");
        grid.add(grams, 2, 1);
        RadioButton liters = new RadioButton("liters");
        grid.add(liters, 2, 2);
        RadioButton pieces = new RadioButton("pieces");
        grid.add(pieces, 2, 3);
        ButtonGroup group = new ButtonGroup();
        
        
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        //for showing the application
        StackPane root = new StackPane();
        root.getChildren().add(grid);
        
        Scene scene = new Scene(root, 600, 500);
        
        primaryStage.setTitle("FoodTracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
