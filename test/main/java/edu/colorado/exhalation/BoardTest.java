package main.java.edu.colorado.exhalation;

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

    }//inialize

    @Test
    void testPrint(){
        String top_of_board = "   A B C D E | F G H I J\n";
        String empty_row = "O O O O O | O O O O O\n";
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

        assertTrue(empty_board.equals(empty_test_board.print()));

        //System.out.println(empty_board);
    }

}//boardTest
