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
        }
        else {
            board = observable.getNpcState();
        }
        Peg peg = board.getPeg(this.location);
        int hit_count = peg.getHitCount()[0];
        //can now update based on what peg corresponds to button
        if(!peg.hasShip() && this.playerObserver == true){
            this.button.setBackground(Color.GREEN);
        }
        else if(!peg.hasShip()){
            this.button.setBackground(Color.RED);
        }
        if(hit_count==1){
            this.button.setBackground(Color.CYAN);
        }
        else if(hit_count==2){
            this.button.setBackground(Color.BLUE);
        }

        if(peg.hasShip()|| peg.getSub() != null){
            this.button.setBackground(Color.BLACK);
        }


    }
}//Guiobserver

