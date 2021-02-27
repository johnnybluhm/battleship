package main.java.edu.colorado.exhalation;

import edu.colorado.exhalation.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShipTest {

    Point[] getGoodVerticalDestroyer(){

        Point vertical_point_array[] = new Point[3];
        vertical_point_array[0] = new Point(0,1);
        vertical_point_array[1] = new Point(0, 2);
        vertical_point_array[2] = new Point(0, 3);
        return vertical_point_array;
    }

    Point[] getGoodHorizontalDestroyer(){

        Point horizontal_point_array[] = new Point[3];
        horizontal_point_array[0] = new Point(0,1);
        horizontal_point_array[1] = new Point(1,1);
        horizontal_point_array[2] = new Point(2,1);
        return horizontal_point_array;
    }

    @Test
    void testOrientation(){


        Point[] horizontal = getGoodHorizontalDestroyer();
        Point[] vertical = getGoodVerticalDestroyer();

        //point is start of test arrays
        Point test_point = new Point(0,1);

        Ship horizontal_ship = new Destroyer("horizontal",test_point);
        Ship vertical_ship = new Destroyer("vertical", test_point);

        Assertions.assertTrue(horizontal_ship.isHorizontal());
        Assertions.assertTrue(vertical_ship.isVertical());
    }

    @Test
    void testCreation(){

        Point[] horizontal = getGoodHorizontalDestroyer();
        Point[] vertical = getGoodVerticalDestroyer();

        //point is start of test arrays
        Point test_point = new Point(0,1);

        Ship horizontal_ship = new Destroyer("horizontal",test_point);
        Ship vertical_ship = new Destroyer("vertical", test_point);

        for(int i =0; i< horizontal.length; i++){
            Assertions.assertTrue(horizontal[i].equals(horizontal_ship.getPointArray()[i]));
            Assertions.assertTrue(vertical[i].equals(vertical_ship.getPointArray()[i]));
        }




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

        Point[] good_vertical_ship = getGoodVerticalDestroyer();
        Point[] good_horizontal_ship = getGoodHorizontalDestroyer();

        /*Assertions.assertTrue(good_horizontal_ship.verify());
        Assertions.assertTrue(good_vertical_ship.verify());
        Assertions.assertFalse(ship_with_bad_points.verify());
        Assertions.assertFalse(vertical_ship_points_bad_order.verify());
        Assertions.assertFalse(horizontal_ship_points_bad_order.verify());*/

        System.out.println(good_vertical_ship[0].getY());
    }
    /*@Test
    void testSort(){
        Point unsorted_horizontal_point_array[] = new Point[3];
        unsorted_horizontal_point_array[0] = new Point(2,0);
        unsorted_horizontal_point_array[1] = new Point(3,0);
        unsorted_horizontal_point_array[1] = new Point(1,0);

        Point unsorted_vertical_point_array[] = new Point[3];
        unsorted_vertical_point_array[0] = new Point(0,2);
        unsorted_vertical_point_array[1] = new Point(0, 3);
        unsorted_vertical_point_array[2] = new Point(0, 1);

        Point sorted_horizontal_point_array[] = new Point[3];
        sorted_horizontal_point_array[0] = new Point(1,0);
        sorted_horizontal_point_array[1] = new Point(2,0);
        sorted_horizontal_point_array[1] = new Point(3,0);

        Point sorted_vertical_point_array[] = new Point[3];
        sorted_vertical_point_array[0] = new Point(0,1);
        sorted_vertical_point_array[1] = new Point(0, 2);
        sorted_vertical_point_array[2] = new Point(0, 3);

        Ship vertical_test_ship = new Ship(unsorted_vertical_point_array);
        Ship horizontal_test_ship = new Ship(unsorted_horizontal_point_array);

        vertical_test_ship.sort();
        horizontal_test_ship.sort();

        for(int i =0; i<sorted_vertical_point_array.length; i++){
            assertEquals(sorted_vertical_point_array[i].getX_(), vertical_test_ship.getPointArray[i].getX_());
            assertEquals(sorted_vertical_point_array[i].getY_(), vertical_test_ship.getPointArray[i].getY_());

        }
        for(int i =0; i<sorted_horizontal_point_array.length; i++){
            assertEquals(sorted_horizontal_point_array[i].getX_(), vertical_test_ship.getPointArray[i].getX_());
            assertEquals(sorted_horizontal_point_array[i].getY_(), vertical_test_ship.getPointArray[i].getY_());

        }

    }//testSort()*/
}