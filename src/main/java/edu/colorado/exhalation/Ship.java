package main.java.edu.colorado.exhalation;
import edu.colorado.exhalation.Point;
abstract public class Ship {

    private Point[] point_array_;
    private boolean vertical_;

    //constructor for testing purposes
    public Ship(Point[] point_array){
        this.point_array_ = point_array;
    }

    public Point[] getPointArray() {
        return this.point_array_;
    }

}

