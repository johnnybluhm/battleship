package edu.colorado.exhalation;

import javax.swing.*;
import java.awt.*;

public class GuiObserver {

    protected Gui observable;
    private JButton button;

    public GuiObserver(Gui observable, JButton button){
        this.observable = observable;
        //this.observable.attach(this);
        this.button = button;
    }

    public void setButton(){
        this.button = button;
    }
    public JButton getButton(){
        return this.button;
    }

    public void update(){
        JButton button = this.getButton();
        button.setBackground(Color.magenta);
    }
}
