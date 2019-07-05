package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    Item testItem;

    @Before
    public void instanceItemTest() {
        testItem = new Item("00c4", 10, 55.42);
    }

    @Test
    public void instancedItemGetIdTest() {
        assertEquals("00c4", testItem.getId());
    }

    @Test
    public void instancedItemGetQuantityTest() {
        Integer expected = 10;
        assertEquals(expected, testItem.getQuantity());
    }

    @Test
    public void instancedItemGetPriceTest() {
        Double expected = 55.42;
        assertEquals(expected, testItem.getPrice(), 0.1);
    }



}
