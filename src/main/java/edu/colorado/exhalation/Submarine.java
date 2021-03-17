package edu.colorado.exhalation;

public class Submarine extends Ship{
    final static int SIZE = 5;
    final static int CAPTAINS_QUARTERS = 3;

    public Submarine(char orientation, Point point){
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
        return false;
    }

    public static Point[] getVerticalPoints(Point start_point){

        int x = start_point.getX();
        int y = start_point.getY();
        Point[] vertical_point_array = new Point[5];
        vertical_point_array[0] = start_point;
        //tail
        vertical_point_array[1] = new Point(x,y+1);
        vertical_point_array[2] = new Point(x,y+2);
        vertical_point_array[3] = new Point(x,y+3);
        //bump
        vertical_point_array[4] = new Point(x+1,y+2);
        return vertical_point_array;
    }

    public static Point[] getHorizontalPoints(Point start_point){
        int x = start_point.getX();
        int y = start_point.getY();
        Point[] horizontal_point_array = new Point[5];
        horizontal_point_array[0] = start_point;
        //tail
        horizontal_point_array[1] = new Point(x+1,y);
        horizontal_point_array[2] = new Point(x+2,y);
        horizontal_point_array[3] = new Point(x+3,y);
        //bump
        horizontal_point_array[4] = new Point(x+2,y-1);
        return horizontal_point_array;
    }

}
