package edu.colorado.exhalation;

public class Game {

    private Board board;

    public Game(){
        this.board = new Board();
    }

    public Board getBoard(){
        return this.board;
    }
}
