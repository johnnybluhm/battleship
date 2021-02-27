package main.java.edu.colorado.exhalation;
import edu.colorado.exhalation.Point;
abstract public class Ship {

    protected Point[] point_array_;
    protected boolean vertical_;

    public Point[] getPointArray() {
        return this.point_array_;
    }

    public boolean isVertical(){
        return this.vertical_;
    }
    public boolean isHorizontal(){
        if(this.vertical_ == true){
            return false;
        }
        else{
            return true;
        }
    }

}

