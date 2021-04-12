package edu.colorado.exhalation;

import java.util.Stack;

public class MoveShips implements Command{
    // actions_[0] in PlayerAction class

    Game game_; // Changes the board(s) connected to a specific instance of Game, this stores that instance
    Stack<Board> undo_; // Array to be used as a stack for undoing Commands, stores Board before Command was used
    Stack<Board> redo_; // Array to be used as a stack for redoing Commands, stores Board before Undo was used
    // NOTE: These are Last-In-First-Out stacks, the top of the stack in this instance is the last element of the array

    public MoveShips(Game game_){ // Generic constructor, passed game_ and makes undo_ and redo_ empty arrays
        this.game_ = game_;
        this.undo_ = new Stack<Board>();
        this.redo_ = new Stack<Board>();
    }

    public void char_action(char direction_){ // Stores board before move in undo_, moves ships in direction 'direction_'
        undo_.push(game_.getPlayerBoard().copy());
//        System.out.println(undo_.peek().getStateString());
        game_.getPlayerBoard().move(direction_); // moves ships
    }

    public void Action(Point point_){ // Shouldn't ever call PlayerAction.Action(0, point_) in main class, since MoveShips only uses Move
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void Undo(){ // When you undo something, you have a new thing to redo if you so choose
        // When you undo something, if you want it back it's the first thing you redo
//        System.out.println(undo_.peek().getStateString());
        redo_.push(game_.getPlayerBoard().copy());
        game_.setPlayerBoard(undo_.pop()); // sets board in game_ to the last old board stored in undo_
    }

    public void Redo(){ // When you redo something, you need to add it back to the undo_ stack since you might want to undo it again
        //System.out.println(redo_.peek().getStateString());
        undo_.push(redo_.peek()); // increases undo_'s size by 1
        game_.setPlayerBoard(redo_.pop()); // sets board in game_ to be the next most recent board stored in redo_
    }
}
