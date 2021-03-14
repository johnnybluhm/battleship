package edu.colorado.exhalation;

//import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BoardTest {


    String getEmptyBoardString(){
        String top_of_board = "   A B C D E | F G H I J\n";
        //ensure space at the end of the test string
        String empty_row = "O O O O O | O O O O O \n";
        String seperator = "   - - - - - | - - - - -\n";
        String empty_board = top_of_board;

        //build board string
        for(int i =0; i <5; i++){
            String num = String.valueOf(i);
            empty_board += num+"| ";
            empty_board += empty_row;
        }
        empty_board+= seperator;
        for(int i =5; i <10; i++){
            String num = String.valueOf(i);
            empty_board += num+"| ";
            empty_board += empty_row;
        }
        return empty_board;
    }

    String getEmptyBoardStringWithHits(){
        String top_of_board = "   A B C D E | F G H I J\n";
        //ensure space at the end of the test string
        String empty_row = "# # # # # | # # # # # \n";
        String seperator = "   - - - - - | - - - - -\n";
        String empty_board = top_of_board;

        //build board string
        for(int i =0; i <5; i++){
            String num = String.valueOf(i);
            empty_board += num+"| ";
            empty_board += empty_row;
        }
        empty_board+= seperator;
        for(int i =5; i <10; i++){
            String num = String.valueOf(i);
            empty_board += num+"| ";
            empty_board += empty_row;
        }
        return empty_board;
    }

    Point[] getPulseArray(Point point){
        int x  = point.getX();
        int y = point.getY();
        final int PULSE_SIZE = 12;
        Point[] points = new Point[PULSE_SIZE+1];
        //get points to sides
        points[0] = new Point(x+1,y);
        points[1] = new Point(x+2,y);
        points[2] = new Point(x-1,y);
        points[3] = new Point(x-2,y);

        //get points above/below
        points[4] = new Point(x,y+1);
        points[5] = new Point(x,y+2);
        points[6] = new Point(x,y-1);
        points[7] = new Point(x,y-2);

        //get corners
        points[8] = new Point(x+1,y+1);
        points[9] = new Point(x-1,y-1);
        points[10] = new Point(x-1,y+1);
        points[11] = new Point(x+1,y-1);

        //add selected point
        points[12] = point;

        //set bad points to null
        for(int i =0; i<points.length; i++){
            if(points[i].isValid()==false){
                points[i] = null;
            }
        }

        return points;
    }
    //board needs to start with all empty pegs
    @Test
    void testInitialize(){
        Peg[][]  peg_array = new Peg[10][10];

        for(int i =0; i< peg_array.length; i++){
            for(int j = 0; j< peg_array[i].length;j++){
                peg_array[i][j] = new Peg(i,j);
            }
        }
        Board test_board = new Board();

        //ensure board object and hard coded peg array have same values
        //also tests getPeg() method
        for(int i =0; i< peg_array.length; i++){
            for(int j = 0; j< peg_array[i].length;j++){
                assertTrue(peg_array[i][j].getPoint().equals(test_board.getPeg(i,j).getPoint()));
            }
        }

    }//initialize

    @Test
    void testEmptyPrint(){
        String empty_board = getEmptyBoardString();

        Board empty_test_board = new Board();

        assertTrue(empty_board.equals(empty_test_board.getStateString()));

    }

    @Test
    void testFullPrint(){
        String top_of_board = "   A B C D E | F G H I J\n";
        //ensure space at the end of the test string
        String empty_row = "# # # # # | # # # # # \n";
        String seperator = "   - - - - - | - - - - -\n";
        String full_board = top_of_board;

        //build board string
        for(int i =0; i <5; i++){
            String num = String.valueOf(i);
            full_board+= num+"| ";
            full_board += empty_row;
        }
        full_board+= seperator;
        for(int i =5; i <10; i++){
            String num = String.valueOf(i);
            full_board += num+"| ";
            full_board += empty_row;
        }

        Board full_test_board = new Board();

        //hit every peg
        for(int i =0; i< 10; i++){
            for(int j = 0; j< 10;j++){
                full_test_board.getPeg(i,j).hit();
            }
        }

        assertTrue(full_board.equals(full_test_board.getStateString()));
    }

    /*@Test
    void testPrintWithShips(){
        String top_of_board = "   A B C D E | F G H I J\n";
        //ensure space at the end of the test string
        String empty_row = "O O O O O | O O O O O \n";
        String seperator = "   - - - - - | - - - - -\n";
        String board = top_of_board;
        String last_row = "9| X X X O O | O O # X X \n";

        //build board string
        for(int i =0; i <5; i++){
            String num = String.valueOf(i);
            board += num+"| ";
            board += empty_row;
        }
        board+= seperator;
        for(int i =5; i <9; i++){
            String num = String.valueOf(i);
            board += num+"| ";
            board += empty_row;
        }
        board+= last_row;
        Board empty_test_board = new Board();
        System.out.println(board);

        //set last row to reflect hard coded last row
        empty_test_board.getPeg(0,9).setType(1);
        empty_test_board.getPeg(1,9).setType(1);
        empty_test_board.getPeg(2,9).setType(1);
        empty_test_board.getPeg(8,9).setType(2);
        empty_test_board.getPeg(9,9).setType(2);


        //hit ships
        empty_test_board.getPeg(0,9).hit();
        empty_test_board.getPeg(1,9).hit();
        empty_test_board.getPeg(2,9).hit();
        empty_test_board.getPeg(8,9).hit();
        empty_test_board.getPeg(9,9).hit();

        //hit empty square
        empty_test_board.getPeg(7,9).hit();

        System.out.println(empty_test_board.getStateString());
        assertTrue(board.equals(empty_test_board.getStateString()));
    }*/

    @Test
    void createShips(){

        String top_of_board = "   A B C D E | F G H I J\n";
        //ensure space at the end of the test string
        String empty_row = "O O O O O | O O O O O \n";
        String seperator = "   - - - - - | - - - - -\n";
        String board_string = top_of_board;
        String last_row = "9| 2 2 O O O | O O O O O \n";

        //build board string
        for(int i =0; i <5; i++){
            String num = String.valueOf(i);
            board_string += num+"| ";
            board_string += empty_row;
        }
        board_string+= seperator;
        for(int i =5; i <9; i++){
            String num = String.valueOf(i);
            board_string += num+"| ";
            board_string += empty_row;
        }
        board_string+= last_row;

        Board test_board = new Board();

        Ship bottom_left_corner_ship = new Minesweeper("horizontal", new Point(0,9)) ;

        test_board.placeShip(bottom_left_corner_ship);

        System.out.println(board_string);
        System.out.println(test_board.getStateString());
        assertTrue(board_string.equals(test_board.getStateString()));

        //method should return -1 as there is already a ship where we are trying to place one
        assertEquals(-1, test_board.placeShip(bottom_left_corner_ship));
        System.out.println(test_board.getStateString());

        Ship second_minesweeper = new Minesweeper("horizontal", new Point(0,2));
        //test placing 2 Minesweepers on the board
        assertEquals(-2, test_board.placeShip(second_minesweeper));

        Ship destroyer = new Destroyer("horizontal", new Point(0,2));

        //test for placing different ship type on top of another
        assertEquals(-1,test_board.placeShip(destroyer));
    }

    @Test
    void testHiddenBoard() {

        String top_of_board = "   A B C D E | F G H I J\n";
        //ensure space at the end of the test string
        String empty_row = "O O O O O | O O O O O \n";
        String seperator = "   - - - - - | - - - - -\n";
        String board_string = top_of_board;
        String last_row = "9| O O O O O | O O O O O \n";
        String last_row_after_hits = "9| X X # # # | # # # # # \n";
        String board_string_after_hits = top_of_board;
        //build board string
        for (int i = 0; i < 5; i++) {
            String num = String.valueOf(i);
            board_string += num + "| ";
            board_string += empty_row;
            board_string_after_hits += num + "| ";
            board_string_after_hits += empty_row;
        }
        board_string += seperator;
        board_string_after_hits += seperator;
        for (int i = 5; i < 9; i++) {
            String num = String.valueOf(i);
            board_string += num + "| ";
            board_string += empty_row;
            board_string_after_hits += num + "| ";
            board_string_after_hits += empty_row;
        }
        board_string += last_row;
        board_string_after_hits += last_row_after_hits;

        Board test_board = new Board();

        Ship bottom_left_corner_ship = new Minesweeper("horizontal", new Point(0, 9));
        Ship top_right_corner_down_ship = new Battleship("vertical", new Point(9, 0));
        Ship middle_destroyer = new Destroyer("horizontal", new Point(5, 6));
        test_board.placeShip(bottom_left_corner_ship);
        test_board.placeShip(top_right_corner_down_ship);
        test_board.placeShip(middle_destroyer);


        //board with no hits should be all O's
        assertTrue(board_string.equals(test_board.getHiddenStateString()));

        //create array of points to hit
        Point[] right_most_vertical_points = new Point[10];
        for (int i = 0; i < right_most_vertical_points.length; i++) {
            right_most_vertical_points[i] = new Point(9, i);
        }
        Point[] bottom_row = new Point[10];
        for (int i = 0; i < bottom_row.length; i++) {
            bottom_row[i] = new Point(i, 9);
        }
        Point[] middle_row = new Point[10];
        for (int i = 0; i < middle_row.length; i++) {
            middle_row[i] = new Point(i, 6);
        }

        for (int i = 0; i < middle_row.length; i++) {
            //test_board.hit(middle_row[i]);
            test_board.hit(bottom_row[i]);
            //test_board.hit(right_most_vertical_points[i]);
        }

        assertTrue(board_string_after_hits.equals(test_board.getHiddenStateString()));

        String empty_board_all_hit = getEmptyBoardStringWithHits();

        //hit every point in new board
        Board empty_board = new Board();
        for (int i = 0; i < empty_board.getBOARD_SIZE(); i++) {
            for (int j = 0; j < empty_board.getBOARD_SIZE(); j++) {
                empty_board.hit(new Point(i, j));
            }
        }
        //ensure all hit empty board is same
        //assertTrue(empty_board_all_hit.equals(test_board.getHiddenState()));


    }//testHiddenBoard

    @Test
    void testIsSunk(){

        //sweeper 0
        //destoryer 1
        //bship 2
        Board test_board = new Board();
        Ship minesweeper = new Minesweeper("horizontal", new Point(0,0));
        Ship destroyer = new Destroyer("horizontal", new Point(0,1));
        Ship  battleship = new Battleship("horizontal", new Point(0,2));
        test_board.placeShip(minesweeper);
        test_board.placeShip(destroyer);
        test_board.placeShip(battleship);
        //System.out.println(board.getState());
        //System.out.println(board.getHiddenState());

        //hits every point on every ship and checks that they are sunk
        Point[] points = minesweeper.getPointArray();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        Assertions.assertTrue(test_board.isSunk(minesweeper));

        points = destroyer.getPointArray();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        Assertions.assertFalse(test_board.isSunk(destroyer));
        test_board.hit(points[1]);
        Assertions.assertTrue(test_board.isSunk(destroyer));

        points = battleship.getPointArray();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        Assertions.assertFalse(test_board.isSunk(battleship));
        test_board.hit(points[2]);
        Assertions.assertTrue(test_board.isSunk(battleship));

    }//testSunk

    @Test
    void testPulseString(){
        Board board = new Board();

        Ship minesweeper = new Minesweeper("horizontal", new Point(0,0));
        Ship destroyer = new Destroyer("horizontal", new Point(0,1));
        Ship  battleship = new Battleship("horizontal", new Point(0,2));
        board.placeShip(minesweeper);
        board.placeShip(destroyer);
        board.placeShip(battleship);
        Point pulse_point = new Point(0,0);

        Board test_board = new Board();

        Ship bottom_left_corner_ship = new Minesweeper("horizontal", new Point(3, 6));
        Ship top_right_corner_down_ship = new Battleship("horizontal", new Point(3, 5));
        Ship middle_destroyer = new Destroyer("horizontal", new Point(3, 4));
        test_board.placeShip(bottom_left_corner_ship);
        test_board.placeShip(top_right_corner_down_ship);
        test_board.placeShip(middle_destroyer);
        pulse_point = new Point(5,5);
        test_board.sonarPulseString(pulse_point);


        System.out.println(test_board.getStateString());
        System.out.println(test_board.getHiddenStateString());
        System.out.println(test_board.sonarPulseString(pulse_point));
    }

    @Test
    void testPulse(){

        Point pulse_point = new Point(5,5);
        Board hard_code_board = new Board();
        Board test_board = new Board();
        Point[] points = getPulseArray(pulse_point);

        //set hard coded board points to visible
        for(int i =0; i<points.length; i++){
            if(points[i].isValid()){
                hard_code_board.getPeg(points[i]).setVisible();
            }
        }
        Board pulse_board =  test_board.sonarPulse(pulse_point);
        Assertions.assertTrue(pulse_board.equals(hard_code_board));
    }

    @Test
    void testCopy(){
        Board first_board = new Board();
        first_board.hit(new Point(1,0));
        Board copy_board = first_board.copy();
        Assertions.assertTrue(copy_board.equals(first_board));
        copy_board.hit(new Point(0,0));
        Assertions.assertFalse(copy_board.equals(first_board));

    }

    @Test
    void testEquals(){

        Board first_board = new Board();
        Point m_point = new Point(0,0);
        Point d_point = new Point(7,0);
        Point b_point = new Point(9,0);
        Point s_point = new Point(7,0);
        Ship minesweeper = new Minesweeper("v",m_point);
        Ship destroyer = new Destroyer("v", d_point);
        Ship battleship = new Battleship("v", b_point);
        Ship submarine = new Submarine("v", s_point);
        first_board.placeShip(minesweeper);
        first_board.placeShip(destroyer);
        first_board.placeShip(battleship);
        first_board.placeShip(submarine);

        Board compare_board_good = new Board();
        compare_board_good.placeShip(minesweeper);
        compare_board_good.placeShip(destroyer);
        compare_board_good.placeShip(battleship);
        compare_board_good.placeShip(submarine);

        Board compare_board_bad = new Board();
        compare_board_bad.placeShip(minesweeper);
        compare_board_good.placeShip(destroyer);
        compare_board_bad.placeShip(battleship);

        Assertions.assertTrue(first_board.equals(compare_board_good));

        System.out.println(compare_board_bad.placeShip(submarine));
        Assertions.assertTrue(!first_board.equals(compare_board_bad));


    }

    //check output to see if it works
    @Test
    void testPrint(){
        Board test_board = new Board();
        Point m_point = new Point(0,0);
        Point d_point = new Point(7,0);
        Point b_point = new Point(9,0);
        Point s_point = new Point(7,0);
        Ship minesweeper = new Minesweeper("v",m_point);
        Ship destroyer = new Destroyer("v", d_point);
        Ship battleship = new Battleship("v", b_point);
        Ship submarine = new Submarine("v", s_point);
        test_board.placeShip(minesweeper);
        test_board.placeShip(destroyer);
        test_board.placeShip(battleship);
        test_board.placeShip(submarine);
        System.out.println(test_board.getStateString());
    }

}//boardTest
