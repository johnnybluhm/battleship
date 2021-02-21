package main.java.edu.colorado.exhalation;

import edu.colorado.exhalation.Peg;
import edu.colorado.exhalation.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    void testShipSunk() {
        Peg ship1 = new Peg(1, 0, 2);
        Peg ship2 = new Peg(1, 1, 2);
        Peg ship3 = new Peg(1, 2, 3);
        Peg ship4 = new Peg(1, 3, 4);
        ship1.hit();
        ship2.hit();
        ship3.hit();
        Peg [] location = {ship1, ship2};
        Peg [] location2 = {ship3, ship4};
        Ship mine1 = new Ship(location);
        Ship mine2 = new Ship(location2);

        assertEquals(true, mine1.isSunk());
        assertEquals(false, mine2.isSunk());
    }
}
