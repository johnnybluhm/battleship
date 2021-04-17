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
            if(peg.isHit()){
                this.button.setBackground(Color.PINK);
            }
            if(peg.hasShip() && peg.getSub() != null){
                //sub and ship
                this.button.setBackground(Color.BLUE);
            }
            else if(peg.getSub() != null){
                //just sub
                this.button.setBackground(Color.CYAN);
            }
            else if(peg.hasShip()){
                if(peg.getShip().isArmoured()){
                    this.button.setBackground(Color.black);
                }
                else{
                    this.button.setBackground(Color.GRAY);
                }
            }

            if(board.isSunk(peg.getShip())){
                //ship is sunk
                this.button.setText("X");
                this.button.setBackground(Color.RED);
            }
            else if(peg.getSub()!= null){
                if(board.isSunk(peg.getSub())){
                    //ship is sunk
                    this.button.setText("X");
                    this.button.setBackground(Color.RED);
                }
            }
        }//playerObserver


        else {
            board = observable.getNpcState();
            Peg peg = board.getPeg(this.location);
            int hit_count = peg.getHitCount()[0];

            this.button.setBackground(Color.WHITE);
            this.button.setText(String.valueOf(hit_count));

            //ship coloring
            if(peg.isVisible()){
                //sonar pulse happened
                //color ships based off friendly
                //ship coloring
                this.button.setBackground(Color.orange);
                if(peg.isHit()){
                    this.button.setBackground(Color.PINK);
                }
                if(peg.hasShip() && peg.getSub() != null){
                    //sub and ship
                    this.button.setBackground(Color.BLUE);
                }
                else if(peg.getSub() != null){
                    //just sub
                    this.button.setBackground(Color.CYAN);
                }
                else if(peg.hasShip()){
                    if(peg.getShip().isArmoured()){
                        this.button.setBackground(Color.black);
                    }
                    else{
                        this.button.setBackground(Color.GRAY);
                    }
                }
            }
            else{
                if(peg.isHit()){
                    this.button.setBackground(Color.PINK);
                }
                if(peg.hasShip() && peg.getSub() != null && peg.isHit()){
                    //sub and ship
                    this.button.setBackground(Color.BLUE);
                }
                else if(peg.getSub() != null && peg.isHit()){
                    //just sub
                    this.button.setBackground(Color.CYAN);
                }
                else if(peg.hasShip() && peg.isHit()){
                    if(peg.getShip().isArmoured()){
                        this.button.setBackground(Color.black);
                    }
                    else{
                        this.button.setBackground(Color.GRAY);
                    }
                }

            }//isNotVisible

            if(board.isSunk(peg.getShip())){
                //ship is sunk
                this.button.setText("X");
                this.button.setBackground(Color.RED);
            }
            else if(peg.getSub()!= null){
                if(board.isSunk(peg.getSub())){
                    //ship is sunk
                    this.button.setText("X");
                    this.button.setBackground(Color.RED);
                }
            }

        }//NpcObserver
    }

    
}//Guiobserver

