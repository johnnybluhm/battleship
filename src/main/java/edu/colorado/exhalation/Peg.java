package edu.colorado.exhalation;

public class Peg {

    private int pos_x_;
    private int pos_y_;

    //2 - mine, 3 - destroyer, 4 -battleship, 0-water
    private int type_;
    private boolean hit_;
    private Ship ship_;


    public Peg(int pos_x, int pos_y){
        this.pos_x_ = pos_x;
        this.pos_y_ = pos_y;
        this.type_ =0;
        this.hit_ =false;
        this.ship_ = null;

    }

    public Peg(int pos_x, int pos_y, int type){
        this.pos_x_ = pos_x;
        this.pos_y_ = pos_y;
        this.type_ =type;
        this.hit_ =false;
        this.ship_ = null;

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
    }//hit

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
            if(getType_() == 2){
                return '2';
            }
            else if(getType_() == 3){
                return '3';
            }
            else if(getType_() == 4){
                return '4';
            }
            return 'O';
        }
    }

    public char printHidden() {
        if (isHit()) {
            if (getType_() == 0) {
                return '#';
            } else {
                return 'X';
            }

        }
        else {
            return 'O';
        }
    }//printHidden()

    public void setShip(Ship ship) {
        this.ship_ = ship;
    }

    public Ship getShip() {
        return ship_;
    }

    public Point getPoint(){
        return new Point(this.pos_x_,this.pos_y_);
    }

    public void setPoint(Point point){
        this.pos_x_ = point.getX();
        this.pos_y_=point.getY();
    }

    public boolean equals(Peg compare_peg){
        boolean point_same = compare_peg.getPoint().equals(this.getPoint());
        boolean type_same = compare_peg.getType_() == this.getType_();
        boolean hit_same = compare_peg.isHit() == this.isHit();
        if(point_same && type_same && hit_same){
            return true;
        }
        else {
            return false;
        }
    }

    /*public boolean overlap() {
        if(print() == '#'){
            System.out.println("Already attacked here! It was a miss!");
            return true;
        }
        else if(print() == 'X') {
            System.out.println("Already attacked here! It was a hit!");
            return true;
        }
        else if(hasShip()){
            if(getType_() == 2){
                System.out.println("Minesweeper is already here! Place ship elsewhere!");
                return true;
            }
            else if(getType_ () == 3){
                System.out.println("Destroyer is already here! Place ship elsewhere!");
                return true;
            }
            else if(getType_() == 4){
                System.out.println("Battleship is already here! Place ship elsewhere!");
                return true;
            }
            else{
                System.out.println("You really shouldn't be seeing this");
            }
        }
        return false;
    }*/
}//Peg
