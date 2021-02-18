package main.java.edu.colorado.exhalation;

public class Board {

    final private int BOARD_SIZE = 10;

    private Peg[][]  peg_array_ = new Peg[BOARD_SIZE][BOARD_SIZE];

    //creates board with all empty pegs, positions are set in the loop
    public Board(){
        for(int i =0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE;j++){
                this.peg_array_[i][j] = new Peg(i,j);
            }
        }
    }//constructor

    public Peg getPeg(int x, int y){
        return peg_array_[x][y];
    }

    public String print(){
        String top_of_board = "   A B C D E | F G H I J\n";
        String seperator = "   - - - - - | - - - - -\n";
        String board = top_of_board;


        //create first half of board before seperator is added
        for(int j =0; j< BOARD_SIZE/2; j++){
            //outer loop goes downward
            String num = String.valueOf(j);
            board += num+"| ";
            //inner loop goes left to right
            for(int i = 0; i< BOARD_SIZE; i++){
                //go across the row
                board += getPeg(i,j).print();
                board += " ";



                //insert | in middle of row
                if(i == 4) board += "| ";

            }//inner for
            board+="\n";
        }//outer for

        board += seperator;

        //create second half of board after seperator is added
        for(int j =BOARD_SIZE/2; j< BOARD_SIZE; j++){
            //outer loop goes downward
            String num = String.valueOf(j);
            board += num+"| ";
            //inner loop goes left to right
            for(int i = 0; i< BOARD_SIZE; i++){
                //go across the row
                board += getPeg(i,j).print();
                board += " ";

                //insert | in middle of row
                if(i == 4) board += "| ";

            }//inner for
            board+="\n";
        }//outer for
        return board;
    }//print


}//Board
