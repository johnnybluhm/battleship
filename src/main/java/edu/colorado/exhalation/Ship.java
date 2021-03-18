package edu.colorado.exhalation;
import java.util.HashMap;
abstract public class Ship {

    protected Point[] points_;
    protected Peg[] pegs_;
    protected boolean vertical_;
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

    abstract public int getCaptainsQuarters();
    abstract public boolean isArmoured();
    abstract public int getSIZE();
    abstract public boolean isSunk();

    //returns hit count of current weapon
    public int getHitCount(Peg peg){
        int[] hitCount = this.peg_to_array_hashMap.get(peg);
        return hitCount[peg.getBoard().getWeapon()];
    }

    //other methods may be useful in the future
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

    public void move(char direction){
        Peg[] pegs = this.getPegs();
        Point[] new_points = new Point[this.getPoints().length];
        for (Peg peg:
             this.getPegs()) {
            System.out.println(peg);
        }
        System.out.println("NEXT");
        if(direction == 'E'){
            for (int i = 0; i < pegs.length; i++) {

                Peg current_peg = pegs[i];
                if(this instanceof Submarine){
                    current_peg.setSub(null);
                }
                else{
                    current_peg.setShip(null);
                }

                Peg next_peg;
                Point current_peg_point = current_peg.getPoint();
                Point next_peg_point = new Point(current_peg_point.getX()+1, current_peg_point.getY());
                next_peg = current_peg.getBoard().getPeg(next_peg_point);
                if(this instanceof Submarine){
                    next_peg.setSub((Submarine) this);
                }
                else{
                    current_peg.setShip(this);
                }
                this.setPeg(next_peg,i);
                new_points[i] = next_peg_point;

            }
            for (Peg peg:
                    this.getPegs()) {
                System.out.println(peg);
            }
            this.setPoints(new_points);

        }//East
    }


}

