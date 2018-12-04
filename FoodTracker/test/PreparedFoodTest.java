/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import foodtracker.foodtypes.PreparedFood;
import foodtracker.utilities.LocalDateConverter;
import java.time.LocalDate;
import java.time.Month;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author terala
 */
public class PreparedFoodTest {
    private PreparedFood pf;
    private LocalDateConverter converter;
    
    public PreparedFoodTest() {
        this.converter = new LocalDateConverter();
    }

    @Before
    public void setUp() {
        int id = 200;
        String name = "lihis";
        String foodType = "prepared";
        int quantity = 3;
        String quantityType = "pieces";
        LocalDate expirationDate = LocalDate.of(2018, 12, 12);
        LocalDate dateAdded = LocalDate.now();
        Boolean opened = true;
        
        pf = new PreparedFood(id, name, foodType, quantity, quantityType, expirationDate, dateAdded, opened);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void getIdWorks() {
        Assert.assertEquals(200, pf.getId());
    }
    
    @Test
    public void getNameWorks() {
        Assert.assertEquals("lihis", pf.getName());
    }
    
    @Test
    public void getFoodTypeWorks() {
        Assert.assertEquals("prepared", pf.getFoodType());
    }
    
    @Test
    public void getQuantityWorks() {
        Assert.assertEquals(3, pf.getQuantity());
    }
    
    @Test
    public void isOpenedWorks() {
        Assert.assertEquals(true, pf.isOpened());
    }
    
    @Test
    public void getQuantityTypeWorks() {
        Assert.assertEquals("pieces", pf.getQuantityType());
    }
    
    @Test
    public void getExpirationDateWorks() {
        Assert.assertEquals(LocalDate.of(2018, 12, 12), pf.getExpirationDate());
    }
    
    @Test
    public void getDateAddedWorks() {
        Assert.assertEquals(LocalDate.now(), pf.getDateAdded());
    }
    
    @Test
    public void toStringWorks() {
        String dateNow = converter.dateToString(LocalDate.now());
        String expiration = converter.dateToString(LocalDate.of(2018, 12, 12));
        Assert.assertEquals("lihis 3 pieces " + dateNow + " - " + expiration, pf.toString());
    }
    
    
}
