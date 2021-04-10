package edu.colorado.exhalation;

//import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;


class BoardTest {

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
    Point[] getGoodVerticalBattleshipPoints(Point start_point){

        int x = start_point.getX();
        int y = start_point.getY();
        Point[] vertical_point_array = new Point[4];
        vertical_point_array[0] = new Point(0,1);
        vertical_point_array[1] = new Point(0, 2);
        vertical_point_array[2] = new Point(0, 3);
        vertical_point_array[3] = new Point(0, 4);

        for (Point point: vertical_point_array
        ) {
            if(!point.isValid()){
                return null;
            }
        }
        return vertical_point_array;
    }
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
                assertTrue(test_board.getPeg(i,j).getShip() == null);
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

        Ship bottom_left_corner_ship = new Minesweeper('h', new Point(0,9)) ;

        test_board.placeShip(bottom_left_corner_ship);

        System.out.println(board_string);
        System.out.println(test_board.getStateString());
        assertTrue(board_string.equals(test_board.getStateString()));

        //method should return -1 as there is already a ship where we are trying to place one
        assertEquals(-2, test_board.placeShip(bottom_left_corner_ship));
        System.out.println(test_board.getStateString());

        Ship second_minesweeper = new Minesweeper('h', new Point(0,2));
        //test placing 2 Minesweepers on the board
        assertEquals(-2, test_board.placeShip(second_minesweeper));

        Ship destroyer = new Destroyer('h', new Point(0,9));

        //test for placing different ship type on top of another
        assertEquals(-3,test_board.placeShip(destroyer));

        //test invalid ship placement

        Board fresh_board = new Board();

        destroyer = new Destroyer('h', new Point(-1,-99));

        assertEquals(-4,test_board.placeShip(destroyer));
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

        Ship bottom_left_corner_ship = new Minesweeper('h', new Point(0, 9));
        Ship top_right_corner_down_ship = new Battleship('v', new Point(9, 0));
        Ship middle_destroyer = new Destroyer('h', new Point(5, 6));
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
    void testPulseString(){
        Board board = new Board();

        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        board.placeShip(minesweeper);
        board.placeShip(destroyer);
        board.placeShip(battleship);
        Point pulse_point = new Point(0,0);

        Board test_board = new Board();

        Ship bottom_left_corner_ship = new Minesweeper('h', new Point(3, 6));
        Ship top_right_corner_down_ship = new Battleship('h', new Point(3, 5));
        Ship middle_destroyer = new Destroyer('h', new Point(3, 4));
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
        Ship minesweeper = new Minesweeper('v',m_point);
        Ship destroyer = new Destroyer('v', d_point);
        Ship battleship = new Battleship('v', b_point);
        Ship submarine = new Submarine('v', s_point);
        first_board.placeShip(minesweeper);
        first_board.placeShip(destroyer);
        first_board.placeShip(battleship);
        first_board.placeShip(submarine);

        Board compare_board_good = new Board();
        Ship minesweeper2 = new Minesweeper('v',m_point);
        Ship destroyer2 = new Destroyer('v', d_point);
        Ship battleship2 = new Battleship('v', b_point);
        Ship submarine2 = new Submarine('v', s_point);
        compare_board_good.placeShip(minesweeper2);
        compare_board_good.placeShip(destroyer2);
        compare_board_good.placeShip(battleship2);
        compare_board_good.placeShip(submarine2);

        Board compare_board_bad = new Board();
        compare_board_bad.placeShip(minesweeper);
        compare_board_good.placeShip(destroyer);
        compare_board_bad.placeShip(battleship);

        Assertions.assertTrue(first_board.equals(compare_board_good));

        //System.out.println(compare_board_bad.placeShip(submarine));
        //Assertions.assertTrue(!first_board.equals(compare_board_bad));


    }

    //check output to see if it works
    @Test
    void testPrint(){
        Board test_board = new Board();
        Point m_point = new Point(0,0);
        Point d_point = new Point(7,0);
        Point b_point = new Point(9,0);
        Point s_point = new Point(7,0);
        Ship minesweeper = new Minesweeper('v',m_point);
        Ship destroyer = new Destroyer('v', d_point);
        Ship battleship = new Battleship('v', b_point);
        Ship submarine = new Submarine('v', s_point);
        test_board.placeShip(minesweeper);
        test_board.placeShip(destroyer);
        test_board.placeShip(battleship);
        test_board.placeShip(submarine);
        System.out.println(test_board.getStateString());
    }

    @Test
    void testWeapon(){
        Board board = new Board();

        Assertions.assertTrue(board.getWeapon() == Board.BOMB);

        Assertions.assertTrue(board.setWeapon(3)== -1);
        board.setWeapon(Board.LASER);
        Assertions.assertTrue(board.getWeapon()== Board.LASER);
    }

    @Test
    void testLaser(){

        //SUB ARMOURED SHIP UNDERNEATH CASE
        Board board = new Board();
        Point start_point = new Point(5,0);
        Ship battleship = new Battleship('v', start_point);
        Ship sub = new Submarine('v', start_point);
        Point[] b_points = getGoodVerticalBattleshipPoints(start_point);
        Point[] sub_points = getVerticalSubPoints(start_point);
        board.placeShip(battleship);
        board.placeShip(sub);
        board.setWeapon(Board.LASER);

        //hit sub at every point
        for (Point point:
             sub_points) {
            board.hit(point);
        }
        Assertions.assertTrue(board.isSunk(sub));
        //due to cpt's quarter ship should still be up
        Assertions.assertFalse(board.isSunk(battleship));
        //END SUB ARMOURED SHIP UNDERNEATH CASE
    }

    @Test
    void testHitting(){

        Point start_point = new Point(5,5);

        Ship minesweeper = new Minesweeper('v',start_point);
        Ship submarine = new Submarine('v', start_point);

        Board board = new Board();

        board.placeShip(minesweeper);
        board.placeShip(submarine);

        Point[] battleship_points = Battleship.getVerticalPoints(start_point);
        Point[] sub_points = Submarine.getVerticalPoints(start_point);

        for (Point point:
             battleship_points) {
            board.hit(point);
        }

        //Assertions.assertTrue(minesweeper.isSunk());
        //Assertions.assertTrue(!submarine.isSunk());

    }

    @Test
    void testPlace(){
        Point start_point = new Point(5,5);

        Ship minesweeper = new Minesweeper('v',start_point);
        //Ship submarine = new Submarine('v', start_point);

        Board first_board = new Board();
        Board second_board = new Board();

        first_board.placeShip(minesweeper);
        second_board.placeShip(minesweeper);

        Assertions.assertTrue(first_board.equals(second_board));

        System.out.println();

    }

    @Test
    void testStack() {
        Board board = new Board();
        Board compare_board = new Board();

        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));

        board.placeShip(minesweeper);
        board.placeShip(destroyer);
        board.placeShip(battleship);
        board.placeShip(submarine);

        compare_board.placeShip(minesweeper);
        compare_board.placeShip(destroyer);
        compare_board.placeShip(battleship);
        compare_board.placeShip(submarine);

        Stack<Board> stack = new Stack<Board>();
        stack.push(board.copy());
        Assertions.assertTrue(stack.peek().equals(compare_board));
        board.move('E');
        System.out.println(stack.peek().getStateString());
        System.out.println(compare_board.getStateString());
        Assertions.assertTrue(stack.pop().equals(compare_board));

    }

    @Test
    void testAirStrike(){
        Board compare_board = new Board();

        Point[] row_points = Board.getRowPoints(0);

        for (int i = 0; i < row_points.length; i++) {
            compare_board.hit(row_points[i]);
            Assertions.assertTrue(row_points[i].getX()==i);
        }

        Board test_board = new Board();
        test_board.airStrike(0);
        Assertions.assertTrue(test_board.equals(compare_board));
    }



}//boardTest
