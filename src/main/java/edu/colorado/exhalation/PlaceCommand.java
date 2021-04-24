package edu.colorado.exhalation;

import java.util.Stack;

public class PlaceCommand implements Command{

    Game game_;
    Stack<Point> undo_loc;
    Stack <Character> undo_orient;
    Stack<Point> redo_loc;
    Stack<Character> redo_orient;

    public PlaceCommand(Game game_){ // generic Constructor
        this.game_ = game_;
        this.undo_loc = new Stack<>();
        this.undo_orient = new Stack<>();
        this.redo_loc = new Stack<>();
        this.redo_orient = new Stack<>();
    }

    public void char_action(char direction){
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void point_action(Point point_){
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public void num_action(int num){
        System.out.println("Something's wrong here, you shouldn't be calling this in the code.");
    }

    public int char_point_action(char orientation, Point point){
        if(game_.getPlayerBoard().getShips()[Board.MINESWEEPER] == null) {
            undo_loc.push(point);
            undo_orient.push(orientation);
            return game_.getPlayerBoard().placeShip(new Minesweeper(orientation, point));
        }
        else if(game_.getPlayerBoard().getShips()[Board.DESTROYER] == null) {
            undo_loc.push(point);
            undo_orient.push(orientation);
            return game_.getPlayerBoard().placeShip(new Destroyer(orientation, point));
        }
        else if(game_.getPlayerBoard().getShips()[Board.BATTLESHIP] == null) {
            undo_loc.push(point);
            undo_orient.push(orientation);
            return game_.getPlayerBoard().placeShip(new Battleship(orientation, point));
        }
        else if(game_.getPlayerBoard().getShips()[Board.SUBMARINE] == null) {
            undo_loc.push(point);
            undo_orient.push(orientation);
            return game_.getPlayerBoard().placeShip(new Submarine(orientation, point));
        }
        else{
            return 0;
        }
    }

    public void undo(){
        System.out.println(undo_loc);
        redo_loc.push(undo_loc.peek());
        redo_orient.push(undo_orient.pop());

        Peg temp = game_.getPlayerBoard().getPeg(undo_loc.pop());

        if(temp.hasShip()){
            Point[] temp2 = game_.getPlayerBoard().getPeg(undo_loc.pop()).getShip().getPoints();
            for (Point point : temp2) {
                game_.getPlayerBoard().resetPeg(point);
            }
        }
        else if(temp.hasSub()){
            Point[] temp2 = game_.getPlayerBoard().getPeg(undo_loc.pop()).getShip().getPoints();
            for (Point point : temp2) {
                game_.getPlayerBoard().resetPeg(point);
            }
        }
    }

    public void redo(){
        undo_loc.push(redo_loc.peek());
        undo_orient.push(redo_orient.peek());
        if(game_.getPlayerBoard().getShips()[Board.MINESWEEPER] == null) {
            game_.getPlayerBoard().placeShip(new Minesweeper(redo_orient.pop(), redo_loc.pop()));
        }
        else if(game_.getPlayerBoard().getShips()[Board.DESTROYER] == null) {
            game_.getPlayerBoard().placeShip(new Destroyer(redo_orient.pop(), redo_loc.pop()));
        }
        else if(game_.getPlayerBoard().getShips()[Board.BATTLESHIP] == null) {
            game_.getPlayerBoard().placeShip(new Battleship(redo_orient.pop(), redo_loc.pop()));
        }
        else if(game_.getPlayerBoard().getShips()[Board.SUBMARINE] == null) {
            game_.getPlayerBoard().placeShip(new Submarine(redo_orient.pop(), redo_loc.pop()));
        }
    }
}