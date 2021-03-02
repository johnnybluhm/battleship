package edu.colorado.exhalation;

//import org.junit.Test;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BoardTest {

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
                assertEquals(peg_array[i][j].getPos_x_(), test_board.getPeg(i,j).getPos_x_());
                assertEquals(peg_array[i][j].getPos_y_(), test_board.getPeg(i,j).getPos_y_());
            }
        }

    }//initialize

    @Test
    void testEmptyPrint(){
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

        Board empty_test_board = new Board();

        assertTrue(empty_board.equals(empty_test_board.getState()));

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

        assertTrue(full_board.equals(full_test_board.getState()));
    }

    @Test
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
        empty_test_board.getPeg(0,9).setType_(1);
        empty_test_board.getPeg(1,9).setType_(1);
        empty_test_board.getPeg(2,9).setType_(1);
        empty_test_board.getPeg(8,9).setType_(2);
        empty_test_board.getPeg(9,9).setType_(2);


        //hit ships
        empty_test_board.getPeg(0,9).hit();
        empty_test_board.getPeg(1,9).hit();
        empty_test_board.getPeg(2,9).hit();
        empty_test_board.getPeg(8,9).hit();
        empty_test_board.getPeg(9,9).hit();

        //hit empty square
        empty_test_board.getPeg(7,9).hit();

        System.out.println(empty_test_board.getState());
        assertTrue(board.equals(empty_test_board.getState()));
    }

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
        System.out.println(test_board.getState());
        assertTrue(board_string.equals(test_board.getState()));
        System.out.println(test_board.getState());
    }

    @Test
    void testHiddenBoard(){

        String top_of_board = "   A B C D E | F G H I J\n";
        //ensure space at the end of the test string
        String empty_row = "O O O O O | O O O O O \n";
        String seperator = "   - - - - - | - - - - -\n";
        String board_string = top_of_board;
        String last_row = "9| 0 0 O O O | O O O O O \n";

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
        System.out.println(test_board.getState());
        assertTrue(board_string.equals(test_board.printHidden()));
        System.out.println(test_board.getState());

    }

}//boardTest
