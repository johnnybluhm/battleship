package edu.colorado.exhalation;

public class Minesweeper extends Ship{
    final static int SIZE = 2;
    final static int CAPTAINS_QUARTERS = 0;
    
    public Minesweeper(char orientation, Point point){
        if(orientation == 'v'){
            this.vertical_ = true;
            this.points_ = new Point[SIZE];

            Point[] points = getVerticalPoints(point);
            this.setPoints(points);
        }//if
        else if(orientation == 'h'){
            this.vertical_ = false;
            this.points_ = new Point[SIZE];

            Point[] points = getHorizontalPoints(point);
            this.setPoints(points);
        }
    }//constructor

    public Minesweeper(Ship copy_ship){
        this.vertical_ = copy_ship.isVertical();
        this.points_ = new Point[SIZE];
        Point[] points = copy_ship.getPoints();
        this.setPoints(points);
    }
    public int getSize(){
        return SIZE;
    }
    @Override
    public boolean isArmoured() {
        return false;
    }
    public static Point[] getVerticalPoints(Point start_point){

        int x = start_point.getX();
        int y = start_point.getY();
        Point[] vertical_point_array = new Point[SIZE];
        vertical_point_array[0] = start_point;
        //tail
        vertical_point_array[1] = new Point(x,y+1);
        /*for (Point point: vertical_point_array
        ) {
            if(!point.isValid()){
                return null;
            }
        }*/
        return vertical_point_array;
    }
    public static Point[] getHorizontalPoints(Point start_point){
        int x = start_point.getX();
        int y = start_point.getY();
        Point[] vertical_point_array = new Point[SIZE];
        vertical_point_array[0] = start_point;
        //tail
        vertical_point_array[1] = new Point(x+1,y);
        return vertical_point_array;
    }

}
