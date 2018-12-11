package foodtracker.ui;

import foodtracker.dao.FoodDao;
import foodtracker.dao.FoodIngredientDao;
import foodtracker.dao.FreshFoodDao;
import foodtracker.dao.PreparedFoodDao;
import foodtracker.foodtypes.FreshFood;
import foodtracker.foodtypes.PreparedFood;
import foodtracker.database.Database;
import foodtracker.foodtypes.FoodIngredient;
import foodtracker.utilities.LocalDateConverter;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class FoodTracker extends Application {

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        Database database = new Database("jdbc:sqlite:food.db");
        FoodDao allFoods = new FoodDao(database);
        PreparedFoodDao preparedFood = new PreparedFoodDao(database);
        FreshFoodDao freshFood = new FreshFoodDao(database);
        FoodIngredientDao foodIngredient = new FoodIngredientDao(database);
        LocalDateConverter converter = new LocalDateConverter();
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        
        //Top text for the application
        Text sceneTitle = new Text("Welcome to FoodTracker!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);
        
//        Text numberOfFoods = new Text("(currently tracking " + allFoods.countAll() + " foods)");
//        numberOfFoods.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 10));
//        grid.add(numberOfFoods, 1, 0);
        
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
        
        //limits the textField only to contain Integers
        /* the one below is for doubles 
        
            vendorList_textField_remaining.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    vendorList_textField_remaining.setText(oldValue);
                }
            }
        });

        
        */
        quantityTf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    quantityTf.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        
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
                            System.out.println("QuantityType changed to: " + toggler.getSelectedToggle().getUserData().toString());
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
        
        
        //toggle group for foodType
        final ToggleGroup foodType = new ToggleGroup();
        RadioButton fresh = new RadioButton("fresh");
        fresh.setUserData("fresh");
        fresh.setToggleGroup(foodType);
        fresh.setSelected(true);
        grid.add(fresh, 0, 4);
        RadioButton prepared = new RadioButton("prepared");
        prepared.setUserData("prepared");
        prepared.setToggleGroup(foodType);
        grid.add(prepared, 0, 5);
        RadioButton ingredient = new RadioButton("ingredient");
        ingredient.setUserData("ingredient");
        ingredient.setToggleGroup(foodType);
        grid.add(ingredient, 1, 4);
        
        foodType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                        if (foodType.getSelectedToggle() != null) {
                            System.out.println("FoodType changed to: " + foodType.getSelectedToggle().getUserData().toString());
                            getClass().getResourceAsStream(
                                foodType.getSelectedToggle().getUserData().toString()
                    );
                }
            }
            
        });
        
        //continue by making a list of what is Expiring today.
        Label expiringSoon = new Label("Expiring soon: ");
        grid.add(expiringSoon, 0, 8);
        GridPane expirationList = new GridPane();
        grid.add(expirationList, 1, 9);
        
        int expiring = 0;
        List<PreparedFood> expiringPrepared = preparedFood.findAll();
        List<FreshFood> expiringFresh = freshFood.findAll();
        
        for (int i = 0; i < expiringFresh.size(); i++) {
            Label ff = new Label(expiringFresh.get(i).toString());
            expirationList.add(ff, 0, i + expiring);
        }
        
        GridPane allfoodsgp = new GridPane();
        grid.add(allfoodsgp, 6, 2);
        List<String> all = new ArrayList<>();
        all.addAll(allFoods.findAll());
//        for (int i = 0; i < all.size(); i++) {
//            Label lb = new Label(all.get(i));
//            allfoodsgp.add(lb, 0, i + 1);
//        }
        
        //VBox containing all foods in a list grouped by their foodType
        VBox vbox = new VBox(8);
        ListView<String> lvTest = new ListView<String>();
        lvTest.getItems().addAll(all);
        vbox.setVgrow(lvTest, Priority.ALWAYS);
        vbox.getChildren().addAll(new Label("Tracked items: " + allFoods.countAll()), lvTest);
        grid.add(vbox, 10, 2);
        
        //VBox with the same information minus duplicates
        VBox collectionTest = new VBox();
        ListView<String> sorted = new ListView<String>();
        sorted.getItems().addAll(allFoods.sortedAll());
        collectionTest.setVgrow(sorted, Priority.ALWAYS);
        collectionTest.getChildren().addAll(new Label("Alphabetical order: "), sorted);
        grid.add(collectionTest, 12, 2);
                
        Button btn = new Button();
        btn.setText("Add a food to the database");
        grid.add(btn, 1, 7);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
            @Override
            public void handle(ActionEvent event) {
//                System.out.println("nappia painettu");
            //alert for numberfield being empty
                if (quantityTf.getText().isEmpty()) {
                    AlertMessage numberAlert = new AlertMessage("Input error", "Input a number for food quantity", "This field doesn't accept symbols other than numbers");
                }
                if (foodNameTf.getText().isEmpty()) {
                    AlertMessage nameAlert = new AlertMessage("Input error", "Input a name for the food", "The food field seems to be empty");
                }
                if (foodNameTf.getText().length() > 50) {
                    int length = foodNameTf.getText().length();
                    AlertMessage foodNameTooLong = new AlertMessage("Input error", "The name for the food is too long", "Try to reduce the length of your foodItem to under 50 characters, now it is: " + length + " characters long");
                }
                //add an alertmessage to notify the user that they havent selected the foodtype.
                
                try {
                    System.out.println("try lauseen sisällä käyty");
                    int amountOfFood = Integer.parseInt(quantityTf.getText());
                    String quantityType = toggler.getSelectedToggle().getUserData().toString();
                    String foodTypeString = foodType.getSelectedToggle().getUserData().toString();
                    System.out.println(foodTypeString);
                    if (foodTypeString.equals("prepared")) {
                        PreparedFood preparedToAdd = new PreparedFood(allFoods.findAll().size(), foodNameTf.getText(), foodTypeString, amountOfFood, quantityType, expiration.getValue(), LocalDate.now(), false);
                        preparedFood.addToDatabase(preparedToAdd);
                    } else if (foodTypeString.equals("fresh")) {
                        FreshFood freshToAdd = new FreshFood(allFoods.findAll().size(), foodNameTf.getText(), foodTypeString, amountOfFood, quantityType, LocalDate.now());
                        System.out.println(freshToAdd.toString());
                        freshFood.addToDatabase(freshToAdd);
                        if (expiringFresh.size() < freshFood.findAll().size()) {
                            int beginningSize = expiringFresh.size();
                            List<FreshFood> test = freshFood.findAll();
                            for (int i = beginningSize; i < test.size(); i++) {
                            Label ff = new Label(test.get(i).toString());
                            expirationList.add(ff, 0, i + beginningSize);
                        }
                        }
                    } else {
                        FoodIngredient ingredientToAdd = new FoodIngredient(19, foodNameTf.getText(), foodTypeString, amountOfFood, quantityType, expiration.getValue(), LocalDate.now());
                        foodIngredient.addToDatabase(ingredientToAdd);
                    }

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                
                
                    
                
            }
        });
        
        //List of all the foods in string format and in labels
        
        
       
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
        
        Scene scene = new Scene(root, 800, 800);
        
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
