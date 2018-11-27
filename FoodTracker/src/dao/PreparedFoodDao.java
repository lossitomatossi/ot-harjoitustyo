package dao;

import foodtypes.PreparedFood;
import database.Database;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PreparedFoodDao implements Dao<PreparedFood, Integer> {
    
    private Database database;

    public PreparedFoodDao(Database database) {
        this.database = database;
    }

    @Override
    public PreparedFood findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FoodItem WHERE id = ? AND foodType = 'prepared'");
        stmt.setObject(1, key);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        PreparedFood pf =  new PreparedFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"),
                    rs.getString("quantityType"), rs.getDate("expirationDate"), rs.getDate("dateAdded"), rs.getBoolean("opened"));
        rs.close();
        stmt.close();
        connection.close();
        return pf;
    }

    @Override
    public List<PreparedFood> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FoodItem WHERE foodType = 'prepared'");
        
        ResultSet rs = stmt.executeQuery();
        List<PreparedFood> preparedFoods = new ArrayList<>();
        while (rs.next()) {
            PreparedFood preparedFood = new PreparedFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"),
                    rs.getString("quantityType"), rs.getDate("expirationDate"), rs.getDate("dateAdded"), rs.getBoolean("opened"));
            preparedFoods.add(preparedFood);
        }
        rs.close();
        stmt.close();
        connection.close();
        return preparedFoods;
    }
    
    //method for showing prepared foods that are expiring soon
    public List<PreparedFood> findAllExpiringSoon() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FoodItem WHERE foodType = 'prepared'");
        
        ResultSet rs = stmt.executeQuery();
        List<PreparedFood> expiringPreparedFoods = new ArrayList<>();
        
        return expiringPreparedFoods;
    }

    @Override
    public PreparedFood saveOrUpdate(PreparedFood object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addToDatabase(PreparedFood food) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:food.db");
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO FoodItem (name, foodType, quantity, quantityType, expirationDate, dateAdded, opened) VALUES (?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, food.getName());
        if (food.getFoodType() == null) {
            stmt.setString(2, "unknown");
        } else {
            stmt.setString(2, food.getFoodType());
        }
        System.out.println("toimiiko");
        stmt.setInt(3, food.getQuantity());
        stmt.setString(4, food.getQuantityType());
        //expirationDate
        stmt.setDate(5, Date.valueOf(LocalDate.now()));
        //dateAdded
        stmt.setDate(6, Date.valueOf(LocalDate.now()));
        stmt.setBoolean(7, food.isOpened());
        stmt.executeUpdate();
        
        conn.close();
    }
}
