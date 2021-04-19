package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testStart(){

        Board new_board = new Board();
        Game fresh_game = new Game();
        assertTrue(fresh_game.getPlayerBoard().equals(new_board));

    }
    @Test
    void testPlaceNpc(){
        Game game = new Game();

        game.placeShipsNpc();
        System.out.println(game.getNpcBoard().getStateString());
        Ship[] ships = game.getNpcBoard().getShips();
        for (Ship ship:
             ships) {
            Assertions.assertTrue(ship != null);
        }

        Board first_board = game.getNpcBoard().copy();

        game = new Game();

        game.placeShipsNpc();

        ships = game.getNpcBoard().getShips();
        for (Ship ship:
                ships) {
            Assertions.assertTrue(ship != null);
        }
        System.out.println(game.getNpcBoard().getStateString());

        Assertions.assertFalse(first_board.equals(game.getNpcBoard()));
    }
    @Test
    void testWinner(){

        Board test_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        test_board.placeShip(minesweeper);
        test_board.placeShip(destroyer);
        test_board.placeShip(battleship);
        test_board.placeShip(submarine);

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

        test_board.setWeapon(Board.LASER);

        points = submarine.getPoints();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }

        Assertions.assertTrue(test_board.isSunk(submarine));
        Game game = new Game();

        game.setPlayerBoard(test_board);

        assertTrue(game.gameOver());
    }
    @Test
    void testPlayerSunkCount(){

        Board test_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        test_board.placeShip(minesweeper);
        test_board.placeShip(destroyer);
        test_board.placeShip(battleship);
        test_board.placeShip(submarine);
        Game game = new Game();
        game.setPlayerBoard(test_board);
        test_board = game.getPlayerBoard();
        //hits every point on every ship and checks that they are sunk
        Point[] points = minesweeper.getPoints();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        game.setPlayerBoard(test_board);
        Assertions.assertTrue(game.getPlayerSunkCount() == 1);

        points = destroyer.getPoints();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        test_board.hit(points[1]);
        game.setPlayerBoard(test_board);
        Assertions.assertTrue(game.getPlayerSunkCount() == 2);

        points = battleship.getPoints();
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        test_board.hit(points[2]);
        game.setPlayerBoard(test_board);
        Assertions.assertTrue(game.getPlayerSunkCount() == 3);


        points = submarine.getPoints();
        test_board.setWeapon(Board.LASER);
        for(int i=0; i< points.length; i++){
            test_board.hit(points[i]);
        }
        game.setPlayerBoard(test_board);
        Assertions.assertTrue(game.getPlayerSunkCount() == 4);


    }
    @Test
    void testTogglePlayer(){

        Game game = new Game();
        Assertions.assertTrue(game.getIsPlayerTurn() == true);
        game.togglePlayer();
        Assertions.assertTrue(game.getIsPlayerTurn() == false);
    }
    @Test
    void testTakeTurn(){

        Board player_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        player_board.placeShip(minesweeper);
        player_board.placeShip(destroyer);
        player_board.placeShip(battleship);
        player_board.placeShip(submarine);

        Game game = new Game();
        game.placeShipsNpc();
        game.getNpcBoard().setWeapon(Board.LASER);
        game.setPlayerBoard(player_board);

        System.out.println(game.getNpcBoard().getStateString());
        while(game.notOver()){
            game.takeTurn(Board.HIT);
            game.togglePlayer();
        }
        System.out.println(game.getNpcBoard().getStateString());
        //System.out.println(game.getPlayerBoard().getStateString());

        //npc board always loses as player board cannot sink sub
        Assertions.assertTrue(game.getNpcSunkCount()==4);
    }
    @Test
    void testChessClock(){

        Board player_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        player_board.placeShip(minesweeper);
        player_board.placeShip(destroyer);
        player_board.placeShip(battleship);
        player_board.placeShip(submarine);

        Game game = new Game();
        game.placeShipsNpc();
        game.getNpcBoard().setWeapon(Board.LASER);
        game.setPlayerBoard(player_board);

        //loop takes 5 seconds to run
        //Board.time takes
        for (int i = 0; i < 10; i++) {
            game.takeTurn(Board.TIME);
        }
        Assertions.assertTrue(game.getPlayerBoard().getTimeLeft()<295000);
    }

    @Test
    void testStorms(){
        Game game = new Game();
        Game game2 = new Game();
        game.setPlayerBoard(game2.getNpcBoard());
        for (int i = 0; i <20 ; i++) {
            game.generateStorm();
        }
        //at least one ship should sink after 20 random storms
        Assertions.assertTrue(game.getNpcSunkCount() > 0);
        Assertions.assertTrue(game.getPlayerSunkCount() > 0);
    }


}