package foodtracker.ui;

import foodtracker.dao.FoodDao;
import foodtracker.database.Database;
import foodtracker.utilities.TableFood;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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

public class DataEditingView extends Application {
    private TableView<TableFood> table = new TableView<TableFood>();
    private final ObservableList<TableFood> data =
            FXCollections.observableArrayList();
    final HBox hb = new HBox();

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        Database database = new Database("jdbc:sqlite:food.db");
        FoodDao allFoods = new FoodDao(database);
        
        TableView<TableFood> table = new TableView();
        table.setEditable(false);
        
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
            new PropertyValueFactory<TableFood, String>("expirationDate"));
        
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
        hb.getChildren().addAll(addFood, addType, addAmount, addAmountType, addDate);
        hb.setSpacing(3);
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, hb);

//        StackPane root = new StackPane();
//        root.getChildren().add(table);

        Scene scene = new Scene(vbox, 600, 600);
        primaryStage.setTitle("Edit the database!");
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
