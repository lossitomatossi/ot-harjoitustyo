package foodtracker.dao;

import foodtracker.foodtypes.FreshFood;
import foodtracker.database.Database;
import foodtracker.utilities.LocalDateConverter;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FreshFoodDao implements Dao<FreshFood, Integer> {
    
    private Database database;
    private LocalDateConverter converter;

    public FreshFoodDao(Database database) {
        this.database = database;
        this.converter = new LocalDateConverter();
    }

    @Override
    public FreshFood findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FoodItem WHERE id = ? AND foodType = 'fresh'");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        String dateAdded = rs.getString("dateAdded");
        FreshFood ff = new FreshFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), converter.stringToDate(dateAdded));
        
        rs.close();
        stmt.close();
        connection.close();
        return ff;
    }

    @Override
    public List<FreshFood> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FoodItem WHERE foodType = 'fresh'");
        
        ResultSet rs = stmt.executeQuery();
        List<FreshFood> freshFoods = new ArrayList<>();
        while (rs.next()) {
            String dateAdded = rs.getString("dateAdded");
            FreshFood freshToAdd = new FreshFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), converter.stringToDate(dateAdded));
            System.out.println("Found the following fresh food from the database: " + freshToAdd.toString());
            freshFoods.add(freshToAdd);
        }
        rs.close();
        stmt.close();
        connection.close();
        return freshFoods;
    }

    @Override
    public FreshFood saveOrUpdate(FreshFood object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:food.db");
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM FoodItem WHERE id = ?");
        stmt.setInt(1, key);
        stmt.executeUpdate();
    }
    
    public void addToDatabase(FreshFood food) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:food.db");
        System.out.println("attempted to connect to the database");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (name, foodType, quantity, quantityType, dateAdded) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, food.getName());
        if (food.getFoodType() == null) {
            stmt.setString(2, "unknown");
        } else {
            stmt.setString(2, food.getFoodType());
        }
        stmt.setInt(3, food.getQuantity());
        stmt.setString(4, food.getQuantityType());
        stmt.setString(5, converter.dateToString(food.getDateAdded()));
        stmt.executeUpdate();
        
        conn.close();
    }
    
    public List<String> allFreshInString() throws SQLException {
        List<String> list = new ArrayList<>();
        List<FreshFood> fresh = findAll();
        for (int i = 0; i < fresh.size(); i++) {
            list.add(fresh.get(i).toString());
        }
        return list;
    }
}
