//package foodtracker.dao;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import foodtracker.foodtypes.FreshFood;
//import foodtracker.database.Database;
//import foodtracker.utilities.LocalDateConverter;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.List;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// *
// * @author terala
// */
//public class FreshFoodDaoTest {
//    private Database database;
//    private FreshFoodDao freshFoodDao;
//    private FreshFood omena;
//    private LocalDateConverter converter;
//    
//    public FreshFoodDaoTest() {
//    }
//    
//    
//    @Before
//    public void setUp() throws ClassNotFoundException, SQLException {
//        this.database = new Database("jdbc:sqlite:food.db");
//        this.freshFoodDao = new FreshFoodDao(database);
//        this.omena = freshFoodDao.findOne(1);
//        this.converter = new LocalDateConverter();
//    }
//    
//    
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
//    
//    @Test
//    public void databaseContainsFreshFoods() throws SQLException {
//        List<FreshFood> freshFoods = freshFoodDao.findAll();
//        Assert.assertEquals(false, freshFoods.isEmpty());
//    }
//    
//    @Test
//    public void deletedItemsNotShowing() throws SQLException {
//        List<FreshFood> ff = freshFoodDao.findAll();
//        freshFoodDao.delete(200);
//        Object o = freshFoodDao.findOne(200);
//        Assert.assertEquals(null, o);
//    }
//    
//    @Test
//    public void findOneFindsName() {
//        Assert.assertEquals("omena", omena.getName());
//    }
//    
//    @Test
//    public void findOneFindsId() {
//        Assert.assertEquals(1, omena.getId());
//    }
//    
//    public void findOneFindsFoodType() {
//        Assert.assertEquals("fresh", omena.getFoodType());
//    }
//    
//    public void findOneFindsQuantity() {
//        Assert.assertEquals(13, omena.getQuantity());
//    }
//    
//    public void findOneFindsQuantityType() {
//        Assert.assertEquals("pieces", omena.getQuantityType());
//    }
//    
//    public void findOneFindsDateAdded() {
//        Assert.assertEquals("04.12.2018", converter.dateToString(omena.getDateAdded()));
//    }
//    
//    public void addToDatabaseTest() throws SQLException {
//        FreshFood sitruuna = new FreshFood(99999, "sitruuna", "fresh", 69, "pieces", LocalDate.now());
//        freshFoodDao.addToDatabase(sitruuna);
//        FreshFood haettuSitruuna = freshFoodDao.findOne(99999);
//        Assert.assertEquals(sitruuna.toString(), haettuSitruuna.toString());
//    }
//    
//    public void addToDatabaseTest2() throws SQLException {
//        FreshFood sitruuna = new FreshFood(99999, "sitruuna", null, 69, "pieces", LocalDate.now());
//        freshFoodDao.addToDatabase(sitruuna);
//        FreshFood haettuSitruuna = freshFoodDao.findOne(99999);
//        Assert.assertEquals("unknown", haettuSitruuna.getFoodType());
//    }
//    
//    
//}
