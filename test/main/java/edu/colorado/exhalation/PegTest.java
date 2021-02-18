package main.java.edu.colorado.exhalation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PegTest {

    private Peg test_peg;

    //will change once constructor is added
    @Test
    void testCreate(){
        test_peg = new Peg(1,0);
        assertEquals(1, test_peg.getPostition_x());
        assertEquals(0, test_peg.getPostition_y());
    }


}