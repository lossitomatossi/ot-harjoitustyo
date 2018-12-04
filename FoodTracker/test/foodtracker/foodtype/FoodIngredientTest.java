/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodtracker.foodtype;

import foodtracker.foodtypes.FoodIngredient;
import java.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FoodIngredientTest {
    private FoodIngredient fi;
    
    public FoodIngredientTest() {
    }
    
    @Before
    public void setUp() {
        fi = new FoodIngredient(18, "pepper", "ingredient", 10, "gram", LocalDate.now());
    }
    
    @Test
    public void toStringWorks() {
        Assert.assertEquals("Ingredient: pepper 10 gram added: " +  LocalDate.now(), fi.toString());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
