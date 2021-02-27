package main.java.edu.colorado.exhalation;

import org.junit.jupiter.api.Test;
import edu.colorado.exhalation.Point;
import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    Point[] getGoodVerticalShipArray(){

        Point vertical_point_array[] = new Point[3];
        vertical_point_array[0] = new Point(0,1);
        vertical_point_array[1] = new Point(0, 2);
        vertical_point_array[2] = new Point(0, 3);
        return vertical_point_array;
    }

    Point[] getGoodHorizontalShipArray(){

        Point horizontal_point_array[] = new Point[3];
        horizontal_point_array[0] = new Point(1,0);
        horizontal_point_array[1] = new Point(2,0);
        horizontal_point_array[1] = new Point(3,0);
        return horizontal_point_array;
    }

    @Test
    void testOrientation(){

        //create point arrays for making ships
        Point horizontal_point_array[] = new Point[2];
        Point vertical_point_array[] = new Point[2];
        horizontal_point_array[0] = new Point(1,0);
        horizontal_point_array[1] = new Point(2,0);
        vertical_point_array[0] = new Point(0,1);
        vertical_point_array[1] = new Point(0, 2);

        //Ship horizontal_ship = new Ship(horizontal_point_array);
        //Ship vertical_ship = new Ship(vertical_point_array);

        //assertTrue(horizontal_ship.isHorizontal());
       // assertTrue(vertical_ship.isVertical());
    }

    @Test
    void testVerify(){
        Point bad_point = new Point(11, 11);
        Point bad_point_neg = new Point(-1, -2);
        Point good_point = new Point(1,2);

        Point ship_with_bad_points[] = new Point[3];
        ship_with_bad_points[0] = bad_point;
        ship_with_bad_points[1] = bad_point_neg;
        ship_with_bad_points[2] = good_point;

        Point vertical_ship_points_bad_order[] = new Point[3];
        vertical_ship_points_bad_order[0] = new Point(1,0);
        vertical_ship_points_bad_order[1] = new Point(1,1);
        vertical_ship_points_bad_order[2] = new Point(1,3);

        Point horizontal_ship_points_bad_order[] = new Point[3];
        vertical_ship_points_bad_order[0] = new Point(1,0);
        vertical_ship_points_bad_order[1] = new Point(1,1);
        vertical_ship_points_bad_order[2] = new Point(1,3);

        Point[] good_vertical_ship = getGoodVerticalShipArray();
        Point[] good_horizontal_ship = getGoodHorizontalShipArray();

        assertTrue(good_horizontal_ship.verify());
        assertTrue(good_vertical_ship.verify());
        assertFalse(ship_with_bad_points.verify());
        assertFalse(vertical_ship_points_bad_order.verify());
        assertFalse(horizontal_ship_points_bad_order.verify());

        System.out.println(good_vertical_ship[0].getY_());




    }
}