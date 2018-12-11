package foodtracker.dao;

import foodtracker.database.Database;
import foodtracker.utilities.LocalDateConverter;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FoodDaoTest {
    private Database database;
    private FoodDao foodDao;
    private LocalDateConverter converter;
    
    public FoodDaoTest() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException {
        this.database = new Database("jdbc:sqlite:food.db");
        this.foodDao = new FoodDao(database);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    //requires that the database contains more than 0 items
    @Test
    public void testCountAll() throws SQLException {
        int x = foodDao.countAll();
        Assert.assertEquals(true, !(x < 0));
        
    }
}
