package edu.colorado.exhalation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubmarineTest {

    Point[] getVerticalSubPoints(Point start_point){

        int x = start_point.getX();
        int y = start_point.getY();
        Point[] vertical_point_array = new Point[5];
        vertical_point_array[0] = start_point;
        //tail
        vertical_point_array[1] = new Point(x,y+1);
        vertical_point_array[2] = new Point(x,y+2);
        vertical_point_array[3] = new Point(x,y+3);
        //bump
        vertical_point_array[4] = new Point(x+1,y+2);

        for (Point point: vertical_point_array
             ) {
            if(!point.isValid()){
                return null;
            }
        }
        return vertical_point_array;
    }

    Point[] getHorizontalSubPoints(Point start_point){
        int x = start_point.getX();
        int y = start_point.getY();
        Point[] horizontal_point_array = new Point[5];
        horizontal_point_array[0] = start_point;
        //tail
        horizontal_point_array[1] = new Point(x+1,y);
        horizontal_point_array[2] = new Point(x+2,y);
        horizontal_point_array[3] = new Point(x+3,y);
        //bump
        horizontal_point_array[4] = new Point(x+2,y-1);

        for (Point point: horizontal_point_array
        ) {
            if(!point.isValid()){
                return null;
            }
        }
        return horizontal_point_array;
    }

    @Test
    void testCreation(){
        Point start_point = new Point(2,4);
        Point[] horizontal = getHorizontalSubPoints(start_point);
        Point[] vertical = getVerticalSubPoints(start_point);

        Ship vertical_sub = new Submarine('v', start_point);
        Ship horizontal_sub = new Submarine('h', start_point);

        for (int i =0; i< horizontal.length; i++){
            Assertions.assertTrue(horizontal_sub.getPointArray()[i].equals(horizontal[i]));
            //System.out.println("POINT 1\n"+horizontal[i]+"\nPOINT 2\n"+horizontal_sub.getPointArray()[i]);
        }

        for (int i =0; i< vertical.length; i++){
            Assertions.assertTrue(vertical_sub.getPointArray()[i].equals(vertical[i]));
        }
    }

    @Test
    void testVerify(){

        Ship bad_sub = new Submarine('v', new Point(9,9));

        Assertions.assertTrue(!bad_sub.isValid());

        bad_sub = new Submarine('h', new Point(9,9));

        Assertions.assertTrue(!bad_sub.isValid());
    }

    @Test
    void testPlace(){

    }

}