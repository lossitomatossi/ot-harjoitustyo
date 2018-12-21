/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodtracker.utilities;

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
    public void getDateAdded() {
        assertEquals(test.getDateAdded(), tf.getDateAdded());
    }

    @Test
    public void getExpiration() {
        assertEquals(test.getExpirationDate(), tf.getExpirationDate());
    }
}
