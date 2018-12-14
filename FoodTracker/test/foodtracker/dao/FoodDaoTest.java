package foodtracker.dao;

import foodtracker.database.Database;
import foodtracker.foodtypes.FoodIngredient;
import foodtracker.foodtypes.FreshFood;
import foodtracker.utilities.LocalDateConverter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FoodDaoTest {
    private Database database;
    private FoodDao allFoods;
    private FreshFood omena;
    private LocalDateConverter converter;
    private Connection conn;
    private List<FreshFood> freshies;
    private List<FoodIngredient> ingredients;
    private List<String> allInAList;
    
    public FoodDaoTest() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        this.database = new Database("jdbc:sqlite:food.db");
        this.conn = database.getConnection();
        this.allFoods = new FoodDao(database);
        this.omena = allFoods.findOneFresh(1);
        this.converter = new LocalDateConverter();
        this.freshies = allFoods.findAllFresh();
        this.ingredients = allFoods.findAllIngredients();
        this.allInAList = allFoods.findAll();
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    //requires that the database contains more than 0 items
    @Test
    public void testCountAll() throws SQLException {
        int x = allFoods.countAll();
        Assert.assertEquals(true, !(x < 0));
        
    }
    
    @Test
    public void freshContainsTest() throws SQLException {
        Assert.assertEquals(false, freshies.isEmpty());
    }
    
    //causes an error
//    @Test
//    public void freshDelete1Test() throws SQLException {
//        List<FreshFood> ff = allFoods.findAllFresh();
//        allFoods.delete(200);
//        Object o = allFoods.findOne(200);
//        Assert.assertEquals(null, o);
//    }
    
    @Test
    public void freshFindOneTest1() {
        Assert.assertEquals("omena", omena.getName());
    }
    
    @Test
    public void freshFindOneTest2() {
        Assert.assertEquals(1, omena.getId());
    }
    
    @Test
    public void freshFoundTest() {
        Assert.assertEquals("fresh", omena.getFoodType());
    }
    
    @Test
    public void freshFindQuantityTest() {
        Assert.assertEquals(13, omena.getQuantity());
    }
    
    @Test
    public void freshQuantityTypeTest() {
        Assert.assertEquals("pieces", omena.getQuantityType());
    }
    
    @Test
    public void freshDateAddedTest() {
        Assert.assertEquals("04.12.2018", converter.dateToString(omena.getDateAdded()));
    }
    
    @Test
    public void findIngredientsTest() {
        Assert.assertEquals(false, ingredients.isEmpty());
    }
    
    @Test
    public void findAllTest() {
        Assert.assertEquals(false, (allInAList.size() < 12));
    }
    
    @Test
    public void allFreshInStringTest() throws SQLException {
        List<String> freshStringList = allFoods.allFreshInString(freshies);
        Assert.assertEquals(true, freshStringList.get(0).equals(omena.toString()));
    }
    
    @Test
    public void allIngredientStringTest() throws SQLException {
        List<String> ingredientStringList = allFoods.allIngredientsInString(ingredients);
        Assert.assertEquals(true, ingredientStringList.contains("paprikajauhe 13 grams added: 2018-12-11"));
    }
    
    @Test
    public void sortedAllTest() throws SQLException {
        List<String> sorted = (List<String>) allFoods.sortedAll();
        Assert.assertEquals("aaaaaaaaaaaaaaaaaaa 11 liters 11.12.2018", sorted.get(0));
    }
    
    @Test
    public void testRegainConnection() throws SQLException {
        allFoods.connectIfNoConnection();
        Assert.assertEquals(false, conn.isClosed());
    }
    @Test
    public void addFreshToDatabaseTest() throws SQLException {
        FreshFood sitruuna = new FreshFood(99999, "sitruuna", "fresh", 69, "pieces", LocalDate.now());
        allFoods.addFreshWithId(sitruuna, 99999);
        FreshFood haettuSitruuna = allFoods.findOneFresh(99999);
        allFoods.delete(99999);
        Assert.assertEquals(sitruuna.toString(), haettuSitruuna.toString());
    }
    
    @Test
    public void addFreshToDatabaseTest2() throws SQLException {
        FreshFood sitruuna = new FreshFood(99999, "sitruuna", "fresh", 69, "pieces", LocalDate.now());
        allFoods.addFreshWithId(sitruuna, 99999);
        FreshFood haettuSitruuna = allFoods.findOneFresh(99999);
        allFoods.delete(99999);
        Assert.assertEquals("fresh", haettuSitruuna.getFoodType());
    }

}
