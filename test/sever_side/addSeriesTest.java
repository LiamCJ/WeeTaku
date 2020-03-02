/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sever_side;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author liam
 */
public class addSeriesTest {
    
    public addSeriesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSeries method, of class addSeries.
     */
    @Test
    public void testAddSeries() {
        System.out.println("addSeries");
        String title = "Test";
        String last_epi = "Test";
        String last_sea = "Test";
        String num_sea = "Test";
        addSeries instance = new addSeries();
        instance.addSeries(title, last_epi, last_sea, num_sea);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
