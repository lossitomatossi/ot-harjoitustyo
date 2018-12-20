package foodtracker.ui;

import foodtracker.dao.FoodDao;
import foodtracker.database.Database;
import foodtracker.utilities.TableFood;
import foodtracker.utilities.LocalDateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DataEditingView extends Application {
    private TableView<TableFood> table = new TableView<TableFood>();
    private final ObservableList<TableFood> data =
            FXCollections.observableArrayList();
    final HBox hb = new HBox();

    @Override
    public void start(Stage stage) throws ClassNotFoundException, SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Edit database");
        stage.setWidth(600);
        stage.setHeight(600);
        Database database = new Database("jdbc:sqlite:food.db");
        FoodDao allFoods = new FoodDao(database);
        LocalDateConverter converter = new LocalDateConverter();
        
        table = new TableView();
        table.setEditable(true);
        
        final Label label = new Label("Tracked Foods: ");
        label.setFont(new Font("Arial", 20));
        
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
            new PropertyValueFactory<TableFood, String>("expirationDate")
        );
        
        //funktio create columns
        
        table.setItems(data);
        
        
        table.getColumns().addAll(columnName, columnFoodType, columnAmount, columnAmountType,
                columnDateAdded, columnExpiration);
        table.setMinSize(800, 100);
        data.addAll(allFoods.tableFiller());
        
        final TextField addFood = new TextField();
        addFood.setPromptText("Name of food");
        addFood.setMaxWidth(columnName.getPrefWidth());
        final TextField addType = new TextField();
        addType.setPromptText("Type of food");
        final TextField addAmount = new TextField();
        addAmount.setPromptText("Amount");
        addAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    addAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        addAmount.setMaxWidth(columnAmount.getPrefWidth());
        final TextField addAmountType = new TextField();
        addAmountType.setPromptText("Unit of amount");
        final DatePicker addDate = new DatePicker();
        addDate.setMaxWidth(columnExpiration.getPrefWidth());
        
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String type = "";
                if (addType.getText().startsWith("p")) {
                    type = "pieces";
                } else if (addType.getText().startsWith("g")){
                    type = "grams";
                } else if (addType.getText().startsWith("l")) {
                    type = "liters";
                } else {
                    type = "unknown";
                }
                String date = converter.dateToString(addDate.getValue());
                data.add(new TableFood(
                        0,
                        addFood.getText(),
                        type,
                        Integer.parseInt(addAmount.getText()),
                        addAmountType.getText(),
                        LocalDate.now(),
                        addDate.getValue()));
                        
                addFood.clear();
                addType.clear();
                addAmount.clear();
                addAmountType.clear();
                addDate.setValue(null);
            }
        });
        
        hb.getChildren().addAll(addFood, addType, addAmount, addAmountType, addButton);
        hb.setSpacing(3);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, hb);
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
