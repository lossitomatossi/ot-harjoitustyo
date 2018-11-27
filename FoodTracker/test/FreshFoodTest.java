import foodtracker.foodtypes.FreshFood;
import java.sql.Date;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FreshFoodTest {
    private FreshFood ff;
    
    public FreshFoodTest() {
    }

    @Before
    public void setUp() {
        ff = new FreshFood(15, "omena", "fresh", 2, "pcs", Date.valueOf(LocalDate.now()));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void getIdWorks() {
        Assert.assertEquals(15, ff.getId());
    }
    
    @Test
    public void getNameWorks() {
        Assert.assertEquals("omena", ff.getName());
    }
    
    @Test
    public void getQuantityWorks() {
        Assert.assertEquals(2, ff.getQuantity());
    }
    
    @Test
    public void getQuantityTypeWorks() {
        Assert.assertEquals("pcs", ff.getQuantityType());
    }
    
    @Test
    public void getDateAddedWorks() {
        Assert.assertEquals(Date.valueOf(LocalDate.now()),  ff.getDateAdded());
    }
    
    @Test
    public void toStringWorks() {
        Assert.assertEquals("omena 2 pcs 2018-11-27", ff.toString());
    }
    
    @Test
    public void getFoodTypeWorks() {
        Assert.assertEquals("fresh", ff.getFoodType());
    }
}
