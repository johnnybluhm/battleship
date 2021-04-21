package edu.colorado.exhalation;

import java.util.Stack;

public class SonarCommand implements Command {
    // actions_[2] in PlayerAction class

    // Same variables as used in all Command classes
    Game game_;
    Stack<Point> undo_;
    Stack<Point> redo_;

    public SonarCommand(Game game_){ // generic Constructor
        this.game_ = game_;
        this.undo_ = new Stack<>();
        this.redo_ = new Stack<>();
    }

    public void char_action(char direction){ // In our main class, we shouldn't ever call PlayerAction.Move(1, 4), since Hit will only use Attack method
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void point_action(Point point_){ // Stores board before hit in undo_, hits the board at point_
        undo_.push(point_);
        game_.getNpcBoard().sonarPulse(point_);
    }

    public void num_action(int num){
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    // Same Undo and Redo logic as in MoveCommand.java
    public void undo(){
        redo_.push(undo_.peek());
        game_.getNpcBoard().undoSonarPulse(undo_.pop());
    }

    public void redo(){
        undo_.push(redo_.peek());
        game_.getNpcBoard().sonarPulse(redo_.pop());
    }
}
