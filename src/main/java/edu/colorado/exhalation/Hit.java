package edu.colorado.exhalation;

import java.util.Arrays;

public class Hit implements Command{
    // actions_[1] in PlayerAction class

    // Same variables as used in all Command classes
    Game game_;
    Board[] undo_;
    Board[] redo_;

    public Hit(Game game_){ // generic Constructor
        this.game_ = game_;
        this.undo_ = new Board[] {};
        this.redo_ = new Board[] {};
    }

    public void Move(char direction){ // In our main class, we shouldn't ever call PlayerAction.Move(1, 4), since Hit will only use Attack method
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void Action(Point point_){ // Stores board before hit in undo_, hits the board at point_
        undo_ = Arrays.copyOf(undo_, undo_.length + 1);
        undo_[undo_.length - 1] = game_.getBoard().copy();
        game_.getBoard().hit(point_);
    }

    // Same Undo and Redo logic as in MoveShips.java
    public void Undo(){
        redo_ = Arrays.copyOf(redo_, redo_.length + 1);
        redo_[redo_.length - 1] = undo_[undo_.length - 1];
        game_.setBoard(undo_[undo_.length - 1]);
        undo_ = Arrays.copyOf(undo_, undo_.length - 1);
    }

    public void Redo(){
        undo_ = Arrays.copyOf(undo_, undo_.length + 1);
        undo_[undo_.length - 1] = redo_[redo_.length - 1];
        game_.setBoard(redo_[redo_.length - 1]);
        redo_ = Arrays.copyOf(redo_, redo_.length - 1);
    }
}
