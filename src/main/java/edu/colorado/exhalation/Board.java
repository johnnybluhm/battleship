package edu.colorado.exhalation;
import edu.colorado.exhalation.Point;
import edu.colorado.exhalation.Peg;

public class Board {

    final private int BOARD_SIZE = 10;
    final private int MINE_SWEEPER = 2;
    final private int DESTROYER = 3;
    final private int BATTLESHIP = 4;

    private Peg[][]  peg_array_ = new Peg[BOARD_SIZE][BOARD_SIZE];

    //ships as point arrays
    private Point[] minesweeper = new Point[MINE_SWEEPER];
    private Point[] destroyer = new Point[DESTROYER];
    private Point[] battleship = new Point[BATTLESHIP];
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
    public int placeShip(Point[] point_array){
        int size = point_array.length;

        //checks all points can be on board
        for(int i =0; i< size; i++){
            if(!point_array[i].verify()){
                return -1;
            }
        }

        //handle minesweeper
        if (size == MINE_SWEEPER) {
            Point point1 = point_array[0];
            Point point2 = point_array[1];
            if( point1 == point2){
                return -1;
            }
            //ship is vertical
            else if(point1.getX() == point2.getX()){

                if (point1.getY()-point2.getY() == 1 || point1.getY()-point2.getY() == -1 ){
                    // point is valid
                    this.getPeg(point1).setType_(MINE_SWEEPER);
                    this.getPeg(point2).setType_(MINE_SWEEPER);
                }
                else {
                    //point is invalid
                    return -1;
                }
            }
            //ship is horizontal
            else if(point1.getY() == point2.getY()){

                if (point1.getX()-point2.getX() == 1 || point1.getX()-point2.getX() == -1 ){
                    // point is valid
                    this.getPeg(point1).setType_(MINE_SWEEPER);
                    this.getPeg(point2).setType_(MINE_SWEEPER);
                }
                else {
                    //point is invalid
                    return -1;
                }
            }
        }//MINESWEEPER

        //handle destroyer
        else if( size == DESTROYER){

            Point point1 = point_array[0];
            Point point2 = point_array[1];
            Point point3 = point_array[2];
            if( point1 == point2 || point1 == point3 || point2 == point3){
                return -1;
            }
            //ship is vertical
            else if(point1.getX() == point2.getX() && point1.getX() == point3.getX()) {

                int point_12_diff = point1.getY() - point2.getY();
                int point_23_diff = point2.getY() - point3.getY();

                if ((point_12_diff == 1 && point_23_diff == 2) || (point_12_diff == -1 && point_23_diff == -2)) {
                    //points are adjacent
                    this.getPeg(point1).setType_(DESTROYER);
                    this.getPeg(point2).setType_(DESTROYER);
                    this.getPeg(point3).setType_(DESTROYER);
                    return 1;
                } else {
                    return -1;
                }
            }
                //ship is vertical
            else if(point1.getY() == point2.getY() && point1.getY() == point3.getY()) {

                int point_12_diff = point1.getX() - point2.getX();
                int point_23_diff = point2.getX() - point3.getX();

                if ((point_12_diff == 1 && point_23_diff == 2) || (point_12_diff == -1 && point_23_diff == -2)) {
                    //points are adjacent
                    this.getPeg(point1).setType_(DESTROYER);
                    this.getPeg(point2).setType_(DESTROYER);
                    this.getPeg(point3).setType_(DESTROYER);
                    return 1;
                } else {
                    return -1;
                }


            }

        }//DESTROYER

        //handle battleship
        else if (size == BATTLESHIP){
            Point point1 = point_array[0];
            Point point2 = point_array[1];
            Point point3 = point_array[2];
            Point point4 = point_array[3];
            if( point1 == point2 || point1 == point3 || point2 == point3 || point1 == point4 || point2 == point4 || point3 == point4){
                return -1;
            }
            //ship is vertical
            else if(point1.getX() == point2.getX() && point1.getX() == point3.getX() && point1.getX() == point4.getX()) {

                int point_12_diff = point1.getY() - point2.getY();
                int point_23_diff = point2.getY() - point3.getY();
                int point_34_diff = point3.getY() - point4.getY();

                if ((point_12_diff == 1 && point_23_diff == 2 && point_34_diff == 3) || (point_12_diff == -1 && point_23_diff == -2 && point_34_diff == -3)) {
                    //points are adjacent
                    this.getPeg(point1).setType_(BATTLESHIP);
                    this.getPeg(point2).setType_(BATTLESHIP);
                    this.getPeg(point3).setType_(BATTLESHIP);
                    this.getPeg(point3).setType_(BATTLESHIP);
                    return 1;
                } else {
                    return -1;
                }
            }
            //ship is vertical
            else if(point1.getY() == point2.getY() && point1.getY() == point3.getY() && point1.getY() == point4.getY()) {

                int point_12_diff = point1.getX() - point2.getX();
                int point_23_diff = point2.getX() - point3.getX();
                int point_34_diff = point3.getX() - point4.getX();

                if ((point_12_diff == 1 && point_23_diff == 2 && point_34_diff == 3) || (point_12_diff == -1 && point_23_diff == -2 && point_34_diff == -3)) {
                    //points are adjacent
                    this.getPeg(point1).setType_(BATTLESHIP);
                    this.getPeg(point2).setType_(BATTLESHIP);
                    this.getPeg(point3).setType_(BATTLESHIP);
                    this.getPeg(point3).setType_(BATTLESHIP);
                    return 1;
                } else {
                    return -1;
                }
            }
            else{
                return -1;
            }
        }//BATTLESHIP

        //error
        else{
            return -1;
        }
        return -1;
    }



}//Board
