package foodtracker.dao;

import foodtracker.foodtypes.PreparedFood;
import foodtracker.database.Database;
import foodtracker.utilities.LocalDateConverter;
import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PreparedFoodDao implements Dao<PreparedFood, Integer> {
    
    private Database database;
    private LocalDateConverter converter;

    public PreparedFoodDao(Database database) {
        this.database = database;
        this.converter = new LocalDateConverter();
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
        LocalDate dateAdded = converter.stringToDate(rs.getString("dateAdded"));
        LocalDate expirationDate = converter.stringToDate(rs.getString("expirationDate"));
        PreparedFood pf = new PreparedFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), expirationDate, dateAdded, rs.getBoolean("opened"));
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
            LocalDate dateAdded = converter.stringToDate(rs.getString("dateAdded"));
            LocalDate expirationDate = converter.stringToDate(rs.getString("expirationDate"));
            PreparedFood pf = new PreparedFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), expirationDate, dateAdded, rs.getBoolean("opened"));
            preparedFoods.add(pf);
            System.out.println("Found the following prepared food from the database: " + pf.toString());
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
        stmt.setString(5, converter.dateToString(food.getExpirationDate()));
        //dateAdded
        stmt.setString(6, converter.dateToString(LocalDate.now()));
        stmt.setBoolean(7, food.isOpened());
        stmt.executeUpdate();
        
        conn.close();
    }
    
    public List<String> allPreparedInString() throws SQLException {
        List<String> list = new ArrayList<>();
        List<PreparedFood> prepared = findAll();
        for (int i = 0; i < prepared.size(); i++) {
            list.add(prepared.get(i).toString());
        }
        return list;
    }
}
