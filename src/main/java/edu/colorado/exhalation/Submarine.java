package edu.colorado.exhalation;

public class Submarine extends Ship{
    final static int SIZE = 5;
    final static int CAPTAINS_QUARTERS = 3;

    public Submarine(String orientation, Point point){
        if(orientation.equals("vertical") || orientation.equals("v")){
            this.vertical_ = true;
            this.point_array_ = new Point[SIZE];
            this.point_array_[0] = point;
            //gets points below initial point
            for (int i =1; i< point_array_.length -1; i++){
                this.point_array_[i] = new Point(this.point_array_[0].getX(), this.point_array_[0].getY() + i);
            }//for
            //bump
            this.point_array_[point_array_.length-1] = new Point(point.getX()+1,point.getY()+2);
        }//if
        else if(orientation.equals("horizontal")|| orientation.equals("h")){
            this.vertical_ =false;
            this.point_array_ = new Point[SIZE];
            this.point_array_[0] = point;
            //gets points that are right to initial point
            for (int i =1; i< point_array_.length -1 ; i++){
                this.point_array_[i] = new Point(this.point_array_[0].getX() + i, this.point_array_[0].getY());
            }//for
            //bump
            this.point_array_[point_array_.length-1] = new Point(point.getX()+1,point.getY()+2);
        }//else if
        else{
            this.point_array_ = null;
        }
    }//constructor

    public Submarine(char orientation, Point point){
        if(orientation == 'v'){
            this.vertical_ = true;
            this.point_array_ = new Point[SIZE];
            this.point_array_[0] = point;
            //gets points below initial point
            for (int i =1; i< point_array_.length-1; i++){
                this.point_array_[i] = new Point(this.point_array_[0].getX(), this.point_array_[0].getY() + i);
            }//for
            //bump
            this.point_array_[point_array_.length-1] = new Point(point.getX()+1,point.getY()+2);
        }//if
        else if(orientation == 'h'){
            this.vertical_ =false;
            this.point_array_ = new Point[SIZE];
            this.point_array_[0] = point;
            //gets points that are right to initial point
            for (int i =1; i< point_array_.length-1; i++){
                this.point_array_[i] = new Point(this.point_array_[0].getX() + i, this.point_array_[0].getY());
            }//for
            //bump
            this.point_array_[point_array_.length-1] = new Point(point.getX()+2,point.getY()-1);
        }//else if
        else{
            this.point_array_ = null;
        }
    }//constructor

    public int getSIZE(){
        return SIZE;
    }

    public int getCaptainsQuarters(){
        return CAPTAINS_QUARTERS;
    }

    @Override
    public boolean isArmoured() {
        return false;
    }

}
