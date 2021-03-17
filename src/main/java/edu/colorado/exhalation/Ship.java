package edu.colorado.exhalation;
import java.util.HashMap;
abstract public class Ship {

    protected Point[] points_;
    protected Peg[] pegs_;
    protected boolean vertical_;
    protected boolean captains_quarters_hit_ = false;
    protected HashMap<Peg, int[]> peg_to_array_hashMap = new HashMap<Peg, int[]>();

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

    //returns hit count of current weapon
    public int getHitCount(Peg peg){
        int[] hitCount = this.peg_to_array_hashMap.get(peg);
        return hitCount[peg.getBoard().getWeapon()];
    }

    public int getHitCount(Peg peg, int weapon){
        int[] hitCount = this.peg_to_array_hashMap.get(peg);
        return hitCount[weapon];
    }

    public int[] getHitCountArray(Peg peg){
        return this.peg_to_array_hashMap.get(peg);
    }

    public void setHash(Peg peg, int[] hit_count){
        if(this.peg_to_array_hashMap.containsKey(peg)){
            this.peg_to_array_hashMap.replace(peg,hit_count);
        }
        else{
            this.peg_to_array_hashMap.put(peg, hit_count);
        }
    }//setHash

    public void setPoints(Point[] points){
        for (int i =0; i< points.length; i++){
            this.points_[i] = points[i];
        }
    }

    public String toString(){
        return "SHIP START: \n"+ points_[0];
    }


}

