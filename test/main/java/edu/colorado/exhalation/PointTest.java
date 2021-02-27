package main.java.edu.colorado.exhalation;

import org.junit.jupiter.api.Test;
import edu.colorado.exhalation.Point;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testPoint(){

        int[] manual_point= new int[2];
        manual_point[0] = 1;
        manual_point[1] = 0;

        Point test_point = new Point(1,2);
        Point test_point2 = new Point(2,0);

        assertEquals(1, test_point.getX());
        assertEquals(2, test_point.getY());

        assertEquals(2, test_point2.getX());
        assertEquals(0, test_point2.getY());

        Point point1 = new Point(1,0);
        Point point2 = new Point(1,0);

        assertTrue(point1.equals(point2));

    }




}