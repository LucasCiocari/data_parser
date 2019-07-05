package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SellerTest {
    Seller testSeller;
    @Before
    public void instanceTestSeller() {
        testSeller = new Seller("123456gt", "Louise", 5500.33);
    }

    @Test
    public void testGetCpf() {
        String expected = "123456gt";
        assertEquals(expected, testSeller.getCpf());
    }

    @Test
    public void testGetName() {
        String expected = "Louise";
        assertEquals(expected, testSeller.getName());
    }
    @Test
    public void testGetSalary() {
        Double expected = 5500.33;
        assertEquals(expected, testSeller.getSalary());
    }
}
