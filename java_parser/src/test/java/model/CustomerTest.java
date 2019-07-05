package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    Customer testCustomer;

    @Before
    public void instanceTestCustomer() {
        testCustomer = new Customer("111222333444", "FakeBusiness123", "Accounting");
    }

    @Test
    public void testGetCnpj(){
        String expected = "111222333444";
        assertEquals(expected, testCustomer.getCnpj());
    }

    @Test
    public void testGetName(){
        String expected = "FakeBusiness123";
        assertEquals(expected, testCustomer.getName());
    }

    @Test
    public void testGetBusinessArea(){
        String expected = "Accounting";
        assertEquals(expected, testCustomer.getBusinessArea());
    }

}
