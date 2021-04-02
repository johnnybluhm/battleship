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
        PlayerAction testingCommands = new PlayerAction();
//        testingCommands.SetCommand(0, new MoveShips(game_)); // sets new command MoveShips at slot 0 in testingCommands
//        testingCommands.Move(0, 'E'); // slot is 0 because that's what MoveEast was set to

        game_.getRemote().Move(0, 'E');

        Assertions.assertTrue(game_.getPlayerBoard().equals(shift_right_board));

    }

    @Test
    void testUndo(){ // Using the above code as a base, now we are using the Command to move and testing the undoing of that Command
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

//        Stack<Board> stack = new Stack<Board>();
//        stack.push(undo_board.copy());
//        undo_board.move('E');

        Ship minesweeper2 = new Minesweeper('h', new Point(0,0));
        Ship destroyer2 = new Destroyer('h', new Point(4,4));
        Ship battleship2 = new Battleship('h', new Point(0,2));
        Ship submarine2 = new Submarine('h', new Point(0,3));
        compare_board.placeShip(minesweeper2);

        //Assertions.assertTrue(stack.pop().equals(compare_board));
        compare_board.placeShip(destroyer2);
        compare_board.placeShip(battleship2);
        compare_board.placeShip(submarine2);

        Game game = new Game();
        game.setPlayerBoard(undo_board);
//        PlayerAction testingCommands = new PlayerAction();
//        testingCommands.SetCommand(0, new MoveShips(game));
//        testingCommands.Move(0, 'E'); // Need to move in order to have something to undo
//        testingCommands.Undo(); // No slot here because things to undo are stored in a stack

        game.getRemote().Move(0, 'E');
        game.getRemote().Undo();

        Assertions.assertTrue(game.getPlayerBoard().equals(compare_board));
    }

//    @Test
//    void testStack(){
//        Board board = new Board();
//        Board compare_board = new Board();
//        Ship minesweeper = new Minesweeper('h', new Point(0,0));
//        Ship destroyer = new Destroyer('h', new Point(4,4));
//        Ship  battleship = new Battleship('h', new Point(0,2));
//        Ship submarine = new Submarine('h', new Point(0,3));
//        Board board2 = new Board();
//        Board compare_board2 = new Board();
//        Ship minesweeper2 = new Minesweeper('h', new Point(0,0));
//        Ship destroyer2 = new Destroyer('h', new Point(4,4));
//        Ship  battleship2 = new Battleship('h', new Point(0,2));
//        Ship submarine2 = new Submarine('h', new Point(0,3));
//
//        Stack<Board> stack = new Stack<Board>();
//        stack.push(board.copy());
//        Assertions.assertTrue(stack.pop().equals(board2));
//
//        board.placeShip(minesweeper);
//        Board board1_copy = board.copy();
//        board2.placeShip(minesweeper2);
//        stack.push(board);
//        Assertions.assertTrue(stack.pop().equals(board1_copy));
//        stack.push(board);
//        board.move('E');
//        Assertions.assertTrue(stack.pop().equals(board1_copy));
//        //undo_board.move('E');
//    }

    @Test
    void testRedo(){ // Once again using the code from above, we're moving, undoing that move, and redoing the undo and testing that here
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
//        PlayerAction testingCommands = new PlayerAction();
//        testingCommands.SetCommand(0, new MoveShips(game));
//        testingCommands.Move(0, 'E'); // Need to move in order to have something to undo
//        testingCommands.Undo(); // Need to undo a move in order to have something to redo
//        testingCommands.Redo(); // No slot here, redo commands are stored in testingCommands
        game.getRemote().Move(0, 'E');
        game.getRemote().Undo();
        game.getRemote().Redo();

        Assertions.assertTrue(game.getPlayerBoard().equals(shift_right_board));
    }

    @Test
    void testHitUndo(){
        Board no_hit_board = new Board();
        Board hit_board = new Board();

        Game game_ = new Game();
        game_.setPlayerBoard(hit_board);
        PlayerAction testingCommands = new PlayerAction();
        testingCommands.SetCommand(0, new Hit(game_));

        testingCommands.Action(0, new Point(0,0));

//        System.out.println(no_hit_board.getStateString());
//        System.out.println(game_.getBoard().getStateString());

        Assertions.assertFalse(game_.getPlayerBoard().equals(no_hit_board));

        testingCommands.Undo();

//        System.out.println(game_.getBoard().getStateString());

        Assertions.assertTrue(game_.getPlayerBoard().equals(no_hit_board));


    }

    @Test
    void testPlaceUndo(){

        Board undo_board = new Board();
        Board compare_board = new Board();
        Ship minesweeper = new Minesweeper('h', new Point(0,0));
        Ship destroyer = new Destroyer('h', new Point(0,1));
        Ship  battleship = new Battleship('h', new Point(0,2));
        Ship submarine = new Submarine('h', new Point(0,3));

        Game game = new Game();
        game.setPlayerBoard(undo_board);
        PlayerAction testingCommands = new PlayerAction();
        testingCommands.SetCommand(0, new MoveShips(game));


    }
}
