package edu.colorado.exhalation;

public class Storm {

    private Point[] points_;
    private int storm_size_;

    public Storm(){

        int random_x;
        int random_y;
        Point random_point;
        int random_num;

        random_num = (int)(Math.random()*100 % 10);
        random_x = (int)(Math.random()*100 % 10);
        random_y = (int)(Math.random()*100 % 10);
        random_point = new Point(random_x,random_y);

        while(!random_point.isValid()){
            //get another point if point bad
            random_x = (int)(Math.random()*100 % 10);
            random_y = (int)(Math.random()*100 % 10);
            random_point = new Point(random_x,random_y);
        }
        if(random_num <3){
            //30%
            this.storm_size_ = 2;
        }
        else if(random_num >= 3 && random_num<6){
            //30%
            storm_size_ =3;
        }
        else if(random_num >=6 && random_num<8){
            //20%
            storm_size_ = 4;
        }
        else if(random_num >= 8 && random_num<9){
            //10%
            storm_size_ = 5;
        }
        else{
            //10%
            storm_size_ = 6;
        }
    }

    public static void generatePoints(Point start_point, int storm_size){
        int x = start_point.getX();
        int y = start_point.getY();
        if(storm_size == 2){
            Point[] points = new Point[storm_size+1];
            //get points to sides
            points[0] = new Point(x+1,y);
            points[1] = new Point(x+2,y);
            points[2] = new Point(x-1,y);
            points[3] = new Point(x-2,y);

            //get points above/below
            points[4] = new Point(x,y+1);
            points[5] = new Point(x,y+2);
            points[6] = new Point(x,y-1);
            points[7] = new Point(x,y-2);

            //get corners
            points[8] = new Point(x+1,y+1);
            points[9] = new Point(x-1,y-1);
            points[10] = new Point(x-1,y+1);
            points[11] = new Point(x+1,y-1);

            //add selected point
            //points[12] = point;
        }
        else if(storm_size == 3){
            Point[] points = new Point[storm_size+1];
            //get points to sides
            points[0] = new Point(x+1,y);
            points[1] = new Point(x+2,y);
            points[2] = new Point(x-1,y);
            points[3] = new Point(x-2,y);

            //get points above/below
            points[4] = new Point(x,y+1);
            points[5] = new Point(x,y+2);
            points[6] = new Point(x,y-1);
            points[7] = new Point(x,y-2);

            //get corners
            points[8] = new Point(x+1,y+1);
            points[9] = new Point(x-1,y-1);
            points[10] = new Point(x-1,y+1);
            points[11] = new Point(x+1,y-1);

            //add selected point
            //points[12] = point;
        }
        else if(storm_size == 4){
            Point[] points = new Point[storm_size+1];
            //get points to sides
            points[0] = new Point(x+1,y);
            points[1] = new Point(x+2,y);
            points[2] = new Point(x-1,y);
            points[3] = new Point(x-2,y);

            //get points above/below
            points[4] = new Point(x,y+1);
            points[5] = new Point(x,y+2);
            points[6] = new Point(x,y-1);
            points[7] = new Point(x,y-2);

            //get corners
            points[8] = new Point(x+1,y+1);
            points[9] = new Point(x-1,y-1);
            points[10] = new Point(x-1,y+1);
            points[11] = new Point(x+1,y-1);

            //add selected point
            //points[12] = point;
        }
        else if(storm_size == 5){

        }
    }

    public int getStormSize(){
        return this.storm_size_;
    }

    public Point[] getPoints(){
        return this.points_;
    }

    public boolean containsPeg(Peg peg){
        Point peg_point = peg.getPoint();
        for (int i = 0; i <this.getPoints().length ; i++) {
            if(this.getPoints()[i].equals(peg_point)){
                return true;
            }
        }
        return false;
    }
}
