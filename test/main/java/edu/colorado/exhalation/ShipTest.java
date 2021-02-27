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

    Point[] getBadVerticalDestroyer(){

        Point vertical_point_array[] = new Point[3];
        vertical_point_array[0] = new Point(-2,99);
        vertical_point_array[1] = new Point(78, 22);
        vertical_point_array[2] = new Point(20, 32);
        return vertical_point_array;
    }

    Point[] getBadHorizontalDestroyer(){

        Point horizontal_point_array[] = new Point[3];
        horizontal_point_array[0] = new Point(10,11);
        horizontal_point_array[1] = new Point(21,21);
        horizontal_point_array[2] = new Point(42,31);
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

        //point is start of test arrays
        Point good_point = new Point(0,1);

        //bad points for testing
        Point bad_point1 = new Point(99,99);
        Point bad_point2 = new Point(-10,-4);
        Point semi_bad_horizontal_point = new Point(8,0);
        Point semi_bad_vertical_point = new Point(0,8);


        //arrays of points for testing
        Point[] good_horizontal_ships ={good_point};
        Point[] good_vertical_ships = {good_point};
        Point[] bad_vertical_ships ={bad_point1, bad_point2, semi_bad_vertical_point};
        Point[] bad_horizontal_ships ={bad_point1, bad_point2, semi_bad_horizontal_point};


        //tests different kinds of ships
        Ship bad_vertical_ship;
        Ship bad_horizontal_ship;

        //test destroyer
        for(int i =0; i< bad_vertical_ships.length; i++){
            bad_vertical_ship = new Destroyer("vertical", bad_vertical_ships[i]);
            Assertions.assertFalse(bad_vertical_ship.verify());
        }
        for(int i =0; i< bad_horizontal_ships.length; i++){
            bad_horizontal_ship = new Destroyer("horizontal", bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.verify());
        }

        Ship good_horizontal_ship = new Destroyer("horizontal",good_point);
        Ship good_vertical_ship = new Destroyer("vertical", good_point);

        Assertions.assertTrue(good_horizontal_ship.verify());
        Assertions.assertTrue((good_vertical_ship.verify()));
        


    }

}