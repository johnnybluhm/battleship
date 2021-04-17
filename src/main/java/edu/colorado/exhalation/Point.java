package edu.colorado.exhalation;

public class Point {

    private int x_;
    private int y_;

    public Point(int x, int y){
        this.x_ = x;
        this.y_ = y;
    }

    public int getX() {
        return x_;
    }

    public void setX(int x_) {
        this.x_ = x_;
    }

    public int getY() {
        return y_;
    }

    public void setY(int y_) {
        this.y_ = y_;
    }

    public boolean isValid(){
        if(this == null){
            return false;
        }
        if(this.x_ <0 || this.x_ > 9){
            return false;
        }
        else if (this.y_ <0 || this.y_ > 9){
            return false;
        }
        else {
            return true;
        }
    } //isValid()


    public boolean equals(Point point){
        if(getY() == point.getY() && getX() == point.getX()){
            return true;
        }
        else {
            return false;
        }
    }

    public int hashCode(){
        final int prime = 31;
        int result =this.getX() + this.getY();
        result = prime*result;
        return result;
    }//hashcode

    @Override
    public String toString(){
        return "x: " + this.getX() +"\ny: "+this.getY();
    }
}

