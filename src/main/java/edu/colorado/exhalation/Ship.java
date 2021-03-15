package edu.colorado.exhalation;
import java.util.HashMap;
abstract public class Ship {

    protected Point[] point_array_;
    protected boolean vertical_;
    protected boolean captains_quarters_hit_ = false;
    protected HashMap<Point, Integer> hit_count_ = new HashMap<Point, Integer>();

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

    public boolean isValid(){

        for(int i =0; i< this.point_array_.length; i++){
            if(this.point_array_[i].isValid()==false){
                return false;
            }
        }
        return true;
    }

    public boolean equals(Ship ship){
        if(ship == null){
            if(this == null){
                return true;
            }
            else{
                return false;
            }
        }
        for (int i = 0; i <ship.getPointArray().length; i++) {
            if(!ship.getPointArray()[i].equals(this.getPointArray()[i])){
                return false;
            }
        }
        return true;
    }

    public boolean captainsQuartersHit(){
        return this.captains_quarters_hit_;
    }

    public void setCaptainsQuartersHit(){
        this.captains_quarters_hit_ = true;
    }
    abstract public int getCaptainsQuarters();
    abstract public boolean isArmoured();
    abstract public int getSIZE();

    int getHitCount(Point point){
        return this.hit_count_.get(point);
    }

    void hit(Point point){
        int count = this.hit_count_.get(point);
        count++;
        this.hit_count_.replace(point,count);
    }

    public String toString(){
        return "SHIP START: \n"+ point_array_[0];
    }


}

