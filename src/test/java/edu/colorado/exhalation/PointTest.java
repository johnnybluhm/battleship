package edu.colorado.exhalation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testCreation(){
        Point test_point = new Point(1,2);

        assertEquals(1, test_point.getX());
        assertEquals(2, test_point.getY());
    }

    @Test
    void testEquals(){
        Point test_point = new Point(1,2);
        Point test_point2 = new Point(2,0);
        Point test_point3 = new Point(1, 2);

        assertFalse(test_point.equals(test_point2));
        assertTrue(test_point.equals(test_point3));
    }

    @Test
    void testChanging(){
        Point test_point = new Point(1,2);
        Point test_point2 = new Point(2,0);

        assertFalse(test_point.equals(test_point2));

        test_point2.setX(1);
        test_point2.setY(2);

        assertTrue(test_point.equals(test_point2));
    }

    @Test
    void testValid(){
        Point test_point = new Point(-1,2);
        Point test_point2 = new Point(1,10);
        Point test_point3 = new Point(-1,11);
        Point test_point4 = new Point(1,2);

        assertFalse(test_point.isValid());
        assertFalse(test_point2.isValid());
        assertFalse(test_point3.isValid());
        assertTrue(test_point4.isValid());
    }

}