package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

class StormTest {
    @Test
    void testConatinsPeg(){
        Storm storm = new Storm();
        Board board = new Board();
        Point test_point = storm.getPoints()[0];

        Peg peg = board.getPeg(test_point);
        Assertions.assertTrue(storm.hasPeg(peg));
    }
}