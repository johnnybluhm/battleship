package main.java.edu.colorado.exhalation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PegTest {



    //will change once constructor is added
    @Test
    void testCreate(){
        Peg test_peg = new Peg(1,0);
        assertEquals(1, test_peg.getPos_x_());
        assertEquals(0, test_peg.getPos_y_());
    }
    @Test
    voidTestPegType(){
        Peg test_peg = new Peg(1,0);
        test_peg.setType_(1);
        assertEquals(1, test_peg.getType_());

    }


}