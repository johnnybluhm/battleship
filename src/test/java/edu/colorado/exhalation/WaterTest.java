package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterTest {
    @Test
    void testMake(){
        Ship water = new Water(new Point(0,0));
        Assertions.assertTrue(!water.isArmoured());
        Assertions.assertTrue(water.getHitCount(water.getPointArray()[0])==0);
        water.hit(water.getPointArray()[0]);
        Assertions.assertTrue(water.getHitCount(water.getPointArray()[0])==1);
        water.hit(water.getPointArray()[0]);
        Assertions.assertTrue(water.getHitCount(water.getPointArray()[0])==2);

    }
}