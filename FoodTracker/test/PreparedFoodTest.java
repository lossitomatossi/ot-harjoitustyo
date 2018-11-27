/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import foodtracker.foodtypes.PreparedFood;
import java.sql.Date;
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
    public PreparedFoodTest() {
    }

    @Before
    public void setUp() {
        pf = new PreparedFood(8, "lihis", "prepared", 3, "pcs" , Date.valueOf(LocalDate.of(2018, Month.MARCH, 30)), Date.valueOf(LocalDate.now()), true);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void getIdWorks() {
        Assert.assertEquals(8, pf.getId());
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
        Assert.assertEquals("pcs", pf.getQuantityType());
    }
    
    @Test
    public void getExpirationDateWorks() {
        Assert.assertEquals(Date.valueOf(LocalDate.of(2018, Month.MARCH, 30)), pf.getExpirationDate());
    }
    
    @Test
    public void getDateAddedWorks() {
        Assert.assertEquals(Date.valueOf(LocalDate.now()), pf.getDateAdded());
    }
    
    @Test
    public void toStringWorks() {
        Assert.assertEquals("lihis 3 (pcs) 2018-03-30", pf.toString());
    }
    
    
}
