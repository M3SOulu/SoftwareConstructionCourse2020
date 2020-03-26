/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.defensiveprogramming.Demo6Assertions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;
import static fi.oulu.softwareconstruction.defensiveprogramming.Demo6Assertions.App.sum;
import static fi.oulu.softwareconstruction.defensiveprogramming.Demo6Assertions.App.sumWithException;

/**
 *
 * @author mclaes
 */
public class AppTest {
    
    public AppTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of sum method, of class App.
     */
//    @Test
//    public void testSum() {
//        System.out.println("sum");
//        assertEquals(10 * 9 / 2, sum(10));
//        assertEquals(0, sum(0));
//        assertEquals(0, sum(-10));
//        assertEquals(499999500000L, sum(1000000));
//    }
    
    /**
     * Test of sumWithException, of class App.
     */
    @Test
    public void testSumWithException() {
        System.out.println("sumWithException");
        assertEquals(sumWithException(10), 10 * 9 / 2);
        assertEquals(sumWithException(0), 0);
    }
 
    /**
     * Test of sumWithException, of class App, with negative k.
     */
    @Test
    public void testSumWithNegative() {
        System.out.println("sumWithException with negative k");
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> sumWithException(-10));
        assertEquals("k was negative", exception.getMessage());
    }
    
    /**
     * Test of sumWithException, of class App, that will overflow.
     */
    @Test
    public void testSumWithOverflow() {
        System.out.println(sum(80000));
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> sumWithException(80000));
        assertEquals("k was too big and overflow", exception.getMessage());
        exception = assertThrows(IllegalArgumentException.class,
                () -> sumWithException(1000000));
        assertEquals("k was too big and overflow", exception.getMessage());
    }
}
