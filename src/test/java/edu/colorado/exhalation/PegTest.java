package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
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
        assertEquals(1, test_peg.getPoint().getX());
        assertEquals(0, test_peg.getPoint().getY());
    }
    @Test
    void testHit(){
        assertFalse(test_peg.isHit());
        test_peg.hit();
        assertTrue(test_peg.isHit());
        test_peg.hit();
        assertTrue(test_peg.isHit());

        test_peg.hit();
    }
    @Test
    void testHasShip(){

        Peg no_ship = new Peg(1,0);
        Peg ship_peg = new Peg(1,0);
        Ship battle_ship = new Battleship('v', new Point(0,0));
        ship_peg.setShip(battle_ship);

        assertFalse(no_ship.hasShip());
        assertTrue(ship_peg.hasShip());

    }
    //O for no hit, X for hit ship, # for hit no ship
    @Test
    void testPegPrint(){
        Peg ship_peg = new Peg(0,0);
        Ship battle_ship = new Battleship('v', new Point(0,0));
        ship_peg.setShip(battle_ship);
        Peg no_ship = new Peg(1,0);

        assertEquals('O', no_ship.print());
        no_ship.hit();
        assertEquals('#', no_ship.print());

        assertEquals('4',ship_peg.print());
        ship_peg.hit();
        assertEquals('X', ship_peg.print());

        Peg ship_peg2 = new Peg(2,0);
        Ship destroyer = new Destroyer('v', new Point(0,0));
        Ship sweeper = new Minesweeper('v', new Point(0,0));
        ship_peg2.setShip(destroyer);
        assertEquals('3',ship_peg2.print());

        ship_peg2.setShip(sweeper);
        assertEquals('2',ship_peg2.print());
    }

    @Test
    void testCopy(){
        Peg first_peg = new Peg(1,0);
        Ship test_ship =new Minesweeper('v', new Point(0,0));
        Ship test_ship2 =new Submarine('v', new Point(2,0));
        first_peg.setVisible();
        first_peg.setShip(test_ship);
        Peg copy_peg = first_peg.copy();
        Assertions.assertTrue(copy_peg.equals(first_peg));
        copy_peg.setHidden();
        Assertions.assertFalse(copy_peg.equals(first_peg));

        Peg second_peg = copy_peg.copy();
        Assertions.assertTrue(second_peg.equals(copy_peg));
        second_peg.setShip(test_ship2);
        assertFalse(second_peg.equals(copy_peg));

    }
    @Test
    void testEquals(){

        Board board = new Board();
        Board compare_board = new Board();
        Peg first_peg = board.getPeg(1,0);
        Peg compare_peg = board.getPeg(0,1);
        Peg second_peg = board.getPeg(2,0);
        Peg third_peg = board.getPeg(3,0);

        Assertions.assertFalse(first_peg.equals(compare_peg));

        compare_peg = compare_board.getPeg(1,0);
        Assertions.assertTrue(first_peg.equals(compare_peg));
        compare_peg.hit();
        Assertions.assertFalse(first_peg.equals(compare_peg));

        compare_peg = first_peg.copy();
        Ship test_ship =new Minesweeper('v', new Point(0,0));
        Ship test_sub =new Submarine('v', new Point(0,0));
        first_peg.setShip(test_ship);
        compare_peg.setShip(test_ship);
        Assertions.assertTrue(first_peg.equals(compare_peg));
        first_peg.setSub((Submarine) test_sub);
        compare_peg.setSub((Submarine) test_sub);
        Assertions.assertTrue(first_peg.equals(compare_peg));

        Peg copy_peg = first_peg.copy();
        Assertions.assertTrue(copy_peg.equals(first_peg));
        Ship test_battle_ship =new Battleship('v', new Point(0,0));
        copy_peg.setShip(test_battle_ship);
        Assertions.assertFalse(first_peg.equals(copy_peg));
        first_peg.setShip(test_battle_ship);
        compare_peg.setShip(test_battle_ship);
        Assertions.assertTrue(first_peg.equals(compare_peg));

        compare_peg = board.getPeg(3,0);
        Ship same_ship =new Minesweeper('v', new Point(0,0));
        Ship same_ship_diff_memory =new Minesweeper('v', new Point(0,0));
        third_peg.setShip(same_ship);
        compare_peg.setShip(same_ship_diff_memory);

        Assertions.assertTrue(third_peg.equals(compare_peg));

        board = new Board();
        Peg fresh_peg = board.getPeg(4,4);
        Peg fresh_peg_copy = fresh_peg.copy();

        Assertions.assertTrue(fresh_peg.equals(fresh_peg_copy));

    }

    @Test
    void testBoardInPeg(){

        Board board = new Board();
        Assertions.assertTrue(board.getPeg(2,0).getBoard().equals(board));

        board.hit(new Point(2,0));
        Assertions.assertTrue(board.getPeg(2,0).getBoard().equals(board));

        board.setWeapon(Board.LASER);

        Assertions.assertTrue(board.getPeg(7,7).getBoard().getWeapon()== Board.LASER);

    }
}//TEST

