package edu.colorado.exhalation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    
    @Test
    void testStart(){

        Board new_board = new Board();

        Game fresh_game = new Game();

        assertTrue(fresh_game.getBoard().equals(new_board));

    }
}