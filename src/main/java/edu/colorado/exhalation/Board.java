package main.java.edu.colorado.exhalation;

public class Board {

    private Peg[][]  peg_array_ = new Peg[10][10];

    //creates board with all empty pegs, positions are set in the loop
    public Board(){
        for(int i =0; i< this.peg_array_.length; i++){
            for(int j = 0; j< this.peg_array_[i].length;j++){
                this.peg_array_[i][j] = new Peg(i,j);
            }
        }
    }//constructor

    public Peg getPeg(int x, int y){
        return peg_array_[x][y];
    }


}//Board
