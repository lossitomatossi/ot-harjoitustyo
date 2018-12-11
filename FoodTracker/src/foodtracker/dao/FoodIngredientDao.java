package foodtracker.dao;

import foodtracker.database.Database;
import foodtracker.foodtypes.FoodIngredient;
import foodtracker.utilities.LocalDateConverter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodIngredientDao implements Dao<FoodIngredient, Integer> {
    private Database database;
    private LocalDateConverter converter;

    public FoodIngredientDao(Database database) {
        this.database = database;
        this.converter = new LocalDateConverter();
    }

    @Override
    public FoodIngredient findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FoodItem WHERE id = ? AND foodType = 'ingredient'");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        String dateAdded = rs.getString("dateAdded");
        String expirationDate = rs.getString("expirationDate");
        FoodIngredient fi = new FoodIngredient(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), converter.stringToDate(dateAdded), converter.stringToDate(expirationDate));
        
        rs.close();
        stmt.close();
        conn.close();
        return fi;
    }

    @Override
    public List<FoodIngredient> findAll() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM FoodItem WHERE foodType = 'ingredient'");
        
        ResultSet rs = stmt.executeQuery();
        List<FoodIngredient> ingredients = new ArrayList<>();
        while (rs.next()) {
            LocalDate expirationDate = converter.stringToDate(rs.getString("expirationDate"));
            String dateAdded = rs.getString("dateAdded");
            FoodIngredient freshToAdd = new FoodIngredient(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), converter.stringToDate(dateAdded), expirationDate);
            System.out.println("Found the following ingredient from the database: " + freshToAdd.toString());
            ingredients.add(freshToAdd);
        }
        rs.close();
        stmt.close();
        conn.close();
        return ingredients;
    }

    @Override
    public FoodIngredient saveOrUpdate(FoodIngredient object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addToDatabase(FoodIngredient ingredient) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:food.db");
        System.out.println("attempted to connect to the database");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (name, foodType, quantity, quantityType, dateAdded, expirationDate) VALUES (?, ?, ?, ?, ?, ?)");
        stmt.setString(1, ingredient.getName());
        if (ingredient.getFoodType() == null) {
            stmt.setString(2, "unknown");
        } else {
            stmt.setString(2, ingredient.getFoodType());
        }
        stmt.setInt(3, ingredient.getQuantity());
        stmt.setString(4, ingredient.getQuantityType());
        stmt.setString(5, converter.dateToString(ingredient.getDateAdded()));
        stmt.setString(6, converter.dateToString(ingredient.getExpirationDate()));
        stmt.executeUpdate();
        System.out.println("Ingredient adding works" + converter.dateToString(ingredient.getDateAdded()));
        
        conn.close();
    }
    
    public List<String> allIngredientsInString() throws SQLException {
        List<String> list = new ArrayList<>();
        List<FoodIngredient> fresh = findAll();
        for (int i = 0; i < fresh.size(); i++) {
            list.add(fresh.get(i).toString());
        }
        return list;
    }

}