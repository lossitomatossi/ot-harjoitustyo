/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodtracker.utilities;

import foodtracker.foodtypes.FoodIngredient;
import foodtracker.foodtypes.FreshFood;
import foodtracker.foodtypes.PreparedFood;
import java.time.LocalDate;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author terala
 */
public class TableFoodTest {

    private TableFood tf;
    private TableFood test;
    private LocalDateConverter converter;

    @Before
    public void setUp() {
        this.tf = new TableFood(13, "name", "type", 69, "qType", LocalDate.now(), LocalDate.of(2020, Month.APRIL, 20));
        test = new TableFood(13, "name", "type", 22, "q", LocalDate.now(), LocalDate.of(2020, Month.APRIL, 20));
    }

    @Test
    public void getIdTest() {
        assertEquals("13", tf.getId());
    }

    @Test
    public void getFoodNameTest() {
        assertEquals("name", tf.getFoodName());
    }

    @Test
    public void getFoodTypeTest() {
        assertEquals("type", tf.getFoodType());
    }

    @Test
    public void getQuantityTest() {
        assertEquals("69", tf.getQuantity());
    }

    @Test
    public void getQuantityTypeTest() {
        assertEquals("qType", tf.getQuantityType());
    }

    @Test
    public void getDateAddedTest() {
        assertEquals(test.getDateAdded(), tf.getDateAdded());
    }

    @Test
    public void getExpirationTest() {
        assertEquals(test.getExpirationDate(), tf.getExpirationDate());
    }

    @Test
    public void tfToFreshTest() {
        FreshFood ff = new FreshFood(13, "name", "fresh", 22, "pieces", LocalDate.now());
        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.MAX);
        FreshFood methodTest = testing.tableFoodToFresh();
        assertEquals(ff.getName(), methodTest.getName());
    }

    @Test
    public void tfToFreshTest2() {
        FreshFood ff = new FreshFood(13, "name", "fresh", 22, "pieces", LocalDate.now());
        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.MAX);
        FreshFood methodTest = testing.tableFoodToFresh();
        assertEquals(ff.getDateAdded(), methodTest.getDateAdded());
    }

    @Test
    public void tfToFreshTest3() {
        FreshFood ff = new FreshFood(13, "name", "fresh", 22, "pieces", LocalDate.now());
        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.MAX);
        FreshFood methodTest = testing.tableFoodToFresh();
        assertEquals(ff.getFoodType(), methodTest.getFoodType());
    }

    @Test
    public void tfToFreshTest4() {
        FreshFood ff = new FreshFood(13, "name", "fresh", 22, "pieces", LocalDate.now());
        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.MAX);
        FreshFood methodTest = testing.tableFoodToFresh();
        assertEquals(ff.getId(), methodTest.getId());
    }

    @Test
    public void tfToFreshTest5() {
        FreshFood ff = new FreshFood(13, "name", "fresh", 22, "pieces", LocalDate.now());
        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.MAX);
        FreshFood methodTest = testing.tableFoodToFresh();
        assertEquals(ff.getQuantity(), methodTest.getQuantity());
    }

    @Test
    public void tfToFreshTest6() {
        FreshFood ff = new FreshFood(13, "name", "fresh", 22, "pieces", LocalDate.now());
        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.MAX);
        FreshFood methodTest = testing.tableFoodToFresh();
        assertEquals(ff.getQuantityType(), methodTest.getQuantityType());
    }

//    @Test
//    public void tfToIngredientTest() {
//        FoodIngredient fi = new FoodIngredient(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        FoodIngredient methodTest = testing.tableFoodToIngredient();
//        assertEquals(fi.getName(), methodTest.getName());
//    }
//    
//    @Test
//    public void tfToIngredientTest2() {
//        FoodIngredient fi = new FoodIngredient(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        FoodIngredient methodTest = testing.tableFoodToIngredient();
//        assertEquals(fi.getId(), methodTest.getId());
//    }
//    
//    @Test
//    public void tfToIngredientTest3() {
//        FoodIngredient fi = new FoodIngredient(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        FoodIngredient methodTest = testing.tableFoodToIngredient();
//        assertEquals(fi.getDateAdded(), methodTest.getDateAdded());
//    }
//    
//    @Test
//    public void tfToIngredientTest4() {
//        FoodIngredient fi = new FoodIngredient(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        FoodIngredient methodTest = testing.tableFoodToIngredient();
//        assertEquals(fi.getExpirationDate(), methodTest.getExpirationDate());
//    }
//    
//    @Test
//    public void tfToIngredientTest6() {
//        FoodIngredient fi = new FoodIngredient(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        FoodIngredient methodTest = testing.tableFoodToIngredient();
//        assertEquals(fi.getFoodType(), methodTest.getFoodType());
//    }
//    
//    @Test
//    public void tfToIngredientTest7() {
//        FoodIngredient fi = new FoodIngredient(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        TableFood testing = new TableFood(13, "name", "fresh", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
//        FoodIngredient methodTest = testing.tableFoodToIngredient();
//        assertEquals(fi.getQuantity(), methodTest.getQuantity());
//    }
//    
    @Test
    public void tfToIngredientTest8() {
        FoodIngredient fi = new FoodIngredient(13, "name", "prepared", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
        TableFood testing = new TableFood(13, "name", "prepared", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
        FoodIngredient methodTest = testing.tableFoodToIngredient();
        assertEquals(fi.getQuantityType(), methodTest.getQuantityType());
    }
    
     @Test
    public void tfToPreparedTest8() {
        PreparedFood fi = new PreparedFood(13, "name", "prepared", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12), false);
        TableFood testing = new TableFood(13, "name", "prepared", 22, "pieces", LocalDate.now(), LocalDate.of(2018, Month.MARCH, 12));
        PreparedFood methodTest = testing.tableFoodToPrepared();
        assertEquals(fi.getQuantityType(), methodTest.getQuantityType());
    }

}
