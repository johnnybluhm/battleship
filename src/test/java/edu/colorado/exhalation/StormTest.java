package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

class StormTest {
    @Test
    void testIncludes(){
        Storm storm = new Storm();
        Board board = new Board();
        Point test_point = storm.getPoints()[0];

        Peg peg = board.getPeg(test_point);
        Assertions.assertTrue(storm.includes(peg.getPoint()));
    }

    @Test
    void testProbabilityDistribution(){

        Storm storm;
        int[] size_count = new int[3];
        size_count[0] = 0;//60%
        size_count[1] = 0;//30%
        size_count[2] = 0;//10%
        for (int i = 0; i < 10000; i++) {
            storm = new Storm();
            if (storm.getSize() == 2) {
                size_count[0] += 1;
            } else if (storm.getSize() == 3) {
                size_count[1] += 1;
            } else if (storm.getSize() == 4) {
                size_count[2] += 1;
            }
        }
            int size_two = size_count[0];
            int size_three = size_count[1];
            int size_four = size_count[2];

            //+-150 for error
            Assertions.assertTrue(size_two>5850 && size_two <6150);
            Assertions.assertTrue(size_three>2850 && size_three <3150);
            Assertions.assertTrue(size_four>850 && size_four <1150);
    }

}