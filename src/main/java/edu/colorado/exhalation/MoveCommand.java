package edu.colorado.exhalation;

import java.util.Stack;

public class MoveCommand implements Command{
    // actions_[0] in PlayerAction class

    Game game_; // Changes the board(s) connected to a specific instance of Game, this stores that instance
    Stack<Character> undo_; // Array to be used as a stack for undoing Commands, stores Board before Command was used
    Stack<Character> redo_; // Array to be used as a stack for redoing Commands, stores Board before Undo was used
    // NOTE: These are Last-In-First-Out stacks, the top of the stack in this instance is the last element of the array

    public MoveCommand(Game game_){ // Generic constructor, passed game_ and makes undo_ and redo_ empty arrays
        this.game_ = game_;
        this.undo_ = new Stack<Character>();
        this.redo_ = new Stack<Character>();
    }

    public void char_action(char direction_){ // Stores board before move in undo_, moves ships in direction 'direction_'
        if(direction_ == 'E'){
            undo_.push('W');
        }
        else if(direction_ == 'W'){
            undo_.push('E');
        }
        else if(direction_ == 'N'){
            undo_.push('S');
        }
        else if(direction_ == 'S'){
            undo_.push('N');
        }
        else{
            System.out.println("ERROR: Direction entered that isn't N,S,E, or W.");
        }
//        System.out.println(undo_.peek().getStateString());
        game_.getPlayerBoard().move(direction_); // moves ships
    }

    public void point_action(Point point_){ // Shouldn't ever call PlayerAction.Action(0, point_) in main class, since MoveShips only uses Move
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void num_action(int num){
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void undo(){
        if(undo_.peek() == 'E'){
            redo_.push('W');
        }
        else if(undo_.peek() == 'W'){
            redo_.push('E');
        }
        else if(undo_.peek() == 'N'){
            redo_.push('S');
        }
        else if(undo_.peek() == 'S'){
            redo_.push('N');
        }
        game_.getPlayerBoard().move(undo_.pop());
    }

    public void redo(){
        if(redo_.peek() == 'E'){
            undo_.push('W');
        }
        else if(redo_.peek() == 'W'){
            undo_.push('E');
        }
        else if(redo_.peek() == 'N'){
            undo_.push('S');
        }
        else if(redo_.peek() == 'S'){
            undo_.push('N');
        }

        game_.getPlayerBoard().move(redo_.pop()); // sets board in game_ to be the next most recent board stored in redo_
    }
}
