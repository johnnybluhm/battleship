package edu.colorado.exhalation;

import java.util.Arrays;

// NOTE: I don't think the commented out CODE is necessary, but I'm leaving it in in case we decide it is

public class PlayerAction {
    Command[] actions_; // stores all classes implementing Command that we will use

    int[] undo_action; // stores order of actions player has taken
//    Point[] undo_attack;
    int[] redo_action; // stores order of actions player has undone
//    Point[] redo_attack;

    int num_actions = 3;
    /* List of classes that implement Command
    0 - MoveShips
    1 - Hit
    2 - SonarPulse
    */ // Maybe we could put PlaceShip in here too? It wouldn't be that hard

    public PlayerAction() { // generic constructor
        this.actions_ = new Command[num_actions];
        this.undo_action = new int[] {};
        this.redo_action = new int[] {};
    }

    public void SetCommand(int slot_, Command action_){ // sets Command 'action_' at element 'slot' in array actions_[]
        actions_[slot_] = action_;
    }

    public void Move(int slot_, char direction_){ // for Commands that use Move(char direction_)
        undo_action = Arrays.copyOf(undo_action, undo_action.length + 1);
        undo_action[undo_action.length - 1] = slot_; // adds Command index in actions_[] to undo_action
        actions_[slot_].Move(direction_); // activates Command at actions_[slot] using 'direction'
    }

    public void Action(int slot_, Point point_){ // for Commands that use Action(Point point_)
        undo_action = Arrays.copyOf(undo_action, undo_action.length + 1);
        undo_action[undo_action.length - 1] = slot_; // adds Command index in actions_[] to undo_action
//        undo_attack = Arrays.copyOf(undo_attack, undo_attack.length + 1);
//        undo_attack[undo_attack.length - 1] = point_;
        actions_[slot_].Action(point_); // activates Command at actions_[slot] using 'point_'
    }

    public void Undo(){
        if(undo_action.length == 0){ // can't undo if there's nothing to be undone
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
            redo_action = Arrays.copyOf(redo_action, redo_action.length + 1); // extends redo_action by 1 index
            redo_action[redo_action.length - 1] = undo_action[undo_action.length - 1]; // puts index of undone Command into redo_action
            actions_[undo_action[undo_action.length - 1]].Undo(); // calls Undo() for the Command most recently done, aka at last element in undo_action[]
            undo_action = Arrays.copyOf(undo_action, undo_action.length - 1); // pops off top Command index in undo_action, it was just undone
        }
    }

    public void Redo(){
        if(redo_action.length == 0){ // can't redo if there's nothing to redo
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
            undo_action = Arrays.copyOf(undo_action, undo_action.length + 1); // extends undo_action by 1 index
            undo_action[undo_action.length - 1] = redo_action[redo_action.length - 1]; // puts index of redone Command into undo_action
            actions_[redo_action[redo_action.length - 1]].Redo(); // calls Redo() for the Command most recently undone, aka at last element in redo_action[]
            redo_action = Arrays.copyOf(redo_action, redo_action.length - 1); // pops off top Command index in redo_action, it was just redone
        }
    }

}
