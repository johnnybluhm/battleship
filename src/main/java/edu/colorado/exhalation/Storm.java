package edu.colorado.exhalation;

public class Storm {

    private Point[] points_;
    private int size_;

    public Storm(){

        int random_num = (int)(Math.random()*100 % 10);
        int storm_size;
        if(random_num <3){
            //30%
            storm_size = 2;
            this.size_ = storm_size;
            Point[] points = generatePoints(storm_size);
            while(points == null){
                points = generatePoints(storm_size);
            }
            this.points_ = points;
        }
        else if(random_num >= 3 && random_num<=6){
            //40%
            storm_size =3;
            this.size_ = storm_size;
            Point[] points = generatePoints(storm_size);
            while(points == null){
                points = generatePoints(storm_size);
            }
            this.points_ = points;
        }
        else if(random_num >6 && random_num<=9){
            //30%
            storm_size = 4;
            this.size_ = storm_size;
            Point[] points = generatePoints(storm_size);
            while(points == null){
                points = generatePoints(storm_size);
            }
            this.points_ = points;
        }
    }

    //-1 storm had invalid point in it
    public static Point[] generatePoints(int storm_size){
        int random_x;
        int random_y;
        Point random_point;

        random_x = (int)(Math.random()*100 % 10);
        random_y = (int)(Math.random()*100 % 10);
        random_point = new Point(random_x,random_y);

        while(!random_point.isValid()){
            //get another point if point bad
            random_x = (int)(Math.random()*100 % 10);
            random_y = (int)(Math.random()*100 % 10);
            random_point = new Point(random_x,random_y);
        }
        int x = random_x;
        int y = random_y;

        if(storm_size == 2){

            Point[] points = new Point[storm_size*storm_size];
            //get points to sides
            points[0] = random_point;
            points[1] = new Point(x+1,y);
            points[2] = new Point(x+1,y+1);
            points[3] = new Point(x,y+1);

            for (int i = 0; i <points.length ; i++) {
                if(!points[i].isValid()){
                    return null;
                }
            }
            return points;
        }
        else if(storm_size == 3){

            Point[] points = new Point[storm_size*storm_size];

            points[0] = random_point;
            points[1] = new Point(x+1,y);
            points[2] = new Point(x+1,y+1);
            points[3] = new Point(x,y+1);


            points[4] = new Point(x+2,y);
            points[5] = new Point(x,y+2);
            points[6] = new Point(x+2,y+2);
            points[7] = new Point(x+1,y+2);
            points[8] = new Point(x+2,y+1);

            for (int i = 0; i <points.length ; i++) {
                if(!points[i].isValid()){
                    return null;
                }
            }
            return points;
        }
        else if(storm_size == 4){

            Point[] points = new Point[storm_size*storm_size];

            points[0] = random_point;
            points[1] = new Point(x+1,y);
            points[2] = new Point(x+1,y+1);
            points[3] = new Point(x,y+1);


            points[4] = new Point(x+2,y);
            points[5] = new Point(x,y+2);
            points[6] = new Point(x+2,y+2);
            points[7] = new Point(x+1,y+2);
            points[8] = new Point(x+2,y+1);

            points[9] = new Point(x+3,y);
            points[10] = new Point(x,y+3);
            points[11] = new Point(x+2,y+3);
            points[12] = new Point(x+3,y+2);
            points[13] = new Point(x+3,y+1);
            points[14] = new Point(x+1,y+3);
            points[15] = new Point(x+3,y+3);

            for (int i = 0; i <points.length ; i++) {
                if(!points[i].isValid()){
                    return null;
                }
            }
            return points;
        }
        return null;
    }

    public Point[] getPoints(){
        return this.points_;
    }

    public boolean includes(Peg peg){
        Point peg_point = peg.getPoint();
        for (int i = 0; i <this.getPoints().length ; i++) {
            if(this.getPoints()[i].equals(peg_point)){
                return true;
            }
        }
        return false;
    }

    public int getSize(){
        return this.size_;
    }
}
