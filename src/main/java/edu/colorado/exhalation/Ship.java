package edu.colorado.exhalation;
import java.util.HashMap;
abstract public class Ship {

    protected Point[] points_;
    protected Peg[] pegs_;
    protected boolean vertical_;
    protected boolean captains_quarters_hit_ = false;
    protected HashMap<Peg, Integer> hit_count_ = new HashMap<Peg, Integer>();

    public Point[] getPoints() {
        return this.points_;
    }

    public void setPeg(Peg peg, int index){
        this.pegs_[index] = peg;
    }

    public Peg[] getPegs(){
        return this.pegs_;
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

        for(int i = 0; i< this.points_.length; i++){
            if(this.points_[i].isValid()==false){
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
        for (int i = 0; i <ship.getPoints().length; i++) {
            if(!ship.getPoints()[i].equals(this.getPoints()[i])){
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

    public int getHitCount(Peg peg){
        return this.hit_count_.get(peg);
    }

    public void setHash(Peg peg, int hit_count){
        if(this.hit_count_.containsKey(peg)){
            this.hit_count_.replace(peg,hit_count);
        }
        else{
            this.hit_count_.put(peg, hit_count);
        }
    }//setHash



    /*void hit(Point point){
        int count = this.hit_count_.get(point);
        count++;
        this.hit_count_.replace(point,count);
    }*/

    public String toString(){
        return "SHIP START: \n"+ points_[0];
    }


}

