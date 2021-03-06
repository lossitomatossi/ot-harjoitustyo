package foodtracker.ui;

import foodtracker.utilities.AlertMessage;
import foodtracker.utilities.TableFood;
import foodtracker.dao.FoodDao;
import foodtracker.foodtypes.FreshFood;
import foodtracker.foodtypes.PreparedFood;
import foodtracker.database.Database;
import foodtracker.foodtypes.FoodIngredient;
import foodtracker.utilities.LocalDateConverter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

//HBox ja BorderPane
public class FoodTracker extends Application {

    private TableView<TableFood> table = new TableView<TableFood>();
    private final ObservableList<TableFood> data
            = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) throws ClassNotFoundException, SQLException {
        Database database = new Database("jdbc:sqlite:food.db");
        FoodDao allFoods = new FoodDao(database);
        //FreshFoodDao freshFood = new FreshFoodDao(database);
        LocalDateConverter converter = new LocalDateConverter();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
//        VBox menuLeft = new VBox();
//        grid.add(menuLeft, 1, 1);
//        VBox menuRight = new VBox();
//        grid.add(menuRight, 2, 1);

        //Top text for the application
        Text sceneTitle = new Text("Welcome to FoodTracker!");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

//        Text numberOfFoods = new Text("(currently tracking " + allFoods.countAll() + " foods)");
//        numberOfFoods.setFont(Font.font("Tahoma", FontWeight.SEMI_BOLD, 10));
//        grid.add(numberOfFoods, 1, 0);
        //food item data gathering
        Label labelFoodName = new Label("Food item:");
        grid.add(labelFoodName, 0, 1);
//        menuLeft.getChildren().add(lbFoodName);
        TextField textFieldFoodName = new TextField();
        grid.add(textFieldFoodName, 1, 1);
//        menuRight.getChildren().add(tfFoodName);

        //food quantity data gathering
        Label labelQuantity = new Label("Quantity of food item:");
        grid.add(labelQuantity, 0, 2);
//        menuLeft.getChildren().add(lbQuantity);
//        menuLeft.getChildren().add(new Label("Paskaa"));
        TextField textFieldQuantity = new TextField();
        grid.add(textFieldQuantity, 1, 2);
//        menuRight.getChildren().add(tfQuantity);

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
        textFieldQuantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldQuantity.setText(newValue.replaceAll("[^\\d]", ""));
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

        //Ability to pick expiration date in a calendar format
        Label expirationLb = new Label("Expiration date: ");
        grid.add(expirationLb, 0, 3);
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

        Button buttonEditView = new Button();
        buttonEditView.setText("Edit the database");
        buttonEditView.setOnAction(e -> {
            Stage edit = new Stage();
            DataEditingView editView = new DataEditingView();
            try {
                editView.start(edit);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FoodTracker.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FoodTracker.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.close();
        });
        grid.add(buttonEditView, 0, 9);

        //continue by making a list of what is Expiring today.
//        Label expiringSoon = new Label("Expiring soon: ");
//        grid.add(expiringSoon, 0, 9);
        List<String> all = new ArrayList<>();
        all.addAll(allFoods.findAll());

        TableView<TableFood> table = new TableView();
        table.setEditable(false);

        final Label label = new Label("Tracked Foods: ");
        label.setFont(new Font("Arial", 20));

        TableColumn columnId = new TableColumn("Id");
        columnId.setCellValueFactory(
                new PropertyValueFactory<TableFood, String>("id"));

        TableColumn columnName = new TableColumn("Food");
        columnName.setCellValueFactory(
                new PropertyValueFactory<TableFood, String>("foodName"));

        TableColumn columnFoodType = new TableColumn("Type");
        columnFoodType.setCellValueFactory(
                new PropertyValueFactory<TableFood, String>("foodType"));

        TableColumn columnAmount = new TableColumn("Amount");
        columnAmount.setCellValueFactory(
                new PropertyValueFactory<TableFood, String>("quantity"));

        TableColumn columnAmountType = new TableColumn("amountType");
        columnAmountType.setCellValueFactory(
                new PropertyValueFactory<TableFood, String>("quantityType"));

        TableColumn columnDateAdded = new TableColumn("Added");
        columnDateAdded.setCellValueFactory(
                new PropertyValueFactory<TableFood, String>("dateAdded"));

        TableColumn columnExpiration = new TableColumn("Expiration");
        columnExpiration.setCellValueFactory(
                new PropertyValueFactory<TableFood, String>("expirationDate"));

        //funktio create columns
        table.setItems(data);

        table.getColumns().addAll(columnId, columnName, columnFoodType, columnAmount, columnAmountType,
                columnDateAdded, columnExpiration);
        table.setMinSize(800, 100);
        data.addAll(allFoods.tableFiller());

        table.getSortOrder().add(columnExpiration);

        Button btn = new Button();
        btn.setText("Add a food to the database");
        grid.add(btn, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
            @Override
            public void handle(ActionEvent event) {
//                System.out.println("nappia painettu");
                //alert for numberfield being empty
                if (textFieldQuantity.getText().isEmpty()) {
                    AlertMessage numberAlert = new AlertMessage("Input error", "Input a number for food quantity", "This field doesn't accept symbols other than numbers");
                }
                if (textFieldFoodName.getText().isEmpty()) {
                    AlertMessage nameAlert = new AlertMessage("Input error", "Input a name for the food", "The food field seems to be empty");
                }
                if (textFieldFoodName.getText().length() > 50) {
                    int length = textFieldFoodName.getText().length();
                    AlertMessage foodNameTooLong = new AlertMessage("Input error", "The name for the food is too long", "Try to reduce the length of your foodItem to under 50 characters, now it is: " + length + " characters long");
                }
                //add an alertmessage to notify the user that they havent selected the foodtype.

                try {
                    System.out.println("try lauseen sisällä käyty");
                    int amountOfFood = Integer.parseInt(textFieldQuantity.getText());
                    String quantityType = toggler.getSelectedToggle().getUserData().toString();
                    String foodTypeString = foodType.getSelectedToggle().getUserData().toString();
                    System.out.println(foodTypeString);
                    if (foodTypeString.equals("prepared")) {
                        PreparedFood preparedToAdd = new PreparedFood(allFoods.findAll().size(), textFieldFoodName.getText(), foodTypeString, amountOfFood, quantityType, expiration.getValue(), LocalDate.now(), false);
                        allFoods.addPreparedToDatabase(preparedToAdd);
                    } else if (foodTypeString.equals("fresh")) {
                        FreshFood freshToAdd = new FreshFood(allFoods.findAll().size(), textFieldFoodName.getText(), foodTypeString, amountOfFood, quantityType, LocalDate.now());
                        System.out.println(freshToAdd.toString());
                        allFoods.addFreshToDatabase(freshToAdd);
                    } else {
                        FoodIngredient ingredientToAdd = new FoodIngredient(19, textFieldFoodName.getText(), foodTypeString, amountOfFood, quantityType, expiration.getValue(), LocalDate.now());
                        allFoods.addIngredientToDatabase(ingredientToAdd);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        Label labelDelete = new Label("Delete by id: ");
        TextField textFieldDelete = new TextField();
        textFieldDelete.setPrefWidth(20);
        Button delete = new Button();
        delete.setText("Delete");
        grid.add(labelDelete, 0, 8);
        grid.add(textFieldDelete, 1, 8);
        grid.add(delete, 2, 8);

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int id = Integer.parseInt(textFieldDelete.getText());
                try {
                    if (!(id > allFoods.countAll())) {
                        allFoods.delete(id);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FoodTracker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        HBox hbox = new HBox();
        StackPane root = new StackPane();
        root.getChildren().add(hbox);

        hbox.getChildren().add(grid);
        hbox.getChildren().add(table);

//        root.getChildren().add(table);
//        root.setAlignment(table, Pos.TOP_RIGHT);
        Scene scene = new Scene(root, 800, 800);

        stage.setTitle("FoodTracker");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
