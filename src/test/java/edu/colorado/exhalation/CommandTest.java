package edu.colorado.exhalation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandTest {

    @Test
    void testShiftCommand(){ // Essentially testShift() from ShipTest.java but implementing Command Pattern
        Board board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        //Ship submarine = new Submarine('h', new Point(0,3));
        board.placeShip(minesweeper);
        board.placeShip(destroyer);
        board.placeShip(battleship);
        //board.placeShip(submarine);

        Board shift_right_board = new Board();
        minesweeper = new Minesweeper('h', new Point(1,0));
        destroyer = new Destroyer('h', new Point(1,1));
        battleship = new Battleship('h', new Point(1,2));
        //submarine = new Submarine('h', new Point(1,3));
        shift_right_board.placeShip(minesweeper);
        shift_right_board.placeShip(destroyer);
        shift_right_board.placeShip(battleship);
        //shift_right_board.placeShip(submarine);

        Game game_ = new Game();
        game_.setBoard(board);
        PlayerAction testingCommands = new PlayerAction();
        testingCommands.SetCommand(0, new MoveShips(game_)); // sets new command MoveShips at slot 0 in testingCommands
        testingCommands.Move(0, 'E'); // slot is 0 because that's what MoveEast was set to
        System.out.println(shift_right_board.getStateString());
        System.out.println(game_.getBoard().getStateString());
        Assertions.assertTrue(game_.getBoard().equals(shift_right_board));

    }

    @Test
    void testUndo(){ // Using the above code as a base, now we are using the Command to move and testing the undoing of that Command
        Board board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        //board.placeShip(minesweeper);
        board.placeShip(destroyer);
        //board.placeShip(battleship);
        //board.placeShip(submarine);

        Board shift_right_board = new Board();
        minesweeper = new Minesweeper('h', new Point(0,0));
        destroyer = new Destroyer('h', new Point(0,1));
        battleship = new Battleship('h', new Point(0,2));
        submarine = new Submarine('h', new Point(0,3));
        shift_right_board.placeShip(minesweeper);
        shift_right_board.placeShip(destroyer);
        shift_right_board.placeShip(battleship);
        shift_right_board.placeShip(submarine);

        Game game_ = new Game();
        game_.setBoard(board);
        PlayerAction testingCommands = new PlayerAction();
        testingCommands.SetCommand(0, new MoveShips(game_));
        testingCommands.Move(0, 'E'); // Need to move in order to have something to undo
        testingCommands.Undo(); // No slot here because things to undo are stored in a stack

        Assertions.assertTrue(game_.getBoard().equals(shift_right_board));
    }

    @Test
    void testRedo(){ // Once again using the code from above, we're moving, undoing that move, and redoing the undo and testing that here
        Board board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));
        //board.placeShip(minesweeper);
        board.placeShip(destroyer);
        //board.placeShip(battleship);
        //board.placeShip(submarine);

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
        game_.setBoard(board);
        PlayerAction testingCommands = new PlayerAction();
        testingCommands.SetCommand(0, new MoveShips(game_));
        testingCommands.Move(0, 'E'); // Need to move in order to have something to undo
        testingCommands.Undo(); // Need to undo a move in order to have something to redo
        testingCommands.Redo(); // No slot here, redo commands are stored in testingCommands

        Assertions.assertTrue(game_.getBoard().equals(shift_right_board));
    }
}
