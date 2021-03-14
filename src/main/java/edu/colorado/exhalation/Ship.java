package edu.colorado.exhalation;

abstract public class Ship {

    protected Point[] point_array_;
    protected boolean vertical_;
    protected boolean captains_quarters_hit = false;

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

    public boolean captainsQuartersHit(){
        return this.captains_quarters_hit;
    }

    public void setCaptainsQuartersHit(){
        this.captains_quarters_hit = true;
    }
    abstract public int getCaptainsQuarters();
    abstract public boolean isArmoured();
    abstract public int getSIZE();

    public String toString(){
        return "SHIP START: \n"+ point_array_[0];
    }
}

