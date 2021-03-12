package edu.colorado.exhalation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubmarineTest {

    Point[] getVerticalSubPoints(Point start_point){

        int x = start_point.getX();
        int y = start_point.getY();
        Point[] vertical_point_array = new Point[5];
        vertical_point_array[0] = start_point;
        //tail
        vertical_point_array[1] = new Point(x,y-1);
        vertical_point_array[2] = new Point(x,y-2);
        vertical_point_array[3] = new Point(x,y-3);
        //bump
        vertical_point_array[4] = new Point(x-1,y-1);

        for (Point point: vertical_point_array
             ) {
            if(!point.isValid()){
                return null;
            }
        }
        return vertical_point_array;
    }

    Point[] getHorizontalSubPoints(Point start_point){
        int x = start_point.getX();
        int y = start_point.getY();
        Point[] horizontal_point_array = new Point[5];
        horizontal_point_array[0] = start_point;
        //tail
        horizontal_point_array[1] = new Point(x-1,y);
        horizontal_point_array[2] = new Point(x-2,y);
        horizontal_point_array[3] = new Point(x-3,y);
        //bump
        horizontal_point_array[4] = new Point(x-1,y+1);

        for (Point point: horizontal_point_array
        ) {
            if(!point.isValid()){
                return null;
            }
        }
        return horizontal_point_array;
    }

    @Test
    void testCreation(){
        Point start_point = new Point(2,4);
        Point[] horizontal = getHorizontalSubPoints(start_point);
        Point[] vertical = getVerticalSubPoints(start_point);

        Ship vertical_sub = new Submarine("v", start_point));
        Ship horizontal_sub = new Submarine("h", start_point));

        for (int i =0; i< horizontal.length; i++){
            Assertions.assertTrue(horizontal_sub.getPointArray()[i].equals(horizontal[i]));
        }

        for (int i =0; i< vertical.length; i++){
            Assertions.assertTrue(vertical_sub.getPointArray()[i].equals(horizontal[i]));
        }





        //assertTrue(horizontal);

    }

    /*@Test
    void testCreation(){

        //point arrays to compare our ship's too once they create their own point array
        Point[] horizontal_d = getGoodHorizontalDestroyer();
        Point[] vertical_d = getGoodVerticalDestroyer();
        Point[] horizontal_m = getGoodHorizontalMinesweeper();
        Point[] vertical_m = getGoodVerticalMinesweeper();
        Point[] horizontal_b = getGoodHorizontalBattleship();
        Point[] vertical_b = getGoodVerticalBattleship();

        //point is start of test arrays
        Point test_point = new Point(0,1);

        //create ships for testing
        Ship horizontal_destroyer = new Destroyer ("horizontal",test_point);
        Ship vertical_destroyer = new Destroyer("vertical", test_point);

        Ship horizontal_battleship = new Battleship ("horizontal",test_point);
        Ship vertical_battleship = new Battleship("vertical", test_point);

        Ship horizontal_minesweeper = new Minesweeper("horizontal",test_point);
        Ship vertical_minesweeper = new Minesweeper("vertical", test_point);

        //loop through point arrays and check that they match
        for(int i =0; i< horizontal_d.length; i++){
            Assertions.assertTrue(horizontal_d[i].equals(horizontal_destroyer.getPointArray()[i]));
            Assertions.assertTrue(vertical_d[i].equals(vertical_destroyer.getPointArray()[i]));
        }

        for(int i =0; i< horizontal_b.length; i++){
            Assertions.assertTrue(horizontal_b[i].equals(horizontal_battleship.getPointArray()[i]));
            Assertions.assertTrue(vertical_b[i].equals(vertical_battleship.getPointArray()[i]));
        }

        for(int i =0; i< horizontal_m.length; i++){
            Assertions.assertTrue(horizontal_m[i].equals(horizontal_minesweeper.getPointArray()[i]));
            Assertions.assertTrue(vertical_m[i].equals(vertical_minesweeper.getPointArray()[i]));
        }
    }

    @Test
    void testVerify(){

        //point is start of test arrays
        Point good_point = new Point(0,1);

        //bad points for testing
        Point bad_point1 = new Point(99,99);
        Point bad_point2 = new Point(-10,-4);
        Point semi_bad_horizontal_point = new Point(9,0);
        Point semi_bad_vertical_point = new Point(0,9);


        //arrays of points for testing
        Point[] good_horizontal_ships ={good_point};
        Point[] good_vertical_ships = {good_point};
        Point[] bad_vertical_ships ={bad_point1, bad_point2, semi_bad_vertical_point};
        Point[] bad_horizontal_ships ={bad_point1, bad_point2, semi_bad_horizontal_point};


        //tests different kinds of ships
        Ship bad_vertical_ship;
        Ship bad_horizontal_ship;

        //test all ships with bad points
        for(int i =0; i< bad_vertical_ships.length; i++){
            bad_vertical_ship = new Destroyer("vertical", bad_vertical_ships[i]);
            Assertions.assertFalse(bad_vertical_ship.verify());
            bad_vertical_ship = new Minesweeper("vertical", bad_vertical_ships[i]);
            Assertions.assertFalse(bad_vertical_ship.verify());
            bad_vertical_ship = new Battleship("vertical", bad_vertical_ships[i]);
            Assertions.assertFalse(bad_vertical_ship.verify());

        }
        for(int i =0; i< bad_horizontal_ships.length; i++){
            bad_horizontal_ship = new Destroyer("horizontal", bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.verify());
            bad_horizontal_ship = new Minesweeper("horizontal", bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.verify());
            bad_horizontal_ship = new Battleship("horizontal", bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.verify());
        }

        //test good points on all ships
        Ship good_horizontal_ship = new Destroyer("horizontal",good_point);
        Ship good_vertical_ship = new Destroyer("vertical", good_point);
        Assertions.assertTrue(good_horizontal_ship.verify());
        Assertions.assertTrue((good_vertical_ship.verify()));

        good_horizontal_ship = new Minesweeper("horizontal",good_point);
        good_vertical_ship = new Minesweeper("vertical", good_point);

        Assertions.assertTrue(good_horizontal_ship.verify());
        Assertions.assertTrue((good_vertical_ship.verify()));

        good_horizontal_ship = new Battleship("horizontal",good_point);
        good_vertical_ship = new Battleship("vertical", good_point);

        Assertions.assertTrue(good_horizontal_ship.verify());
        Assertions.assertTrue((good_vertical_ship.verify()));
    }*/

}