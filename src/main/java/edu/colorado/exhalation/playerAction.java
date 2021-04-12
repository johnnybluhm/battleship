package edu.colorado.exhalation;

import java.util.Stack;

// NOTE: I don't think the commented out CODE is necessary, but I'm leaving it in in case we decide it is

public class playerAction {
    Command[] actions_; // stores all classes implementing Command that we will use

    Stack<Integer> undo_action; // stores order of actions player has taken
//    Point[] undo_attack;
    Stack<Integer> redo_action; // stores order of actions player has undone
//    Point[] redo_attack;

    int num_actions = 3;
    /* List of classes that implement Command
    0 - MoveShips
    1 - Hit
    2 - SonarPulse
    */ // Maybe we could put PlaceShip in here too? It wouldn't be that hard

    public playerAction() { // generic constructor
        this.actions_ = new Command[num_actions];
        this.undo_action = new Stack<Integer>();
        this.redo_action = new Stack<Integer>();
    }

    public void setCommand(int slot_, Command action_){ // sets Command 'action_' at element 'slot' in array actions_[]
        actions_[slot_] = action_;
    }

    public void char_action(int slot_, char direction_){ // for Commands that use Move(char direction_)
        undo_action.push(slot_);
        actions_[slot_].char_action(direction_); // activates Command at actions_[slot] using 'direction'
    }

    public void action(int slot_, Point point_){ // for Commands that use Action(Point point_)
        undo_action.push(slot_);
//        undo_attack = Arrays.copyOf(undo_attack, undo_attack.length + 1);
//        undo_attack[undo_attack.length - 1] = point_;
        actions_[slot_].Action(point_); // activates Command at actions_[slot] using 'point_'
    }

    public void undo(){
        if(undo_action.isEmpty()){ // can't undo if there's nothing to be undone
            System.out.println("No actions to undo.");
        }
//        else if(undo_action[undo_action.length-1] == 4 || undo_action[undo_action.length-1] == 5){
//            // Action to be undone involves a Point
//            redo_action = Arrays.copyOf(redo_action, redo_action.length + 1);
//            redo_action[redo_action.length - 1] = undo_action[undo_action.length - 1];
//            redo_attack = Arrays.copyOf(redo_attack, redo_attack.length + 1);
//            redo_attack[redo_attack.length - 1] = undo_attack[undo_attack.length - 1];
//            actions_[undo_action[undo_action.length - 1]].Undo();
//            undo_action = Arrays.copyOf(undo_action, undo_action.length - 1);
//            undo_attack = Arrays.copyOf(undo_attack, undo_attack.length - 1);
//        }
        else { // there's something to be undone
            redo_action.push(undo_action.peek()); // extends redo_action by 1 index
            actions_[undo_action.pop()].Undo();
        }
    }

    public void redo(){
        if(redo_action.isEmpty()){ // can't redo if there's nothing to redo
            System.out.println("No actions to redo.");
        }
//        else if(redo_action[redo_action.length-1] == 4 || redo_action[redo_action.length-1] == 5){
//            // Action to be redone involves a Point
//            undo_action = Arrays.copyOf(undo_action, undo_action.length + 1);
//            undo_action[undo_action.length - 1] = redo_action[redo_action.length - 1];
//            undo_attack = Arrays.copyOf(undo_attack, undo_attack.length + 1);
//            undo_attack[undo_attack.length - 1] = redo_attack[redo_attack.length - 1];
//            actions_[redo_action[redo_action.length - 1]].Redo();
//            redo_action = Arrays.copyOf(redo_action, redo_action.length - 1);
//        }
        else { // there's something to be redone
            undo_action.push(redo_action.peek()); // extends undo_action by 1 index// puts index of redone Command into undo_action
            actions_[redo_action.pop()].Redo(); // calls Redo() for the Command most recently undone, aka at last element in redo_action[]
        }
    }

}
