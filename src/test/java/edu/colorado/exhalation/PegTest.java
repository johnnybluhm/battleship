package edu.colorado.exhalation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static junit.framework.TestCase.assertEquals;
//import static junit.framework.TestCase.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    //Make sure you can't attack same place twice
  /*  @Test
    void testPegOverlap(){
        Peg peg_hit = new Peg(1,0);
        Peg ship_hit = new Peg(1, 1, 1);
        Peg no_hit = new Peg(1, 2);
        peg_hit.hit();
        ship_hit.hit();

//        String miss_msg = "Already attacked here! It was a miss!";
//        String hit_msg = "Already attacked here! It was a hit!";

        assertEquals(true, peg_hit.overlap());
        assertEquals(true, ship_hit.overlap());
        assertEquals(false, no_hit.overlap());
    }

    @Test
    void testShipOverlap(){
        Peg ship1 = new Peg(1, 0, 2);
        Peg ship2 = new Peg(1, 1, 3);
        Peg ship3 = new Peg(1, 2, 4);
        Peg no_ship = new Peg(1, 3, 0);

//        String mine_msg = "Minesweeper is here! Place ship elsewhere!";
//        String dest_msg = "Destroyer is here! Place ship elsewhere!";
//        String batt_msg = "Battleship is here! Place ship elsewhere!";

        assertEquals(true, ship1.overlap());
        assertEquals(true, ship2.overlap());
        assertEquals(true, ship3.overlap());
        assertEquals(false, no_ship.overlap());
    }*/
}