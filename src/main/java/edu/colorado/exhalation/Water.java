package edu.colorado.exhalation;

public class Water extends Ship{

    final static int SIZE = 1;
    final static int CAPTAINS_QUARTERS = 0;

    public Water(Point point){
        this.points_ = new Point[SIZE];
        this.points_[0] = point;
        this.vertical_ = false;
    }

    @Override
    public int getCaptainsQuarters() {
        return CAPTAINS_QUARTERS;
    }

    @Override
    public boolean isArmoured() {
        return false;
    }

    @Override
    public int getSIZE() {
        return 0;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

}
