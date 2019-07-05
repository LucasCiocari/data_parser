package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SaleTest {
    Sale testSale;
    ArrayList<Item> testArrayItem;

    @Before
    public void instanceTestSale() {
        testArrayItem = new ArrayList<>();
        testArrayItem.add(new Item("0001", 5, 12.5));
        testArrayItem.add(new Item("0002", 2, 5.25));
        testArrayItem.add(new Item("0003", 8, 3.30));
        testSale = new Sale("04de", testArrayItem, "Joseph");
    }

    @Test
    public void testIfTotalWasCalculatedRight() {
        Double expected = 99.4;
        assertEquals(expected, testSale.getTotal());
    }

    @Test
    public void testGetId() {
        String expected = "04de";
        assertEquals(expected, testSale.getId());
    }

    @Test
    public void testGetSalesman() {
        String expected = "Joseph";
        assertEquals(expected, testSale.getSalesman());
    }

    @Test
    public void testGetItemsSold() {
        assertEquals(testArrayItem, testSale.getItemsSold());
    }

}
