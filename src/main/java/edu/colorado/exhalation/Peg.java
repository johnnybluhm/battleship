package edu.colorado.exhalation;

public class Peg {

    private Point point_;

    private boolean hit_;
    private Ship ship_;
    private boolean visible_;
    private Submarine sub_;
    private Board board_ = null;
    private int hit_count_ = 0;

    //keep old constructor so we don't have to remake tests
    public Peg(int pos_x, int pos_y){
        this.point_ = new Point(pos_x,pos_y);
        this.hit_ =false;
        this.ship_ = null;
        this.visible_ = false;
        this.sub_ = null;
    }

    public Peg(int pos_x, int pos_y, Board board){
        this.point_ = new Point(pos_x,pos_y);
        this.hit_ =false;
        this.ship_ = null;
        this.visible_ = false;
        this.sub_ = null;
        this.board_ = board;
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
        this.hit_ = copy_peg.isHit();
        this.ship_ = copy_peg.getShip();
        this.visible_ = copy_peg.isVisible();
        this.sub_ = copy_peg.getSub();
        this.hit_count_ = copy_peg.getHitCount();
    }

    public boolean isHit() {
        return hit_;
    }

    public void hit() {
        this.hit_ = true;
        this.hit_count_++;

        //change hash map value to int array
        //then we can check how many times peg has been hit by
        //each weapon
        //here we do what he have to based on weapon, and can
        //update hit count in array by doing hit_count_[Board.BOMB]++
        if(ship_!= null){
            this.getShip().setHash(this, this.hit_count_);
        }
        if(this.sub_ != null){
            this.getSub().setHash(this, this.hit_count_);
        }
    }//hit

    public boolean hasShip(){
        if(this.ship_ == null){
            return false;
        }
        else{
            return true;
        }
    }

   public char print(){
        if(isHit()){
            if(this.getShip()==null){
                return '#';
            }
            else{
                return 'X';
            }
        }//if isHit()
        else{
            if(this.getSub() == null && this.getShip() != null) {
                //only ship
                String size = String.valueOf(this.getShip().getSIZE());
                return size.charAt(0);
            } else if(this.getShip() == null && this.getSub() != null){
                //only sub
                String size = String.valueOf(this.getSub().getSIZE());
                return size.charAt(0);
            }
            else if(this.getSub() != null && this.getShip() != null){
                //ship and sub
                String size = String.valueOf(this.getShip().getSIZE());
                return size.charAt(0);
            }
            else{
                //no ship no sub
                    return 'O';
                }
            }
    }//print

   public char printHidden() {
        if (isHit()) {
            if (this.getShip()== null) {
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
        boolean hit_same = compare_peg.isHit() == this.isHit();
        boolean visibility_same = compare_peg.isVisible() == this.isVisible();
        boolean ship_same = compare_peg.getShip() == this.getShip();
        boolean sub_same = compare_peg.getSub() == this.getSub();
        if(point_same && hit_same && visibility_same && ship_same && sub_same){
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
            return "Point :\n" + this.point_ + "\nVisibility: " + this.visible_ +"\nHit: " + this.hit_ + "Ship: NULL";
        } else {
            return "Point :\n"  + this.point_ + "\nVisibility: " +this.visible_+"\nHit: "+this.hit_+"\nShip: \n"+this.getShip();
        }
    }//toString

    public Submarine getSub() {
        return this.sub_;
    }

    public void setSub(Submarine sub) {
        this.sub_ = sub;
    }

    public Board getBoard() {
        return board_;
    }

    public void setBoard(Board board) {
        this.board_ = board;
    }

    public int getHitCount() {
        return hit_count_;
    }
}//Peg
