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
        assertTrue(test_peg.isHit() == false);
        test_peg.hit();
        assertTrue(test_peg.isHit());
        test_peg.hit();
        assertTrue(test_peg.isHit());
    }

    @Test
    void testHasShip(){

        Peg no_ship = new Peg(1,0);
        Peg ship_1 = new Peg(1,0,1);
        Peg ship_2 = new Peg(1,0);
        Peg ship_3 = new Peg(1,0, 3);
        ship_2.setType_(2);


        assertFalse(no_ship.hasShip());
        assertTrue(ship_1.hasShip());
        assertTrue(ship_2.hasShip());
        assertTrue(ship_3.hasShip());
    }


    //O for no hit, X for hit ship, # for hit no ship
    @Test
    void testPegPrint(){
        Peg no_ship = new Peg(1,0);
        Peg ship_1 = new Peg(1,0,1);
        Peg ship_2 = new Peg(1,0);
        Peg noship2 = new Peg(1,0);

        no_ship.hit();
        ship_1.hit();
        assertEquals('#', no_ship.print());
        assertEquals('X', ship_1.print());
        assertEquals('O', ship_2.print());
        assertEquals('O', noship2.print());

    }


}