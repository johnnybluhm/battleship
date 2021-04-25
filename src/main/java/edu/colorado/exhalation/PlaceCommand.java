package edu.colorado.exhalation;

import java.util.Stack;

public class PlaceCommand implements Command{

    Game game_;
    Stack<Point> undo_loc;
    Stack <Character> undo_orient;
    Stack <Character> undo_ship;
    Stack<Point> redo_loc;
    Stack<Character> redo_orient;
    Stack <Character> redo_ship;

    public PlaceCommand(Game game_){ // generic Constructor
        this.game_ = game_;
        this.undo_loc = new Stack<>();
        this.undo_orient = new Stack<>();
        this.undo_ship = new Stack<>();
        this.redo_loc = new Stack<>();
        this.redo_orient = new Stack<>();
        this.redo_ship = new Stack<>();
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
            undo_ship.push('m');
            return game_.getPlayerBoard().placeShip(new Minesweeper(orientation, point));
        }
        else if(game_.getPlayerBoard().getShips()[Board.DESTROYER] == null) {
            undo_loc.push(point);
            undo_orient.push(orientation);
            undo_ship.push('d');
            return game_.getPlayerBoard().placeShip(new Destroyer(orientation, point));
        }
        else if(game_.getPlayerBoard().getShips()[Board.BATTLESHIP] == null) {
            undo_loc.push(point);
            undo_orient.push(orientation);
            undo_ship.push('b');
            return game_.getPlayerBoard().placeShip(new Battleship(orientation, point));
        }
        else if(game_.getPlayerBoard().getShips()[Board.SUBMARINE] == null) {
            undo_loc.push(point);
            undo_orient.push(orientation);
            undo_ship.push('s');
            return game_.getPlayerBoard().placeShip(new Submarine(orientation, point));
        }
        else{
            return 0;
        }
    }

    public void undo(){
        redo_loc.push(undo_loc.peek());
        redo_orient.push(undo_orient.pop());
        redo_ship.push(undo_ship.pop());

        if(redo_ship.peek() == 'm'){
            game_.getPlayerBoard().getShips()[Board.MINESWEEPER] = null;
            Point [] temp = game_.getPlayerBoard().getPeg(undo_loc.pop()).getShip().getPoints();
            for(Point point : temp){
                game_.getPlayerBoard().resetPeg(point);
            }
        }
        else if(redo_ship.peek() == 'd') {
            game_.getPlayerBoard().getShips()[Board.DESTROYER] = null;
            Point [] temp = game_.getPlayerBoard().getPeg(undo_loc.pop()).getShip().getPoints();
            for(Point point : temp){
                game_.getPlayerBoard().resetPeg(point);
            }
        }
        else if(redo_ship.peek() == 'b') {
            game_.getPlayerBoard().getShips()[Board.BATTLESHIP] = null;
            Point [] temp = game_.getPlayerBoard().getPeg(undo_loc.pop()).getShip().getPoints();
            for(Point point : temp){
                game_.getPlayerBoard().resetPeg(point);
            }
        }
        else if(redo_ship.peek() == 's') {
            game_.getPlayerBoard().getShips()[Board.SUBMARINE] = null;
            Point [] temp = game_.getPlayerBoard().getPeg(undo_loc.pop()).getSub().getPoints();
            for(Point point : temp){
                game_.getPlayerBoard().resetPeg(point);
            }
        }
    }

    public void redo(){
        undo_loc.push(redo_loc.pop());
        undo_orient.push(redo_orient.pop());
        undo_ship.push(redo_ship.pop());

        if(undo_ship.peek() == 'm') {
            System.out.println("Placing minesweeper");
            this.char_point_action(undo_orient.peek(), undo_loc.peek());
//            game_.getPlayerBoard().placeShip(new Minesweeper(undo_orient.peek(), undo_loc.peek()));
        }
        else if(undo_ship.peek() == 'd') {
            System.out.println("Placing destroyer");
            game_.getPlayerBoard().placeShip(new Destroyer(undo_orient.peek(), undo_loc.peek()));
        }
        else if(undo_ship.peek() == 'b') {
            System.out.println("Placing battleship");
            game_.getPlayerBoard().placeShip(new Battleship(undo_orient.peek(), undo_loc.peek()));
        }
        else if(undo_ship.peek() == 's') {
            System.out.println("Placing submarine");
            game_.getPlayerBoard().placeShip(new Submarine(undo_orient.peek(), undo_loc.peek()));
        }
    }
}