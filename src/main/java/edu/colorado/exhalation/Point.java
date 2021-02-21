package main.java.edu.colorado.exhalation;

public class Point {

    private int x_;
    private int y_;

    public Point(int x, int y){
        this.x_ = x;
        this.y_ = y;
    }


    public int getX_() {
        return x_;
    }

    public void setX_(int x_) {
        this.x_ = x_;
    }

    public int getY_() {
        return y_;
    }

    public void setY_(int y_) {
        this.y_ = y_;
    }

    public boolean verify(){
        if(this.x_ <0 || this.x_ > 9){
            return false;
        }
        else if (this.y_ <0 || this.y_ > 9){
            return false;
        }
        else {
            return true;
        }
    } //verify
}

