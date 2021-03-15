package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShipTest {

    Point[] getGoodVerticalDestroyer(){

        Point[] vertical_point_array = new Point[3];
        vertical_point_array[0] = new Point(0,1);
        vertical_point_array[1] = new Point(0, 2);
        vertical_point_array[2] = new Point(0, 3);
        return vertical_point_array;
    }

    Point[] getGoodHorizontalDestroyer(){

        Point[] horizontal_point_array = new Point[3];
        horizontal_point_array[0] = new Point(0,1);
        horizontal_point_array[1] = new Point(1,1);
        horizontal_point_array[2] = new Point(2,1);
        return horizontal_point_array;
    }

    Point[] getGoodHorizontalMinesweeper(){

        Point[] horizontal_point_array = new Point[2];
        horizontal_point_array[0] = new Point(0,1);
        horizontal_point_array[1] = new Point(1,1);
        return horizontal_point_array;
    }
    Point[] getGoodVerticalMinesweeper(){

        Point[] horizontal_point_array = new Point[2];
        horizontal_point_array[0] = new Point(0,1);
        horizontal_point_array[1] = new Point(0,2);
        return horizontal_point_array;
    }
    Point[] getGoodVerticalBattleship(){

        Point[] vertical_point_array = new Point[4];
        vertical_point_array[0] = new Point(0,1);
        vertical_point_array[1] = new Point(0, 2);
        vertical_point_array[2] = new Point(0, 3);
        vertical_point_array[3] = new Point(0, 4);
        return vertical_point_array;
    }

    Point[] getGoodHorizontalBattleship(){

        Point[] horizontal_point_array = new Point[4];
        horizontal_point_array[0] = new Point(0,1);
        horizontal_point_array[1] = new Point(1,1);
        horizontal_point_array[2] = new Point(2,1);
        horizontal_point_array[3] = new Point(3,1);
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
            Assertions.assertTrue(horizontal_d[i].equals(horizontal_destroyer.getPoints()[i]));
            Assertions.assertTrue(vertical_d[i].equals(vertical_destroyer.getPoints()[i]));
        }

        for(int i =0; i< horizontal_b.length; i++){
            Assertions.assertTrue(horizontal_b[i].equals(horizontal_battleship.getPoints()[i]));
            Assertions.assertTrue(vertical_b[i].equals(vertical_battleship.getPoints()[i]));
        }

        for(int i =0; i< horizontal_m.length; i++){
            Assertions.assertTrue(horizontal_m[i].equals(horizontal_minesweeper.getPoints()[i]));
            Assertions.assertTrue(vertical_m[i].equals(vertical_minesweeper.getPoints()[i]));
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
            Assertions.assertFalse(bad_vertical_ship.isValid());
            bad_vertical_ship = new Minesweeper("vertical", bad_vertical_ships[i]);
            Assertions.assertFalse(bad_vertical_ship.isValid());
            bad_vertical_ship = new Battleship("vertical", bad_vertical_ships[i]);
            Assertions.assertFalse(bad_vertical_ship.isValid());

        }
        for(int i =0; i< bad_horizontal_ships.length; i++){
            bad_horizontal_ship = new Destroyer("horizontal", bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.isValid());
            bad_horizontal_ship = new Minesweeper("horizontal", bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.isValid());
            bad_horizontal_ship = new Battleship("horizontal", bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.isValid());
        }

        //test good points on all ships
        Ship good_horizontal_ship = new Destroyer("horizontal",good_point);
        Ship good_vertical_ship = new Destroyer("vertical", good_point);
        Assertions.assertTrue(good_horizontal_ship.isValid());
        Assertions.assertTrue((good_vertical_ship.isValid()));

        good_horizontal_ship = new Minesweeper("horizontal",good_point);
        good_vertical_ship = new Minesweeper("vertical", good_point);

        Assertions.assertTrue(good_horizontal_ship.isValid());
        Assertions.assertTrue((good_vertical_ship.isValid()));

        good_horizontal_ship = new Battleship("horizontal",good_point);
        good_vertical_ship = new Battleship("vertical", good_point);

        Assertions.assertTrue(good_horizontal_ship.isValid());
        Assertions.assertTrue((good_vertical_ship.isValid()));
    }

    @Test
    void testPegArray(){
        Point start_point = new Point(5,5);

        Ship minesweeper = new Minesweeper('v',start_point);
        Ship submarine = new Submarine('v', start_point);

        Board board = new Board();

        board.placeShip(minesweeper);
        board.placeShip(submarine);

        Point[] mine_sweeper_points = Minesweeper.getVerticalPoints(start_point);
        Point[] sub_points = Submarine.getVerticalPoints(start_point);

        for (int i = 0, battleship_pointsLength = mine_sweeper_points.length; i < battleship_pointsLength; i++) {
            Point point = mine_sweeper_points[i];
            Assertions.assertTrue(board.getPeg(point).equals(minesweeper.getPegs()[i]));
        }

        for (int i = 0, battleship_pointsLength = sub_points.length; i < battleship_pointsLength; i++) {
            Point point = sub_points[i];
            Assertions.assertTrue(board.getPeg(point).equals(submarine.getPegs()[i]));
        }

        //Assertions.assertTrue(minesweeper.isSunk());
        //Assertions.assertTrue(!submarine.isSunk());
    }



}