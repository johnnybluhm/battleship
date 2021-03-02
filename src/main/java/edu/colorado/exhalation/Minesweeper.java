package edu.colorado.exhalation;

public class Minesweeper extends Ship{
    final private int SIZE = 2;
    final private int CAPTAINS_QUARTERS = 0;
    public Minesweeper(String orientation, Point point){
        if(orientation.equals("vertical")){
            this.vertical_ = true;
            this.point_array_ = new Point[SIZE];
            this.point_array_[0] = point;
            //gets points below initial point
            for (int i =1; i< point_array_.length; i++){
                this.point_array_[i] = new Point(this.point_array_[0].getX(), this.point_array_[0].getY() + i);
            }//for
        }//if
        else if(orientation.equals("horizontal")){
            this.vertical_ =false;
            this.point_array_ = new Point[SIZE];
            this.point_array_[0] = point;
            //gets points that are right to initial point
            for (int i =1; i< point_array_.length; i++){
                this.point_array_[i] = new Point(this.point_array_[0].getX() + i, this.point_array_[0].getY());
            }//for
        }//else if
        else{
            this.point_array_ = null;
        }
    }//constructor

    public int getSIZE(){
        return this.SIZE;
    }

    public int getCaptainsQuarters(){
        return this.CAPTAINS_QUARTERS;
    }
}
