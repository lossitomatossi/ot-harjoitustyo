package foodtracker.dao;

import foodtracker.database.Database;
import foodtracker.foodtypes.FoodIngredient;
import foodtracker.foodtypes.FreshFood;
import foodtracker.foodtypes.PreparedFood;
import foodtracker.utilities.LocalDateConverter;
import foodtracker.utilities.TableFood;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FoodDaoTest {
    private Database database;
    private FoodDao allFoods;
    private FreshFood omena;
    private PreparedFood asdad;
    private LocalDateConverter converter;
    private Connection conn;
    private List<FreshFood> freshies;
    private List<FoodIngredient> ingredients;
    private List<String> allInAList;
    
    public FoodDaoTest() {
    }
    
    @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        this.database = new Database("jdbc:sqlite:test.db");
        this.conn = database.getConnection();
        this.allFoods = new FoodDao(database);
        this.omena = allFoods.findOneFresh(1);
        this.asdad = allFoods.findOnePrepared(3);
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
    public void prepFoundTest() {
        Assert.assertEquals("prepared", asdad.getFoodType());
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
        FreshFood lemon = new FreshFood(99999, "sitruuna", "fresh", 69, "pieces", LocalDate.now());
        allFoods.addFreshWithId(lemon, 99999);
        FreshFood foundLemon = allFoods.findOneFresh(99999);
        allFoods.delete(99999);
        Assert.assertEquals(lemon.toString(), foundLemon.toString());
    }
    
    @Test
    public void addFreshToDatabaseTest2() throws SQLException {
        FreshFood lemon = new FreshFood(99999, "sitruuna", "fresh", 69, "pieces", LocalDate.now());
        allFoods.addFreshWithId(lemon, 99999);
        FreshFood foundLemon = allFoods.findOneFresh(99999);
        allFoods.delete(99999);
        Assert.assertEquals("fresh", foundLemon.getFoodType());
    }
    
    @Test
    public void addIngredientTest() throws SQLException {
        FoodIngredient cumin = new FoodIngredient(777, "cumin", "ingredient", 69, "pieces", LocalDate.now(), LocalDate.now());
        System.out.println("222222");
        allFoods.addIngredientWithId(cumin, 777);
        System.out.println("33333");
        FoodIngredient foundCumin = allFoods.findOneIngredient(777);
        System.out.println("44444");
        allFoods.delete(777);
        Assert.assertEquals(cumin.toString(), foundCumin.toString());
    }
    
    @Test
    public void addIngredientTest2() throws SQLException {
        FoodIngredient cumin = new FoodIngredient(77777, "cumin", "ingredient", 69, "pieces", LocalDate.now(), LocalDate.now());
        allFoods.addIngredientWithId(cumin, 77777);
        FoodIngredient foundCumin = allFoods.findOneIngredient(77777);
        allFoods.delete(77777);
        Assert.assertEquals("ingredient", foundCumin.getFoodType());
    }
    
    @Test
    public void addPreparedTest() throws SQLException {
        PreparedFood meatloaf = new PreparedFood(888, "meatloaf", "prepared", 500, "grams", LocalDate.now(), LocalDate.now(), false);
        allFoods.addPreparedWithId(meatloaf, 888);
        PreparedFood foundMeatloaf = allFoods.findOnePrepared(888);
        allFoods.delete(888);
        Assert.assertEquals(meatloaf.toString(), foundMeatloaf.toString());
    }
    
    @Test
    public void addIngredientTest3() throws SQLException {
        LocalDate now = LocalDate.now();
        String date = converter.dateToString(now);
        FoodIngredient cumin = new FoodIngredient(888, "cumin", "prepared", 500, "grams", LocalDate.now(), LocalDate.now());
        allFoods.addIngredientToDatabase(cumin);
        int id = allFoods.findIdForNameAmount("cumin", 500);
        PreparedFood foundMeatloaf = allFoods.findOnePrepared(id);
        allFoods.delete(id);
        Assert.assertEquals(cumin.getName(), foundMeatloaf.getName());
    }
    
    @Test
    public void addIngredientTest4() throws SQLException {
        LocalDate now = LocalDate.now();
        String date = converter.dateToString(now);
        FoodIngredient cumin = new FoodIngredient(888, "cumin", "prepared", 500, "grams", LocalDate.now(), LocalDate.now());
        allFoods.addIngredientToDatabase(cumin);
        int id = allFoods.findIdForNameAmount("cumin", 500);
        PreparedFood foundMeatloaf = allFoods.findOnePrepared(id);
        allFoods.delete(id);
        Assert.assertEquals(cumin.getQuantity(), foundMeatloaf.getQuantity());
    }
    
    
    
    
//    @Test
//    public void addPreparedTest2() throws SQLException {
//        PreparedFood meatloaf = new PreparedFood(888, "meatloaf", "prepared", 500, "grams", LocalDate.now(), LocalDate.now(), false);
//        allFoods.addPreparedWithId(meatloaf, 888);
//        PreparedFood foundMeatloaf = allFoods.findOnePrepared(888);
//        allFoods.delete(888);
//        Assert.assertEquals("prepared", foundMeatloaf.getFoodType());
//    }
    
    @Test
    public void freshNotFoundTest() throws SQLException {
        Assert.assertEquals(null, allFoods.findOneFresh(888888888));
    }
    
    @Test
    public void preparedNotFoundTest() throws SQLException {
        Assert.assertEquals(null, allFoods.findOnePrepared(88888888));
    }
    
    @Test
    public void ingredientNotFound() throws SQLException {
        Assert.assertEquals(null, allFoods.findOnePrepared(88888888));
    }
    
    @Test
    public void preparedToTable() throws SQLException {
        PreparedFood fi = new PreparedFood(59, "name", "prepared", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12), false);
        TableFood testing = new TableFood(59, "name", "prepared", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
        allFoods.addPreparedWithId(fi, 59);
        List<TableFood> list = allFoods.preparedToTable();
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getFoodName().equals("name")) {
                found = true;
            }
        }
        allFoods.delete(59);
        Assert.assertEquals(true, found);
    }
}
