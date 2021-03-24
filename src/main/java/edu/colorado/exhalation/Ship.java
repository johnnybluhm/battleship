package edu.colorado.exhalation;
import java.util.HashMap;
abstract public class Ship {

    protected Point[] points_;
    protected boolean vertical_;

    public Point[] getPoints() {
        return this.points_;
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

//   public int move(char direction){
//        if (direction == 'E'){
//            if(this.isSunk()){
//              return -1;
//            }
//            Peg[] old_pegs = new Peg[this.getPegs().length];
//            Peg[] new_pegs = new Peg[this.getPegs().length];
//            Point[] new_points = new Point[this.getPegs().length];
//
//            for (int i = this.getPegs().length -1 ; i >= 0; i--) {
//                Peg current_peg = this.getPegs()[i];
//                old_pegs[i] = current_peg;
//                Point next_peg_point = new Point(current_peg.getPoint().getX()+1, current_peg.getPoint().getY());
//                Peg next_peg = current_peg.getBoard().getPeg(next_peg_point);
//                this.getPoints()[i] = next_peg_point;
//                new_pegs[i] = next_peg;
//                new_points[i] = next_peg_point;
//                this.setPeg(next_peg, i);
//                //this.getPoints()[i] = next_peg_point;
//            }
//
//            for (int i = 0; i< this.getPegs().length;i++) {
//                Peg old_peg = old_pegs[i];
//                Peg new_peg = new_pegs[i];
//                if(this instanceof Submarine){
//                    old_peg.setSub(null);
//                    new_peg.setSub((Submarine) this);
//                }
//                else{
//                    System.out.println(old_peg);
//                    old_peg.setShip(null);
//                    new_peg.setShip(this);
//                    System.out.println(old_peg);
//                }
//            }
//            /*for (int i =0; i< old_pegs.length; i++) {
//                System.out.println(this.getPegs()[i]);
//                System.out.println(old_pegs[i]);
//            }*/
//        }
//        return 1;
//    }



}

