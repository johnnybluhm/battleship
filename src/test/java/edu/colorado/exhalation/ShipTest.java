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

        Ship horizontal_ship = new Destroyer('h',test_point);
        Ship vertical_ship = new Destroyer('v', test_point);

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
        Ship horizontal_destroyer = new Destroyer ('h',test_point);
        Ship vertical_destroyer = new Destroyer('v', test_point);

        Ship horizontal_battleship = new Battleship ('h',test_point);
        Ship vertical_battleship = new Battleship('v', test_point);

        Ship horizontal_minesweeper = new Minesweeper('h',test_point);
        Ship vertical_minesweeper = new Minesweeper('v', test_point);

        //loop through point arrays and check that they match
        for(int i =0; i< horizontal_d.length; i++){
            System.out.println(horizontal_d[i]);
            System.out.println(horizontal_destroyer.getPoints()[i]);
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
            bad_vertical_ship = new Destroyer('v', bad_vertical_ships[i]);
            Assertions.assertFalse(bad_vertical_ship.isValid());
            bad_vertical_ship = new Minesweeper('v', bad_vertical_ships[i]);
            Assertions.assertFalse(bad_vertical_ship.isValid());
            bad_vertical_ship = new Battleship('v', bad_vertical_ships[i]);
            Assertions.assertFalse(bad_vertical_ship.isValid());

        }
        for(int i =0; i< bad_horizontal_ships.length; i++){
            bad_horizontal_ship = new Destroyer('h', bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.isValid());
            bad_horizontal_ship = new Minesweeper('h', bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.isValid());
            bad_horizontal_ship = new Battleship('h', bad_horizontal_ships[i]);
            Assertions.assertFalse(bad_horizontal_ship.isValid());
        }

        //test good points on all ships
        Ship good_horizontal_ship = new Destroyer('h',good_point);
        Ship good_vertical_ship = new Destroyer('v', good_point);
        Assertions.assertTrue(good_horizontal_ship.isValid());
        Assertions.assertTrue((good_vertical_ship.isValid()));

        good_horizontal_ship = new Minesweeper('h',good_point);
        good_vertical_ship = new Minesweeper('v', good_point);

        Assertions.assertTrue(good_horizontal_ship.isValid());
        Assertions.assertTrue((good_vertical_ship.isValid()));

        good_horizontal_ship = new Battleship('h',good_point);
        good_vertical_ship = new Battleship('v', good_point);

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

    @Test
    void testHitCount(){

        //BATTLESHIP
        Board board = new Board();
        Point start_point = new Point(5,5);
        Ship ship= new Battleship('v', start_point);
        board.placeShip(ship);
        Point[] hits = Battleship.getVerticalPoints(start_point);
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 2);
        }

        //DESTROYER
        board = new Board();
        ship = new Destroyer('v', start_point);
        board.placeShip(ship);
        hits = Destroyer.getVerticalPoints(start_point);
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 2);
        }

        //MINESWEEPER
        board = new Board();
        ship = new Minesweeper('v', start_point);
        board.placeShip(ship);
        hits = Minesweeper.getVerticalPoints(start_point);
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 2);
        }

        //SUB
        //hits with bomb should be 0
        board = new Board();
        ship = new Submarine('v', start_point);
        board.placeShip(ship);
        hits = Submarine.getVerticalPoints(start_point);
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 2);
        }

        board.setWeapon(Board.LASER);
        //should now get hit with laser
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(ship.getHitCount(ship.getPegs()[i]) == 2);
        }



    }//hitCountTest

    @Test
    void testIsSunk(){

        Board board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        board.placeShip(minesweeper);
        board.placeShip(destroyer);
        board.placeShip(battleship);
        board.placeShip(submarine);

        //hits every point on every ship and checks that they are sunk
        Point[] points = minesweeper.getPoints();
        for(int i=0; i< points.length; i++){
            board.hit(points[i]);
        }
        Assertions.assertTrue(board.isSunk(minesweeper));

        points = destroyer.getPoints();
        for(int i=0; i< points.length; i++){
            board.hit(points[i]);
        }
        Assertions.assertFalse(board.isSunk(destroyer));
        board.hit(points[1]);
        Assertions.assertTrue(board.isSunk(destroyer));

        points = battleship.getPoints();
        for(int i=0; i< points.length; i++){
            board.hit(points[i]);
        }
        Assertions.assertFalse(board.isSunk(battleship));
        board.hit(points[2]);
        Assertions.assertTrue(board.isSunk(battleship));

        points = submarine.getPoints();
        for(int i=0; i< points.length; i++){
            board.hit(points[i]);
        }
        Assertions.assertFalse(board.isSunk(submarine));
        board.setWeapon(Board.LASER);
        for(int i=0; i< points.length; i++){
            board.hit(points[i]);
        }
        Assertions.assertTrue(board.isSunk(submarine));

    }//testSunk

    @Test
    void testShift(){
        Board board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        board.placeShip(minesweeper);
        board.placeShip(destroyer);
        board.placeShip(battleship);
        board.placeShip(submarine);

        Board shift_right_board = new Board();

        minesweeper = new Minesweeper('h', new Point(1,0));
        destroyer = new Destroyer('h', new Point(1,1));
        battleship = new Battleship('h', new Point(1,2));
        submarine = new Submarine('h', new Point(1,3));

        board.move('E');

        Assertions.assertTrue(board.equals(shift_right_board));

    }

}