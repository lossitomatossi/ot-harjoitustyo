package foodtracker.dao;

import foodtracker.database.Database;
import foodtracker.foodtypes.Food;
import foodtracker.utilities.LocalDateConverter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;


public class FoodDao {
    
    private Database database;
    private LocalDateConverter converter;
    private FreshFoodDao freshFoods;
    private PreparedFoodDao preparedFoods;
    private FoodIngredientDao ingredients;
    
    public FoodDao(Database database) {
        this.database = database;
        this.converter = new LocalDateConverter();
        this.freshFoods = new FreshFoodDao(database);
        this.preparedFoods = new PreparedFoodDao(database);
        this.ingredients = new FoodIngredientDao(database);
    }
    
    public Food findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    //Combines all toStrings of foodtypes to list
    public List<String> findAll() throws SQLException {
        List<String> allFoods = new ArrayList<>();
        List<String> freshies = freshFoods.allFreshInString();
        List<String> prepareds = preparedFoods.allPreparedInString();
        List<String> ingredientes = ingredients.allIngredientsInString();
        allFoods.addAll(freshies);
        allFoods.addAll(prepareds);
        allFoods.addAll(ingredientes);
        return allFoods;
    }
    
    public Collection<String> sortedAll() throws SQLException {
        Collection<String> sorted = new TreeSet<String>(Collator.getInstance());
        List<String> all = findAll();
        sorted.addAll(all);
        return sorted;
    }
    
    
    public int countAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM FoodItem");
        ResultSet rs = stmt.executeQuery();
        int num = rs.getInt(1);
        return num;
    }

    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
