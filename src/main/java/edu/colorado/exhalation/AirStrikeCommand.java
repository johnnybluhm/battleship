package edu.colorado.exhalation;

import java.util.Stack;

public class AirStrikeCommand implements Command{
    // actions_[3] in PlayerAction class

    // Same variables as used in all Command classes
    Game game_;
    Stack<Integer> undo_;
    Stack<Integer> redo_;

    public AirStrikeCommand(Game game_){ // generic Constructor
        this.game_ = game_;
        this.undo_ = new Stack<>();
        this.redo_ = new Stack<>();
    }

    public void char_action(char direction){
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void point_action(Point point_){
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void num_action(int num){
        undo_.push(num);
        game_.getPlayerBoard().airStrike(num);
    }

    // Same Undo and Redo logic as in MoveCommand.java
    public void undo(){
        redo_.push(undo_.peek());
        game_.getPlayerBoard().undoAirStrike(undo_.pop());
    }

    public void redo(){
        undo_.push(redo_.peek());
        game_.getPlayerBoard().airStrike(redo_.pop());
    }
}
