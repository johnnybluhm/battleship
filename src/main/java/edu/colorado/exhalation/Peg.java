package main.java.edu.colorado.exhalation;

public class Peg {

    private int pos_x_;
    private int pos_y_;

    //2 - mine, 3 - destroyer, 4 -battleship, 0-water
    private int type_;


    private boolean hit_;


    public Peg(int pos_x, int pos_y){
        this.pos_x_ = pos_x;
        this.pos_y_ = pos_y;
        this.type_ =0;
        this.hit_ =false;

    }

    public Peg(int pos_x, int pos_y, int type){
        this.pos_x_ = pos_x;
        this.pos_y_ = pos_y;
        this.type_ =type;
        this.hit_ =false;

    }

    public int getPos_x_() {
        return pos_x_;
    }

    public void setPos_x_(int pos_x) {
        this.pos_x_ = pos_x;
    }

    public int getPos_y_() {
        return pos_y_;
    }

    public void setPos_y_(int pos_y) {
        this.pos_y_ = pos_y;
    }

    public int getType_() {
        return type_;
    }

    public void setType_(int type) {
        this.type_ = type;
    }

    public boolean isHit() {
        return hit_;
    }

    public void hit() {
        this.hit_ = true;
    }

    public boolean hasShip(){
        if(getType_() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public char print(){
        if(isHit()){
            if(getType_()==0){
                return '#';
            }
            else{
                return 'X';
            }
        }//if isHit()
        else{
            return 'O';
        }
    }
}//Peg
