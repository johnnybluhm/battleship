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
        Point d_point = new Point(7,0);
        Point b_point = new Point(9,0);
        Point s_point = new Point(7,0);
        Ship minesweeper = new Minesweeper('v',m_point);
        Ship destroyer = new Destroyer('v', d_point);
        Ship battleship = new Battleship('v', b_point);
        Ship submarine = new Submarine('v', s_point);
        test_board.placeShip(minesweeper);
        test_board.placeShip(destroyer);
        test_board.placeShip(battleship);
        test_board.placeShip(submarine);
        Game fresh_game = new Game();
        fresh_game.place("src/test/java/edu/colorado/exhalation/user_commands.txt");


        System.out.println(fresh_game.getBoard().getStateString());
        System.out.println(test_board.getStateString());
        Assertions.assertTrue(fresh_game.getBoard().equals(test_board));
    }

    @Test
    void testPlaceNpc(){
        Game game = new Game();

        game.placeShipsNpc();

        Ship[] ships = game.getNpcBoard().getShips();
        for (Ship ship:
             ships) {
            Assertions.assertTrue(ship != null);
        }
    }


    @Test
    void testWinner(){

        Board test_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        test_board.placeShip(minesweeper);
        test_board.placeShip(destroyer);
        test_board.placeShip(battleship);

        //hits every point on every ship and checks that they are sunk
        Point[] points = minesweeper.getPoints();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        Assertions.assertTrue(test_board.isSunk(minesweeper));

        points = destroyer.getPoints();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        Assertions.assertFalse(test_board.isSunk(destroyer));
        test_board.hit(points[1]);
        Assertions.assertTrue(test_board.isSunk(destroyer));

        points = battleship.getPoints();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        Assertions.assertFalse(test_board.isSunk(battleship));
        test_board.hit(points[2]);
        Assertions.assertTrue(test_board.isSunk(battleship));

        Game game = new Game();

        game.setBoard(test_board);

        assertTrue(game.isLoser());
    }

}