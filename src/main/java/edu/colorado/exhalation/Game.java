package edu.colorado.exhalation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Game {

    private Board board_;

    public Game(){
        this.board_ = new Board();
    }

    public Board getBoard(){
        return this.board_;
    }

    //for testing, takes a file as user input
    public void place(String file_name){

        File user_moves = new File(file_name);


        try (FileReader file_reader = new FileReader(user_moves)) {
            //successfully opened file

            for(int i=0;i<this.getBoard().getShips().length;i++){
                System.out.println("Please select orientation");
                System.out.println("v : vertical");
                System.out.println("h : horizontal");
                char orientation = (char) file_reader.read();
                System.out.println(orientation);
                System.out.println("Please select x position of point as a number");
                char x =(char) file_reader.read();
                System.out.println(x);
                System.out.println("Please select y position of point as a number");
                char y =(char) file_reader.read();
                System.out.println(y);

                int pos_x = Character.getNumericValue(x);
                int pos_y = Character.getNumericValue(y);

                Point ship_start = new Point(pos_x,pos_y);

                if(i ==0){
                    Ship minesweeper = new Minesweeper(orientation, ship_start);
                    this.getBoard().placeShip(minesweeper);
                }
                else if(i == 1){
                    Ship destroyer = new Destroyer(orientation, ship_start);
                    this.getBoard().placeShip(destroyer);
                }
                else{
                    Ship battleship = new Battleship(orientation, ship_start);
                    this.getBoard().placeShip(battleship);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBoard(Board board){
        this.board_ = board;
    }

    public boolean isLoser(){
        Board board = this.getBoard();
        Ship[] ships = board.getShips();
        int sunk_count =0;
        for(int i =0; i<ships.length; i++){
            if(board.isSunk(ships[i])){
                sunk_count++;
            }
        }
        if(sunk_count == board.getShips().length){
            return true;
        }
        else{
            return false;
        }
    }//isWinner()

}//Game
