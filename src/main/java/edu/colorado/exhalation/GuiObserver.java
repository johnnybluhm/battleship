package edu.colorado.exhalation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiObserver {

    private Gui observable;
    private JButton button;
    private Point location;

    public GuiObserver(Gui observable, JButton button, Point point){
        this.observable = observable;
        this.observable.attachPlayer(this);
        this.button = button;
        this.location = point;
    }

    public void setButton(){
        this.button = button;
    }
    public JButton getButton(){
        return this.button;
    }

    public void update(){

        Board board =observable.getPlayerState();
        Peg peg = board.getPeg(this.location);
        int hit_count = peg.getHitCount()[0];
        //can now update based on what peg corresponds to button
        if(!peg.hasShip()){
            this.button.setBackground(Color.GREEN);
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

