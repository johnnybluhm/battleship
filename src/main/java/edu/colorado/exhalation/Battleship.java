package edu.colorado.exhalation;

public class Battleship extends Ship{

    final static int SIZE = 4;
    final static int CAPTAINS_QUARTERS = 2;

    public Battleship(String orientation, Point point){
        if(orientation.equals("vertical") || orientation.equals("v")){
            this.vertical_ = true;
            this.points_ = new Point[SIZE];
            this.points_[0] = point;
            //gets points below initial point
            for (int i = 1; i< points_.length; i++){
                this.points_[i] = new Point(this.points_[0].getX(), this.points_[0].getY() + i);
            }//for
        }//if
        else if(orientation.equals("horizontal") || orientation.equals("h")){
            this.vertical_ =false;
            this.points_ = new Point[SIZE];
            this.points_[0] = point;
            //gets points that are right to initial point
            for (int i = 1; i< points_.length; i++){
                this.points_[i] = new Point(this.points_[0].getX() + i, this.points_[0].getY());
            }//for
        }//else if
        else{
            this.points_ = null;
        }
    }//constructor

    public Battleship(char orientation, Point point){
        if(orientation == 'v'){
            this.vertical_ = true;
            this.points_ = new Point[SIZE];
            this.points_[0] = point;
            this.hit_count_.put(point,0);
            //gets points below initial point
            for (int i = 1; i< points_.length; i++){
                this.points_[i] = new Point(this.points_[0].getX(), this.points_[0].getY() + i);
                this.hit_count_.put(this.points_[i],0);
            }//for
        }//if
        else if(orientation == 'h'){
            this.vertical_ =false;
            this.points_ = new Point[SIZE];
            this.points_[0] = point;
            this.hit_count_.put(point,0);
            //gets points that are right to initial point
            for (int i = 1; i< points_.length; i++){
                this.points_[i] = new Point(this.points_[0].getX() + i, this.points_[0].getY());
                this.hit_count_.put(this.points_[i],0);
            }//for
        }//else if
        else{
            this.points_ = null;
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

    public static Point[] getVerticalPoints(Point start_point){

        int x = start_point.getX();
        int y = start_point.getY();
        Point[] vertical_point_array = new Point[SIZE];
        vertical_point_array[0] = start_point;
        //tail
        vertical_point_array[1] = new Point(x,y+1);
        vertical_point_array[2] = new Point(x,y+2);
        vertical_point_array[3] = new Point(x,y+3);
        for (Point point: vertical_point_array
        ) {
            if(!point.isValid()){
                return null;
            }
        }
        return vertical_point_array;
    }

    public static Point[] getHorizontalPoints(Point start_point){
        int x = start_point.getX();
        int y = start_point.getY();
        Point[] vertical_point_array = new Point[SIZE];
        vertical_point_array[0] = start_point;
        //tail
        vertical_point_array[1] = new Point(x+1,y);
        vertical_point_array[2] = new Point(x+2,y);
        vertical_point_array[2] = new Point(x+3,y);
        for (Point point: vertical_point_array
        ) {
            if(!point.isValid()){
                return null;
            }
        }
        return vertical_point_array;
    }
}//battleship
