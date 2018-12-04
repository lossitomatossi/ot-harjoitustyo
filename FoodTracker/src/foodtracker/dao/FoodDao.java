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
        this.converter = new LocalDateConverter();
    }
    @Override
    public Food findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Food> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM FoodItem");
        ResultSet rs = stmt.executeQuery();
        List<Food> food = new ArrayList<>();
        while (rs.next()) {
            String dateAdded = rs.getString("dateAdded");
            FreshFood freshToAdd = new FreshFood(rs.getInt("id"), rs.getString("name"), rs.getString("foodType"), rs.getInt("quantity"), rs.getString("quantityType"), converter.stringToDate(dateAdded));
            //System.out.println(freshToAdd.toString());
            food.add(freshToAdd);
        }
        rs.close();
        stmt.close();
        connection.close();
        return food;
    }
    /*
    private PreparedFood collectPreparedFood(ResultSet rs) {
        return new PreparedFood()
    }
    */
    
    /*
    public int countAll() {
        // SELECT COUNT(*) FROM FoodItem;
    }
    */

    @Override
    public Food saveOrUpdate(Food object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
