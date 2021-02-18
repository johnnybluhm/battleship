package main.java.edu.colorado.exhalation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PegTest {


    private Peg test_peg;


    @BeforeEach
    void create(){
      test_peg = new Peg(1,0);
    }

    //test using point (1,0)
    @Test
    void testCreate(){
        assertEquals(1, test_peg.getPos_x_());
        assertEquals(0, test_peg.getPos_y_());
    }
    @Test
    void testPegType(){
        test_peg.setType_(1);
        assertEquals(1, test_peg.getType_());
    }

    @Test
    void testHit(){
        
    }


}