package foodtracker.dao;

import foodtracker.database.Database;
import foodtracker.foodtypes.Food;
import foodtracker.foodtypes.FreshFood;
import foodtracker.utilities.LocalDateConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FoodDao implements Dao<Food, Integer> {
    
    private Database database;
    private LocalDateConverter converter;

    public FoodDao(Database database) {
        this.database = database;
        this.converter = converter;
    }
    @Override
    public Food findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Food> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FoodItem WHERE foodType = 'fresh'");
        
        ResultSet rs = stmt.executeQuery();
        List<Food> food = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String foodType = rs.getString("foodType");
            int quantity = rs.getInt("quantity");
            String quantityType = rs.getString("quantityType");
            String dateAdded = rs.getString("dateAdded");
            FreshFood freshToAdd = new FreshFood(id, name, foodType, quantity, quantityType, converter.stringToDate(dateAdded));
            //System.out.println(freshToAdd.toString());
            food.add(freshToAdd);
        }   
        
        rs.close();
        stmt.close();
        connection.close();
        
        
        return food;
    }

    @Override
    public Food saveOrUpdate(Food object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
