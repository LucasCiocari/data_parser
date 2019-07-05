package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DataWrapperTest {
    DataWrapper testDataWrapper;

    @Before
    public void instanceTestDataWrapper() {
        String[] customer1 = {"001", "1234","Business1", "Accounting"};
        String[] customer2 = {"001", "4321","Business2", "Faking"};
        String[] salesman1 = {"002", "aaaa1234", "Lucio", "4202.24"};
        String[] salesman2 = {"002", "bbbb5678", "Anna", "5497.56"};
        String[] sale1 = {"003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro"};
        String[] sale2 = {"003", "08", "[1-34-10,2-33-1.50,3-40-0.10]", "Paulo" };
        testDataWrapper = new DataWrapper();
        testDataWrapper.addCustomer(customer1);
        testDataWrapper.addCustomer(customer2);
        testDataWrapper.addSeller(salesman1);
        testDataWrapper.addSeller(salesman2);
        testDataWrapper.addSale(sale1);
        testDataWrapper.addSale(sale2);
    }

    @Test
    public void testIfMinMaxWorks(){
        testDataWrapper.computeMinMax();
        String expected = "10";
        assertEquals(expected, testDataWrapper.getMax().getId());
        expected = "08";
        assertEquals(expected, testDataWrapper.getMin().getId());
    }
}
