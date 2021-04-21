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
        playerAction testingCommands = new playerAction();
//        testingCommands.SetCommand(0, new MoveShips(game_)); // sets new command MoveShips at slot 0 in testingCommands
//        testingCommands.Move(0, 'E'); // slot is 0 because that's what MoveEast was set to

        game_.getRemote().char_action(0, 'E');

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

        game.getRemote().char_action(0, 'E');
        game.getRemote().undo();

        Assertions.assertTrue(game.getPlayerBoard().equals(compare_board));
    }

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
        game.getRemote().char_action(0, 'E');
        game.getRemote().undo();
        game.getRemote().redo();

        Assertions.assertTrue(game.getPlayerBoard().equals(shift_right_board));
    }

    @Test
    void testHitUndo(){
        Board no_hit_board = new Board();
        Board hit_board = new Board();

        Game game_ = new Game();
        game_.setPlayerBoard(hit_board);

        game_.getRemote().action(1, new Point(0,0));

//        System.out.println(no_hit_board.getStateString());
//        System.out.println(game_.getBoard().getStateString());

        Assertions.assertFalse(game_.getPlayerBoard().equals(no_hit_board));

        game_.getRemote().undo();

//        System.out.println(game_.getBoard().getStateString());

        Assertions.assertTrue(game_.getPlayerBoard().equals(no_hit_board));


    }

//    @Test
//    void testPlaceUndo(){
//
//        Board undo_board = new Board();
//        Board compare_board = new Board();
//        Ship minesweeper = new Minesweeper('h', new Point(0,0));
//        Ship destroyer = new Destroyer('h', new Point(0,1));
//        Ship  battleship = new Battleship('h', new Point(0,2));
//        Ship submarine = new Submarine('h', new Point(0,3));
//
//        Game game = new Game();
//        game.setPlayerBoard(undo_board);
//        playerAction testingCommands = new playerAction();
//        testingCommands.setCommand(0, new MoveShips(game));
//
//
//    }

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

        game.getRemote().char_action(0, 'E');
        game.getRemote().action(1, new Point(0, 0));
        game.getRemote().char_action(0, 'S');
        game.getRemote().action(1, new Point(0, 2));
        game.getRemote().char_action(0, 'E');
        game.getRemote().action(1, new Point(0, 4));
        for(int i = 0; i < 6; i++){
            game.getRemote().undo();
        }

        Assertions.assertTrue(game.getPlayerBoard().equals(original_board));
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

        Board redone_board = new Board();
        Ship minesweeper2 = new Minesweeper('h', new Point(2,1));
        Ship destroyer2 = new Destroyer('h', new Point(2,2));
        Ship battleship2 = new Battleship('h', new Point(2,3));
        Ship submarine2 = new Submarine('h', new Point(2,4));
        redone_board.placeShip(minesweeper2);
        redone_board.placeShip(destroyer2);
        redone_board.placeShip(battleship2);
        redone_board.placeShip(submarine2);
        redone_board.hit(new Point(0, 0));
        redone_board.hit(new Point(0, 2));
        redone_board.hit(new Point(0, 4));

        Game game = new Game();
        game.setPlayerBoard(changing_board);

        game.getRemote().char_action(0, 'E');
        game.getRemote().action(1, new Point(0, 0));
        game.getRemote().char_action(0, 'S');
        game.getRemote().action(1, new Point(0, 2));
        game.getRemote().char_action(0, 'E');
        game.getRemote().action(1, new Point(0, 4));
        for(int i = 0; i < 6; i++){
            game.getRemote().undo();
        }

        Assertions.assertFalse(game.getPlayerBoard().equals(redone_board));

        for(int i = 0; i < 6; i ++){
            game.getRemote().redo();
        }

        Assertions.assertTrue(game.getPlayerBoard().equals(redone_board));
    }
}
