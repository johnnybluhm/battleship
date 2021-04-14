package edu.colorado.exhalation;

import javax.swing.*;
import java.awt.*;

public class GuiObserver {

    private Gui observable;
    private JButton button;
    private Point location;
    private boolean playerObserver;

    public GuiObserver(Gui observable, JButton button, Point point, boolean player_observer){
        this.observable = observable;
        this.observable.attach(this);
        this.button = button;
        this.location = point;
        if(player_observer == true){
            this.playerObserver = true;
        }
        else {
            this.playerObserver = false;
        }
    }

    public void setButton(){
        this.button = button;
    }
    public JButton getButton(){
        return this.button;
    }

    public void update(){

        Board board;
        if(this.playerObserver == true){
            board = observable.getPlayerState();
            Peg peg = board.getPeg(this.location);
            int hit_count = peg.getHitCount()[0];
            this.button.setBackground(Color.WHITE);
            this.button.setText(String.valueOf(hit_count));

            //ship coloring
            if(peg.hasShip() && peg.getSub() != null){
                //sub and ship
                this.button.setBackground(Color.BLUE);
            }
            else if(peg.getSub() != null){
                //just sub
                this.button.setBackground(Color.CYAN);
            }
            else if(peg.hasShip()){
                this.button.setBackground(Color.BLACK);
            }

            if(board.isSunk(peg.getShip())){
                //ship is sunk
                this.button.setText("X");
                this.button.setBackground(Color.RED);
            }
        }//playerObserver


        else {
            board = observable.getNpcState();
            Peg peg = board.getPeg(this.location);
            int hit_count = peg.getHitCount()[0];

            this.button.setBackground(Color.WHITE);
            this.button.setText(String.valueOf(hit_count));

            //ship coloring
            if(peg.hasShip() && peg.getSub() != null){
                //sub and ship
                this.button.setBackground(Color.BLUE);
            }
            else if(peg.getSub() != null){
                //just sub
                this.button.setBackground(Color.CYAN);
            }
            else if(peg.hasShip()){
                this.button.setBackground(Color.BLACK);
            }

            if(board.isSunk(peg.getShip())){
                //ship is sunk
                this.button.setText("X");
                this.button.setBackground(Color.RED);
            }

        }//NpcObserver



    }
}//Guiobserver

