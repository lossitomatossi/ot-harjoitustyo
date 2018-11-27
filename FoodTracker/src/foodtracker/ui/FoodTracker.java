package foodtracker.ui;

import Dao.FreshFoodDao;
import Dao.PreparedFoodDao;
import FoodTypes.FreshFood;
import FoodTypes.PreparedFood;
import database.Database;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.*;

public class FoodTracker extends Application {

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        Database database = new Database("jdbc:sqlite:food.db");
        PreparedFoodDao preparedFood = new PreparedFoodDao(database);
        FreshFoodDao freshFood = new FreshFoodDao(database);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        //Top text for the application
        Text sceneTitle = new Text("Welcome to FoodTracker!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);
        
        //food item data gathering
        Label foodNameLb = new Label("Food item:");
        grid.add(foodNameLb, 0, 1);
        TextField foodNameTf = new TextField();
        grid.add(foodNameTf, 1, 1);
        
        
        //food quantity data gathering
        Label quantityLb = new Label("Quantity of food item:");
        grid.add(quantityLb, 0, 2);
        TextField quantityTf = new TextField();
        grid.add(quantityTf, 1, 2);
        
        
        //Radiobuttons for quantity
        RadioButton grams = new RadioButton("grams");
        grams.setUserData("grams");
        grid.add(grams, 2, 1);
        RadioButton liters = new RadioButton("liters");
        liters.setUserData("liters");
        grid.add(liters, 2, 2);
        RadioButton pieces = new RadioButton("pieces");
        pieces.setUserData("pieces");
        grid.add(pieces, 2, 3);
        
        
        //togglegroup for checking which button is selected
        final ToggleGroup toggler = new ToggleGroup();
        grams.setToggleGroup(toggler);
        grams.setSelected(true);
        liters.setToggleGroup(toggler);
        pieces.setToggleGroup(toggler);
        
        
        //Checks which radiobutton is toggled
        toggler.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                        if (toggler.getSelectedToggle() != null) {
                            System.out.println("Toggle changed to: " + toggler.getSelectedToggle().getUserData().toString());
                            getClass().getResourceAsStream(
                                toggler.getSelectedToggle().getUserData().toString()
                    );
                }
            }
            
        });
        
        //use toggler.getSelectedToggle().getUserData() to find out which button
        //is used so you can get good sql queries in place!
        
        
        
        //Ability to pick expiration date in a calendar format
        Label expirationLb = new Label("Expiration date: ");
        grid.add(expirationLb, 0 , 3);
        DatePicker expiration = new DatePicker();
        expiration.setOnAction(event -> {
            System.out.println("Selected date: " + expiration.getValue());
            System.out.println("Date now: " + Date.valueOf(LocalDate.now()));
        });
        grid.add(expiration, 1, 3);
        
        Button btn = new Button();
        btn.setText("Add a food to the database");
        grid.add(btn, 1, 4);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
            @Override
            public void handle(ActionEvent event) {
//                System.out.println("nappia painettu");
                try {
//                    System.out.println("try lauseen sisällä käyty");
                    int amountOfFood = Integer.parseInt(quantityTf.getText());
                    String selected = toggler.getSelectedToggle().getUserData().toString();
                    System.out.println(selected);
                    PreparedFood foodToAdd = new PreparedFood((preparedFood.findAll().size() +1), foodNameTf.getText(), null, amountOfFood, selected, Date.valueOf(expiration.getValue()), Date.valueOf(LocalDate.now()), false);
                    System.out.println(foodToAdd);
                    preparedFood.addToDatabase(foodToAdd);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        
        
        //continue by making a list of what is Expiring today.
        Label expiringSoon = new Label("Expiring soon: ");
        grid.add(expiringSoon, 0, 5);
        GridPane expirationList = new GridPane();
        grid.add(expirationList, 1, 6);
        
        int expiring = 0;
        List<PreparedFood> expiringPrepared = preparedFood.findAll();
        List<FreshFood> expiringFresh = freshFood.findAll();
        for (int i = 0; i < expiringFresh.size(); i++) {
            Label ff = new Label(expiringFresh.get(i).toString());
            expirationList.add(ff, 0, i + expiring);
        }
        
        List<Object> kaikki = new ArrayList<>();
        kaikki.addAll(expiringFresh);
        kaikki.addAll(expiringPrepared);
        for (int i = 0; i < kaikki.size(); i++) {
            Label ff = new Label(kaikki.get(i).toString());
            expirationList.add(ff, 0, i + expiring);
        }
        
        
        
        
        
        
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
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
