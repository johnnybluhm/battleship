package edu.colorado.exhalation;
abstract public class Ship {

    protected Point[] points_;
    protected boolean vertical_;
    protected boolean sunk_ = false;

    public Point[] getPoints() {
        return this.points_;
    }
    public boolean isVertical(){
        return this.vertical_;
    }
    public boolean isHorizontal(){
        return !this.vertical_;
    }
    public boolean isValid(){
        if(points_ == null){
            return false;
        }
        for(int i = 0; i< this.points_.length; i++){
            if(!this.points_[i].isValid()){
                return false;
            }
        }
        return true;
    }

    abstract public boolean isArmoured();
    abstract public int getSize();

    public void setPoints(Point[] points){
        this.points_ = points;
    }

    public String toString(){
        return "SHIP START: \n"+ points_[0];
    }

    public boolean equals(Ship compare_ship){
        if(compare_ship == null){
            return false;
        }
        Point[] compare_ship_points = compare_ship.getPoints();
        if(this.getPoints().length!=compare_ship.getPoints().length){
            return false;
        }
        for (int i = 0; i < compare_ship_points.length; i++) {
            if(!compare_ship_points[i].equals(this.getPoints()[i])){
                return false;
            }
        }
        return true;
    }

    public void setSunk(){
        this.sunk_ = true;
    }

    public boolean isSunk(){
        return this.sunk_;
    }

}

