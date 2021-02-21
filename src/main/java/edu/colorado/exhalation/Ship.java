package edu.colorado.exhalation;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {
    private String name;
    // TODO: create appropriate getter and setter methods
    // TODO: Understand encapsulation
    // TODO: Understand what these todo comments mean
    //team exhalation, duo 1 was here

    private boolean sunk_;

    private Peg location_[]; // 1 is up, 2 is right, 3 is down, 4 is left

    public Ship(){
        this.sunk_ = false;
    }

    public Ship(Peg location[]){
        this.sunk_ = false;
        this.location_ = location;
    }

    public boolean isSunk() {
        int temp = 0;
        for(int i = 0; i < location_.length; i++){
            if(location_[i].isHit()){
                temp++;
            }
        }
        if(temp == location_.length) {
            this.sunk_ = true;
            return true;
        }
        System.out.println(this.sunk_);
        return sunk_;
    }

    public  void show()     {     // dunno why this is here maybe it is just an example method
        System.out.println("IF you can't see this then something is severely wrong!!");
    }
}
