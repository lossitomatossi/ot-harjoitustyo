package foodtracker.utilities;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomas
 */
public class MiscUtilitiesTest {
    private MiscUtilities misc;
    
    public MiscUtilitiesTest() {
        this.misc = new MiscUtilities();
    }
    
    @Test
    public void testWhichFoodType() {
        String s = "p";
        assertEquals("prepared", misc.whichType(s));
    }
    
    @Test
    public void testWhichFoodType2() {
        String s = "f";
        assertEquals("fresh", misc.whichType(s));
    }
    
    @Test
    public void testWhichFoodType3() {
        String s = "ingre";
        assertEquals("ingredient", misc.whichType(s));
    }
    
    @Test
    public void testWhichQuantityType() {
        String s = "gra";
        assertEquals("grams", misc.whichQuantityType(s));
    }
    
    @Test
    public void testWhichQuantityType2() {
        String s = "l";
        assertEquals("liters", misc.whichQuantityType(s));
    }
    
    @Test
    public void testWhichQuantityType3() {
        String s = "peocws";
        assertEquals("pieces", misc.whichQuantityType(s));
    }
}
