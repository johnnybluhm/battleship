package edu.colorado.exhalation;


public class Destroyer extends Ship{
    final static int SIZE = 3;
    final static int CAPTAINS_QUARTERS = 1;

    public Destroyer(String orientation, Point point){
        if(orientation.equals("vertical") || orientation.equals("v")){
            this.vertical_ = true;
            this.point_array_ = new Point[SIZE];
            this.point_array_[0] = point;
            //gets points below initial point
            for (int i =1; i< point_array_.length; i++){
               this.point_array_[i] = new Point(this.point_array_[0].getX(), this.point_array_[0].getY() + i);
            }//for
        }//if
        else if(orientation.equals("horizontal")|| orientation.equals("h")){
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

    public Destroyer(char orientation, Point point){
        if(orientation == 'v'){
            this.vertical_ = true;
            this.point_array_ = new Point[SIZE];
            this.point_array_[0] = point;
            this.hit_count_.put(point,0);
            //gets points below initial point
            for (int i =1; i< point_array_.length; i++){
                this.point_array_[i] = new Point(this.point_array_[0].getX(), this.point_array_[0].getY() + i);
                this.hit_count_.put(this.point_array_[i],0);
            }//for
        }//if
        else if(orientation == 'h'){
            this.vertical_ =false;
            this.point_array_ = new Point[SIZE];
            this.point_array_[0] = point;
            this.hit_count_.put(point,0);
            //gets points that are right to initial point
            for (int i =1; i< point_array_.length; i++){
                this.point_array_[i] = new Point(this.point_array_[0].getX() + i, this.point_array_[0].getY());
                this.hit_count_.put(this.point_array_[i],0);
            }//for
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
        return true;
    }
}
