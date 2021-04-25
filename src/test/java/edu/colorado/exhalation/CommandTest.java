package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandTest {

    @Test
    void testMoveCommand(){ // Essentially testShift() from ShipTest.java but implementing Command Pattern
        Board board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        board.placeShip(minesweeper);
        board.placeShip(destroyer);
        board.placeShip(battleship);
        board.placeShip(submarine);

        Board shift_right_board = new Board();
        minesweeper = new Minesweeper('h', new Point(1,0));
        destroyer = new Destroyer('h', new Point(1,1));
        battleship = new Battleship('h', new Point(1,2));
        submarine = new Submarine('h', new Point(1,3));
        shift_right_board.placeShip(minesweeper);
        shift_right_board.placeShip(destroyer);
        shift_right_board.placeShip(battleship);
        shift_right_board.placeShip(submarine);

        Game game_ = new Game();
        game_.setPlayerBoard(board);

        game_.getRemote().char_action(0, 'E');

        Assertions.assertTrue(game_.getPlayerBoard().equals(shift_right_board));

    }

    @Test
    void testMoveUndo(){ // Using the above code as a base, now we are using the Command to move and testing the undoing of that Command
        Board undo_board = new Board();
        Board compare_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(4,4));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        undo_board.placeShip(minesweeper);
        undo_board.placeShip(destroyer);
        undo_board.placeShip(battleship);
        undo_board.placeShip(submarine);

        Ship minesweeper2 = new Minesweeper('h', new Point(0,0));
        Ship destroyer2 = new Destroyer('h', new Point(4,4));
        Ship battleship2 = new Battleship('h', new Point(0,2));
        Ship submarine2 = new Submarine('h', new Point(0,3));
        compare_board.placeShip(minesweeper2);

        compare_board.placeShip(destroyer2);
        compare_board.placeShip(battleship2);
        compare_board.placeShip(submarine2);

        Game game = new Game();
        game.setPlayerBoard(undo_board);

        game.getRemote().char_action(0, 'E');
        game.getRemote().undo();

        Assertions.assertTrue(game.getPlayerBoard().equals(compare_board));
    }

    @Test
    void testMoveRedo(){ // Once again using the code from above, we're moving, undoing that move, and redoing the undo and testing that here
        Board redo_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        redo_board.placeShip(minesweeper);
        redo_board.placeShip(destroyer);
        redo_board.placeShip(battleship);
        redo_board.placeShip(submarine);

        Board shift_right_board = new Board();
        Ship minesweeper2 = new Minesweeper('h', new Point(1,0));
        Ship destroyer2 = new Destroyer('h', new Point(1,1));
        Ship battleship2 = new Battleship('h', new Point(1,2));
        Ship submarine2 = new Submarine('h', new Point(1,3));
        shift_right_board.placeShip(minesweeper2);
        shift_right_board.placeShip(destroyer2);
        shift_right_board.placeShip(battleship2);
        shift_right_board.placeShip(submarine2);

        Game game = new Game();
        game.setPlayerBoard(redo_board);

        game.getRemote().char_action(0, 'E');
        game.getRemote().undo();
        game.getRemote().redo();

        Assertions.assertTrue(game.getPlayerBoard().equals(shift_right_board));
    }

    @Test
    void testHitCommand(){
        Board original_board = new Board();
        original_board.hit(new Point(0, 0));

        Board hit_board = new Board();
        Game game_ = new Game();
        game_.setNpcBoard(hit_board);
        game_.getRemote().point_action(1, new Point(0,0));

        Assertions.assertTrue(game_.getNpcBoard().equals(original_board));
    }

    @Test
    void testHitUndo(){
        Board original_board = new Board();
        Board undo_board = new Board();
        Game game_ = new Game();
        game_.setNpcBoard(undo_board);
        game_.getRemote().point_action(1, new Point(0,0));

        Assertions.assertFalse(game_.getNpcBoard().equals(original_board));

        game_.getRemote().undo();

        Assertions.assertTrue(game_.getNpcBoard().equals(original_board));
    }

    @Test
    void testHitRedo(){
        Board original_board = new Board();
        original_board.hit(new Point(0, 0));
        Board redo_board = new Board();
        Game game_ = new Game();
        game_.setNpcBoard(redo_board);
        game_.getRemote().point_action(1, new Point(0,0));

        Assertions.assertTrue(game_.getNpcBoard().equals(original_board));
        game_.getRemote().undo();
        Assertions.assertFalse(game_.getNpcBoard().equals(original_board));
        game_.getRemote().redo();
        Assertions.assertTrue(game_.getNpcBoard().equals(original_board));
    }

    @Test
    void testAirStrikeCommand(){
        Board original_board = new Board();
        original_board.airStrike(0);
        Board air_strike_board = new Board();
        Game game_ = new Game();
        game_.setNpcBoard(air_strike_board);
        game_.getRemote().num_action(3, 0);

        Assertions.assertTrue(game_.getNpcBoard().equals(original_board));
    }

    @Test
    void testAirStrikeUndo(){
        Board original_board = new Board();
        Board undo_board = new Board();
        Game game_ = new Game();
        game_.setNpcBoard(undo_board);
        game_.getRemote().num_action(3, 0);
        Assertions.assertFalse(game_.getNpcBoard().equals(original_board));
        game_.getRemote().undo();
        Assertions.assertTrue(game_.getNpcBoard().equals(original_board));
    }

    @Test
    void testAirStrikeRedo(){
        Board original_board = new Board();
        original_board.airStrike(0);
        Board redo_board = new Board();
        Game game_ = new Game();
        game_.setNpcBoard(redo_board);
        game_.getRemote().num_action(3, 0);
        Assertions.assertTrue(game_.getNpcBoard().equals(original_board));
        game_.getRemote().undo();
        Assertions.assertFalse(game_.getNpcBoard().equals(original_board));
        game_.getRemote().redo();
        Assertions.assertTrue(game_.getNpcBoard().equals(original_board));
    }

    @Test
    void testPlaceCommand(){
        Board original_board = new Board();
        original_board.placeShip(new Minesweeper('h', new Point(0,0)));
        original_board.placeShip(new Destroyer('h', new Point(0,1)));
        original_board.placeShip(new Battleship('h', new Point(0,2)));
        original_board.placeShip(new Submarine('h', new Point(0,3)));

        Game game_ = new Game();
        game_.getRemote().char_point_action(2, 'h', new Point(0,0));
        game_.getRemote().char_point_action(2, 'h', new Point(0,1));
        game_.getRemote().char_point_action(2, 'h', new Point(0,2));
        game_.getRemote().char_point_action(2, 'h', new Point(0,3));

        Assertions.assertTrue(game_.getPlayerBoard().equals(original_board));
    }

    @Test
    void testPlaceUndo(){
        Board original_board = new Board();

        Game game_ = new Game();
        game_.getRemote().char_point_action(2, 'h', new Point(0,0));
        game_.getRemote().char_point_action(2, 'h', new Point(0,1));
        game_.getRemote().char_point_action(2, 'h', new Point(0,2));
        game_.getRemote().char_point_action(2, 'h', new Point(0,3));

        Assertions.assertFalse(game_.getNpcBoard().equals(original_board));

        game_.getRemote().undo();
        game_.getRemote().undo();
        game_.getRemote().undo();
        game_.getRemote().undo();

        Assertions.assertTrue(game_.getPlayerBoard().equals(original_board));
    }

    @Test
    void testPlaceRedo(){
        Board original_board = new Board();
        original_board.placeShip(new Minesweeper('h', new Point(0,0)));
        original_board.placeShip(new Destroyer('h', new Point(0,1)));
        original_board.placeShip(new Battleship('h', new Point(0,2)));
        original_board.placeShip(new Submarine('h', new Point(0,3)));

        Game game_ = new Game();
        game_.getRemote().char_point_action(2, 'h', new Point(0,0));
        game_.getRemote().char_point_action(2, 'h', new Point(0,1));
        game_.getRemote().char_point_action(2, 'h', new Point(0,2));
        game_.getRemote().char_point_action(2, 'h', new Point(0,3));

        Assertions.assertTrue(game_.getPlayerBoard().equals(original_board));

        game_.getRemote().undo();
        game_.getRemote().undo();
        game_.getRemote().undo();
        game_.getRemote().undo();

        Assertions.assertFalse(game_.getPlayerBoard().equals(original_board));

        game_.getRemote().redo();
        game_.getRemote().redo();
        game_.getRemote().redo();
        game_.getRemote().redo();

        Assertions.assertTrue(game_.getPlayerBoard().equals(original_board));
    }

    @Test
    void testMultiActionUndo(){
        Board changing_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        changing_board.placeShip(minesweeper);
        changing_board.placeShip(destroyer);
        changing_board.placeShip(battleship);
        changing_board.placeShip(submarine);

        Board original_board = new Board();
        Ship minesweeper2 = new Minesweeper('h', new Point(0,0));
        Ship destroyer2 = new Destroyer('h', new Point(0,1));
        Ship battleship2 = new Battleship('h', new Point(0,2));
        Ship submarine2 = new Submarine('h', new Point(0,3));
        original_board.placeShip(minesweeper2);
        original_board.placeShip(destroyer2);
        original_board.placeShip(battleship2);
        original_board.placeShip(submarine2);

        Game game = new Game();
        game.setPlayerBoard(changing_board);
        game.setNpcBoard(changing_board);

        game.getRemote().char_action(0, 'E');
        game.getRemote().point_action(1, new Point(0, 0));
        game.getRemote().char_action(0, 'S');
        game.getRemote().point_action(1, new Point(0, 2));
        game.getRemote().char_action(0, 'E');
        game.getRemote().point_action(1, new Point(0, 4));
        for(int i = 0; i < 6; i++){
            game.getRemote().undo();
        }

        Assertions.assertTrue(game.getPlayerBoard().equals(original_board));
        Assertions.assertTrue(game.getNpcBoard().equals(original_board));
    }

    @Test
    void testMultiActionRedo(){
        Board changing_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        changing_board.placeShip(minesweeper);
        changing_board.placeShip(destroyer);
        changing_board.placeShip(battleship);
        changing_board.placeShip(submarine);

        Board changing_board2 = new Board();
        Ship minesweeper4 = new Minesweeper('h', new Point(0,0));
        Ship destroyer4 = new Destroyer('h', new Point(0,1));
        Ship  battleship4 = new Battleship('h', new Point(0,2));
        Ship submarine4 = new Submarine('h', new Point(0,3));
        changing_board2.placeShip(minesweeper4);
        changing_board2.placeShip(destroyer4);
        changing_board2.placeShip(battleship4);
        changing_board2.placeShip(submarine4);

        Board redone_board = new Board();
        Ship minesweeper2 = new Minesweeper('h', new Point(2,1));
        Ship destroyer2 = new Destroyer('h', new Point(2,2));
        Ship battleship2 = new Battleship('h', new Point(2,3));
        Ship submarine2 = new Submarine('h', new Point(2,4));
        redone_board.placeShip(minesweeper2);
        redone_board.placeShip(destroyer2);
        redone_board.placeShip(battleship2);
        redone_board.placeShip(submarine2);

        Board redone_board2 = new Board();
        Ship minesweeper3 = new Minesweeper('h', new Point(0,0));
        Ship destroyer3 = new Destroyer('h', new Point(0,1));
        Ship battleship3 = new Battleship('h', new Point(0,2));
        Ship submarine3 = new Submarine('h', new Point(0,3));
        redone_board2.placeShip(minesweeper3);
        redone_board2.placeShip(destroyer3);
        redone_board2.placeShip(battleship3);
        redone_board2.placeShip(submarine3);
        redone_board2.hit(new Point(0, 0));
        redone_board2.hit(new Point(0, 2));
        redone_board2.hit(new Point(0, 4));

        Game game = new Game();
        game.setPlayerBoard(changing_board);
        game.setNpcBoard(changing_board2);

        game.getRemote().char_action(0, 'E');
        game.getRemote().point_action(1, new Point(0, 0));
        game.getRemote().char_action(0, 'S');
        game.getRemote().point_action(1, new Point(0, 2));
        game.getRemote().char_action(0, 'E');
        game.getRemote().point_action(1, new Point(0, 4));
        for(int i = 0; i < 6; i++){
            game.getRemote().undo();
        }

        Assertions.assertFalse(game.getPlayerBoard().equals(redone_board));

        for(int i = 0; i < 6; i ++){
            game.getRemote().redo();
        }

        System.out.println(game.getPlayerBoard().getStateString());
        System.out.println(game.getNpcBoard().getStateString());
        System.out.println(redone_board.getStateString());

        Assertions.assertTrue(game.getPlayerBoard().equals(redone_board));
        Assertions.assertTrue(game.getNpcBoard().equals(redone_board2));
    }
}
