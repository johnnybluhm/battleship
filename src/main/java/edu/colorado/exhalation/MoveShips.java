package edu.colorado.exhalation;

import java.util.Arrays;

public class MoveShips implements Command{
    // actions_[0] in PlayerAction class

    Game game_; // Changes the board(s) connected to a specific instance of Game, this stores that instance
    Board[] undo_; // Array to be used as a stack for undoing Commands, stores Board before Command was used
    Board[] redo_; // Array to be used as a stack for redoing Commands, stores Board before Undo was used
    // NOTE: These are Last-In-First-Out stacks, the top of the stack in this instance is the last element of the array

    public MoveShips(Game game_){ // Generic constructor, passed game_ and makes undo_ and redo_ empty arrays
        this.game_ = game_;
        this.undo_ = new Board[] {};
        this.redo_ = new Board[] {};
    }

    public void Move(char direction_){ // Stores board before move in undo_, moves ships in direction 'direction_'
        undo_ = Arrays.copyOf(undo_, undo_.length + 1); // increases undo_'s size by 1
        undo_[undo_.length - 1] = game_.getBoard().copy(); // sets old board to last element of undo_
        game_.getBoard().move(direction_); // moves ships
    }

    public void Action(Point point_){ // Shouldn't ever call PlayerAction.Action(0, point_) in main class, since MoveShips only uses Move
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void Undo(){ // When you undo something, you have a new thing to redo if you so choose
        redo_ = Arrays.copyOf(redo_, redo_.length + 1); // increases redo_'s size by 1
        redo_[redo_.length - 1] = undo_[undo_.length - 1]; // sets last element of redo_ to be the same as the last element of undo_
        // When you undo something, if you want it back it's the first thing you redo
        game_.setBoard(undo_[undo_.length - 1]); // sets board in game_ to the last old board stored in undo_
        undo_ = Arrays.copyOf(undo_, undo_.length - 1); // pops board off of undo_, since it is now the current board
    }

    public void Redo(){ // When you redo something, you need to add it back to the undo_ stack since you might want to undo it again
        undo_ = Arrays.copyOf(undo_, undo_.length + 1); // increases undo_'s size by 1
        undo_[undo_.length - 1] = redo_[redo_.length - 1]; // sets last element of undo_ to be the same as the last element of redo_
        game_.setBoard(redo_[redo_.length - 1]); // sets board in game_ to be the next most recent board stored in redo_
        redo_ = Arrays.copyOf(redo_, redo_.length - 1); // pops board off of redo_, since it is now the current board
    }
}
