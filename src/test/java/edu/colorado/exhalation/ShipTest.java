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
    Point[] getVerticalSubPoints(Point start_point){

        int x = start_point.getX();
        int y = start_point.getY();
        Point[] vertical_point_array = new Point[5];
        vertical_point_array[0] = start_point;
        //tail
        vertical_point_array[1] = new Point(x,y+1);
        vertical_point_array[2] = new Point(x,y+2);
        vertical_point_array[3] = new Point(x,y+3);
        //bump
        vertical_point_array[4] = new Point(x+1,y+2);

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
        horizontal_point_array[1] = new Point(x+1,y);
        horizontal_point_array[2] = new Point(x+2,y);
        horizontal_point_array[3] = new Point(x+3,y);
        //bump
        horizontal_point_array[4] = new Point(x+2,y-1);

        for (Point point: horizontal_point_array
        ) {
            if(!point.isValid()){
                return null;
            }
        }
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
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.BOMB) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.BOMB) == 2);
        }

        //DESTROYER
        board = new Board();
        ship = new Destroyer('v', start_point);
        board.placeShip(ship);
        hits = Destroyer.getVerticalPoints(start_point);
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.BOMB) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.BOMB) == 2);
        }

        //MINESWEEPER
        board = new Board();
        ship = new Minesweeper('v', start_point);
        board.placeShip(ship);
        hits = Minesweeper.getVerticalPoints(start_point);
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.BOMB) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.BOMB) == 2);
        }

        //SUB
        board = new Board();
        ship = new Submarine('v', start_point);
        board.placeShip(ship);
        hits = Submarine.getVerticalPoints(start_point);
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.BOMB) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.BOMB) == 2);
        }

        board.setWeapon(Board.LASER);
        //should now get hit with laser
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.LASER) == 1);
        }
        for (int i = 0; i < hits.length; i++) {
            Point point = hits[i];
            board.hit(point);
            Assertions.assertTrue(board.getHitCount(board.getPeg(ship.getPoints()[i]), Board.LASER) == 2);
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
    void testRemoval(){
        Board empty_board = new Board();
        Board board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        board.placeShip(minesweeper);
        board.placeShip(destroyer);
        board.placeShip(battleship);
        board.placeShip(submarine);
        board.removeShips();
        //test removal no sunk ships
        Assertions.assertTrue(board.equals(empty_board));

        board = new Board();
        board.placeShip(minesweeper);
        board.placeShip(destroyer);

        Board sunk_ship_board = new Board();
        sunk_ship_board.placeShip(minesweeper);

        //sink the sweeper
        for (int i = 0; i < minesweeper.getPoints().length; i++) {
            Point point = minesweeper.getPoints()[i];
            board.hit(point);
            sunk_ship_board.hit(point);
        }

        board.removeShips();

        Assertions.assertTrue(board.equals(sunk_ship_board));



    }
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
        Ship minesweeper2 = new Minesweeper('h', new Point(1,0));
        Ship destroyer2 = new Destroyer('h', new Point(1,1));
        Ship battleship2 = new Battleship('h', new Point(1,2));
        Ship submarine2 = new Submarine('h', new Point(1,3));
        shift_right_board.placeShip(minesweeper2);
        shift_right_board.placeShip(destroyer2);
        shift_right_board.placeShip(battleship2);
        shift_right_board.placeShip(submarine2);


        board.move('E');

        Assertions.assertTrue(board.equals(shift_right_board));

        Board shift_left_board = new Board();
        shift_left_board.placeShip(minesweeper);
        shift_left_board.placeShip(battleship);
        shift_left_board.placeShip(destroyer);
        shift_left_board.placeShip(submarine);

        board.move('W');
        Assertions.assertTrue(board.equals(shift_left_board));
        board.move('W');
        Assertions.assertTrue(board.equals(shift_left_board));

        board = new Board();
        board.placeShip(minesweeper);
        board.placeShip(destroyer);
        board.placeShip(battleship);
        board.placeShip(submarine);

        for (Point point:
             minesweeper.getPoints()) {
            board.hit(point);
        }

        System.out.println(board.getStateString());
        board.move('E');
        shift_right_board = new Board();
        Ship minesweeper3 = new Minesweeper('h', new Point(0,0));
        Ship destroyer3 = new Destroyer('h', new Point(1,1));
        Ship battleship3 = new Battleship('h', new Point(1,2));
        Ship submarine3 = new Submarine('h', new Point(1,3));
        shift_right_board.placeShip(minesweeper3);
        shift_right_board.placeShip(destroyer3);
        shift_right_board.placeShip(battleship3);
        shift_right_board.placeShip(submarine3);
        for (Point point:
                minesweeper.getPoints()) {
            shift_right_board.hit(point);
        }
        System.out.println(board.getStateString());
        System.out.println(shift_right_board.getStateString());
        Assertions.assertTrue(board.equals(shift_right_board));




    }
    @Test
    void testSubCreation(){
        Point start_point = new Point(2,4);
        Point[] horizontal = getHorizontalSubPoints(start_point);
        Point[] vertical = getVerticalSubPoints(start_point);

        Ship vertical_sub = new Submarine('v', start_point);
        Ship horizontal_sub = new Submarine('h', start_point);

        for (int i =0; i< horizontal.length; i++){
            Assertions.assertTrue(horizontal_sub.getPoints()[i].equals(horizontal[i]));
        }

        for (int i =0; i< vertical.length; i++){
            Assertions.assertTrue(vertical_sub.getPoints()[i].equals(vertical[i]));
        }
    }
    @Test
    void testSubIsValid(){

        Ship bad_sub = new Submarine('v', new Point(9,9));
        Assertions.assertTrue(!bad_sub.isValid());
        bad_sub = new Submarine('h', new Point(9,9));
        Assertions.assertTrue(!bad_sub.isValid());
    }
    @Test
    void testSubPlace(){

        Board board = new Board();
        Point start_point = new Point(5,5);
        Ship test_sub = new Submarine('v', start_point);
        Ship test_ship = new Battleship('v', start_point);
        board.placeShip(test_ship);
        Board board2 = new Board();
        board2.placeShip(test_sub);

        Assertions.assertTrue(board2.placeShip(test_ship)==1);

    }

}