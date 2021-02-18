package main.java.edu.colorado.exhalation;

public class Peg {

    private int pos_x_;
    private int pos_y_;
    private int type_;
    public Peg(int pos_x, int pos_y){
        this.pos_x_ = pos_x;
        this.pos_y_ = pos_y;
    }

    public int getPos_x_() {
        return pos_x_;
    }

    public void setPos_x_(int pos_x_) {
        this.pos_x_ = pos_x_;
    }

    public int getPos_y_() {
        return pos_y_;
    }

    public void setPos_y_(int pos_y_) {
        this.pos_y_ = pos_y_;
    }

    public int getType_() {
        return type_;
    }

    public void setType_(int type) {
        this.type_ = type;
    }
}
