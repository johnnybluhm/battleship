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
        /*for (int i =0; i< points.length; i++){
            this.points_[i] = points[i];
        }*/
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



//    public void move(char direction){
//        Peg[] pegs = this.getPegs(); // gets pegs that ship is on
//        int length = pegs.length;
//        Point[] new_points = new Point[length]; // new array of pegs for where ship is moving to
//        for (Peg peg:
//             this.getPegs()) { // iterates through this.getPegs() and prints them out, troubleshooting it looks like
//            System.out.println(peg);
//        }
//        System.out.println("NEXT");
//        if(direction == 'E'){ // direction ships are moving is East
//            if(pegs[length - 1].getPoint().getX() == 9){ // Ship cannot go any further East, it's on the edge of the map
//                System.out.println("SHIP NAME cannot move any further East, it's on the East edge of the board!");
//            }
//            else { // Ship can move East
//                for (int i = 0; i < length; i++) { // iterates through pegs by the peg
//                    Peg current_peg = pegs[i]; // sets new peg to be old peg ship was on
//                    if (this instanceof Submarine) {
//                        current_peg.setSub(null);
//                    } else {
//                        current_peg.setShip(null);
//                    }
//
//                    Peg next_peg;
//                    Point current_peg_point = current_peg.getPoint();
//                    Point next_peg_point = new Point(current_peg_point.getX() + 1, current_peg_point.getY());
//                    next_peg = current_peg.getBoard().getPeg(next_peg_point);
//                    if (this instanceof Submarine) {
//                        current_peg.setShip(this);
//                    }
//                    next_peg.setSub((Submarine) this);
//                    } else {
//                     this.setPeg(next_peg, i);
//                    new_points[i] = next_peg_point;
//
//                }
//                for (Peg peg :
//                        this.getPegs()) {
//                    System.out.println(peg);
//                }
//                this.setPoints(new_points);
//            }
//        }//East
//    }


}

