package foodtracker.dao;

import foodtracker.foodtypes.FreshFood;
import foodtracker.database.Database;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FreshFoodDao implements Dao<FreshFood, Integer> {
    
    private Database database;

    public FreshFoodDao(Database database) {
        this.database = database;
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
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String foodType = rs.getString("foodType");
        int quantity = rs.getInt("quantity");
        String quantityType = rs.getString("quantityType");
        String dateAdded = rs.getString("dateAdded");
        
        FreshFood ff = null;
        try {
            ff = new FreshFood(id, name, foodType, quantity, quantityType, stringToDate(dateAdded));
        } catch (ParseException ex) {
            Logger.getLogger(FreshFoodDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String foodType = rs.getString("foodType");
            int quantity = rs.getInt("quantity");
            String quantityType = rs.getString("quantityType");
            String dateAdded = rs.getString("dateAdded");

            FreshFood freshToAdd = null;
            try {
                freshToAdd = new FreshFood(id, name, foodType, quantity, quantityType, stringToDate(dateAdded));
            } catch (ParseException ex) {
                Logger.getLogger(FreshFoodDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (freshToAdd != null) {freshFoods.add(freshToAdd); }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addToDatabase(FreshFood food) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:food.db");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (name, foodType, quantity, quantityType, dateAdded) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, food.getName());
        if (food.getFoodType() == null) {
            stmt.setString(2, "unknown");
        } else {
            stmt.setString(2, food.getFoodType());
        }
        stmt.setInt(3, food.getQuantity());
        stmt.setString(4, food.getQuantityType());
        //dateAdded
//        java.sql.Date sqlDate = new jav0a.sql.Date(System.);
        stmt.setString(5, Date.valueOf(LocalDate.now()).toString());
        stmt.executeUpdate();
        System.out.println("FreshFoods adding works");
        
        conn.close();
    }
    
    private Date stringToDate(String string) throws ParseException {
        Date date1 = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(string);
        return date1;
    }
}
