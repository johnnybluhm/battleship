package edu.colorado.exhalation;
import edu.colorado.exhalation.Point;
import edu.colorado.exhalation.Peg;

public class Board {

    final private int BOARD_SIZE = 10;
    final private int MINESWEEPER = 0;
    final private int DESTROYER = 1;
    final private int BATTLESHIP = 2;

    private Peg[][]  peg_array_ = new Peg[BOARD_SIZE][BOARD_SIZE];

    //ships as point arrays
    private Ship[] ships_array =  {null, null, null};

    //creates board with all empty pegs, positions are set in the loop
    public Board(){
        for(int i =0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE;j++){
                this.peg_array_[i][j] = new Peg(i,j);
            }
        }
    }//constructor

    public Peg getPeg(Point point){

        return peg_array_[point.getX()][point.getY()];
    }

    public Peg getPeg(int x, int y){

        return peg_array_[x][y];
    }

    public String getState(){
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

    public String printFriendly(){
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
                board += String.valueOf(getPeg(i,j).getType_());
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
                board += String.valueOf(getPeg(i,j).getType_());
                board += " ";

                //insert | in middle of row
                if(i == 4) board += "| ";

            }//inner for
            board+="\n";
        }//outer for
        return board;
    }//printFriendly

    //returns -1 on error
    public int placeShip(Ship ship){
        int ship_type;
        int array_index;
        if(ship instanceof Minesweeper){
            ship_type = ((Minesweeper) ship).getSIZE();
            array_index = MINESWEEPER;
        }
        else if(ship instanceof Battleship){
            ship_type = ((Battleship) ship).getSIZE();
            array_index = BATTLESHIP;
        }
        else if(ship instanceof Destroyer){
            ship_type = ((Destroyer) ship).getSIZE();
            array_index = DESTROYER;
        }
        else{
            return -1;
        }

        Point[] point_array = ship.getPointArray();

        //ensure all requested pegs are water
        for(int i = 0; i<point_array.length; i++){
            Peg peg_for_ship = this.getPeg(point_array[i]);
            if(peg_for_ship.getType_() != 0){
                return -1;
            }
        }

        for(int i = 0; i<point_array.length; i++){
            Peg peg_for_ship = this.getPeg(point_array[i]);
            peg_for_ship.setType_(ship_type);
        }
        this.ships_array[array_index] = ship;

      return 1;
    }//place ship

}//Board
