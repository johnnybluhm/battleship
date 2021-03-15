package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WaterTest {
    @Test
    void testMake(){

        Point test_point = new Point(0,0);
        Ship water = new Water(new Point(0,0));

        Assertions.assertTrue(!water.isArmoured());
        Assertions.assertTrue(water.getHitCount(water.getPoints()[0])==0);
        water.hit(water.getPoints()[0]);
        Assertions.assertTrue(water.getHitCount(water.getPoints()[0])==1);
        water.hit(water.getPoints()[0]);
        Assertions.assertTrue(water.getHitCount(water.getPoints()[0])==2);

    }
}