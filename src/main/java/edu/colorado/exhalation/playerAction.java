package edu.colorado.exhalation;

import java.util.Stack;

// NOTE: I don't think the commented out CODE is necessary, but I'm leaving it in in case we decide it is

public class playerAction {
    Command[] actions_; // stores all classes implementing Command that we will use

    Stack<Integer> undo_action; // stores order of actions player has taken
    Stack<Integer> redo_action; // stores order of actions player has undone

    int num_actions = 4;
    /* List of classes that implement Command
    0 - MoveCommand
    1 - HitCommand
    2 - PlaceCommand
    3 - AirStrikeCommand
    */

    public playerAction() { // generic constructor
        this.actions_ = new Command[num_actions];
        this.undo_action = new Stack<Integer>();
        this.redo_action = new Stack<Integer>();
    }

    public void setCommand(int slot_, Command action_){ // sets Command 'action_' at element 'slot' in array actions_[]
        actions_[slot_] = action_;
    }

    public void char_action(int slot_, char direction_){
        undo_action.push(slot_);
        actions_[slot_].char_action(direction_);
    }

    public void point_action(int slot_, Point point_){
        undo_action.push(slot_);
        actions_[slot_].point_action(point_);
    }

    public void num_action(int slot_, int num){
        undo_action.push(slot_);
        actions_[slot_].num_action(num);
    }

    public int char_point_action(int slot_, char character, Point point){
        undo_action.push(slot_);
        return(actions_[slot_].char_point_action(character, point));
    }

    public void undo(){
        if(undo_action.isEmpty()){ // can't undo if there's nothing to be undone
            System.out.println("No actions to undo.");
        }
        else { // there's something to be undone
            redo_action.push(undo_action.peek());
            actions_[undo_action.pop()].undo();
        }
    }

    public void redo(){
        if(redo_action.isEmpty()){ // can't redo if there's nothing to redo
            System.out.println("No actions to redo.");
        }
        else { // there's something to be redone
            undo_action.push(redo_action.peek());
            actions_[redo_action.pop()].redo();
        }
    }

}
