package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testStart(){

        Board new_board = new Board();
        Game fresh_game = new Game();
        assertTrue(fresh_game.getBoard().equals(new_board));

    }

    @Test
    void testPlace(){

        Board test_board = new Board();
        Point m_point = new Point(0,0);
        Point b_point = new Point(9,0);
        Point d_point = new Point(7,0);
        Ship minesweeper = new Minesweeper("v",m_point);
        Ship destroyer = new Destroyer("v", d_point);
        Ship battleship = new Battleship("v", b_point);

        test_board.placeShip(minesweeper);
        test_board.placeShip(destroyer);
        test_board.placeShip(battleship);



        Game fresh_game = new Game();

        fresh_game.place();

        Assertions.assertTrue(fresh_game.getBoard().equals(test_board));


    }

}