package edu.colorado.exhalation;

public class Battleship extends Ship{

    final static int SIZE = 4;
    final static int CAPTAINS_QUARTERS = 2;

    public Battleship(char orientation, Point point){
        if(orientation == 'v'){
            this.vertical_ = true;
            this.points_ = new Point[SIZE];
            this.pegs_ = new Peg[SIZE];
            Point[] points = getVerticalPoints(point);
            this.setPoints(points);
        }//if
        else if(orientation == 'h'){
            this.vertical_ = false;
            this.points_ = new Point[SIZE];
            this.pegs_ = new Peg[SIZE];
            Point[] points = getHorizontalPoints(point);
            this.setPoints(points);
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
        vertical_point_array[3] = new Point(x+3,y);
        return vertical_point_array;
    }
}//battleship
