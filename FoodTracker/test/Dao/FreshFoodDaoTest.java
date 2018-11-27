package Dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import foodtracker.dao.FreshFoodDao;
import foodtracker.foodtypes.FreshFood;
import foodtracker.database.Database;
import java.sql.SQLException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author terala
 */
public class FreshFoodDaoTest {
    private Database database;
    private FreshFoodDao freshFoodDao;
    
    public FreshFoodDaoTest() {
    }
    
    
    @Before
    public void setUp() throws ClassNotFoundException {
        database = new Database("jdbc:sqlite:food.db");
        freshFoodDao = new FreshFoodDao(database);
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void databaseContainsFreshFoods() throws SQLException {
        List<FreshFood> freshFoods = freshFoodDao.findAll();
        Assert.assertEquals(false, freshFoods.isEmpty());
    }
}
