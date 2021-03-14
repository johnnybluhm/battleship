package edu.colorado.exhalation;

import java.nio.channels.Pipe;

public class Peg {

    private Point point_;

    //2 - mine, 3 - destroyer, 4 -battleship, 0-water
    private int type_;
    private boolean hit_;
    private Ship ship_;
    private boolean visible_;
    private Submarine sub_;

    //keep old construcotrs so we dont have to remake tests
    public Peg(int pos_x, int pos_y){
        this.point_ = new Point(pos_x,pos_y);
        this.type_ =0;
        this.hit_ =false;
        this.ship_ = null;
        this.visible_ = false;
        this.sub_ = null;
    }
    public Peg(int pos_x, int pos_y, int type){
        this.point_ = new Point(pos_x,pos_y);
        this.type_ =type;
        this.hit_ =false;
        this.ship_ = null;
        this.visible_ = false;
        this.sub_ = null;
    }

    public Peg(Point point){
        this.point_ = new Point(point.getX(), point.getY());
        this.hit_ =false;
        this.ship_ = null;
        this.visible_ = false;
        this.sub_ = null;
    }

    public Peg(Peg copy_peg){
        this.point_ = copy_peg.getPoint();
        this.type_ = copy_peg.getType();
        this.hit_ = copy_peg.isHit();
        this.ship_ = copy_peg.getShip();
        this.visible_ = copy_peg.isVisible();
        this.sub_ = copy_peg.getSub();
    }

    public int getType() {
        return type_;
    }

    public void setType(int type) {
        this.type_ = type;
    }

    public boolean isHit() {
        return hit_;
    }

    public void hit() {
        this.hit_ = true;
    }//hit

    public boolean hasShip(){
        if(getType() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public char print(){
        if(isHit()){
            if(getType()==0){
                return '#';
            }
            else{
                return 'X';
            }
        }//if isHit()
        else{
            if(getType() == 2){
                return '2';
            }
            else if(getType() == 3){
                return '3';
            }
            else if(getType() == 4){
                return '4';
            }
            return 'O';
        }
    }

    public char printHidden() {
        if (isHit()) {
            if (getType() == 0) {
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
        return this.point_;
    }

    public void setPoint(Point point){
        this.point_.setX(point.getX());
        this.point_.setY(point.getY());
    }

    public boolean equals(Peg compare_peg){
        boolean point_same = compare_peg.getPoint().equals(this.getPoint());
        boolean type_same = compare_peg.getType() == this.getType();
        boolean hit_same = compare_peg.isHit() == this.isHit();
        boolean visibility_same = compare_peg.isVisible() == this.isVisible();
        boolean ship_same = compare_peg.getShip() == this.getShip();
        if(point_same && type_same && hit_same && visibility_same && ship_same){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isVisible() {
        return this.visible_;
    }

    public void setVisible(){
        this.visible_ = true;
    }

    public void setHidden(){
        this.visible_ = false;
    }

    public Peg copy(){
        return new Peg(this);
    }

    @Override
    public String toString() {
        if (this.getShip() == null) {
            return "Point :\n" + this.point_ + "\nVisibility: " + this.visible_ + "\nType: " + this.type_ + "\nHit: " + this.hit_ + "Ship: NULL";
        } else {
            return "Point :\n"  + this.point_ + "\nVisibility: " +this.visible_+"\nType: "+this.type_+"\nHit: "+this.hit_+"\nShip: \n"+this.getShip();
        }
    }//toString

    public Submarine getSub() {
        return this.sub_;
    }

    public void setSub(Submarine sub) {
        this.sub_ = sub;
    }
}//Peg
